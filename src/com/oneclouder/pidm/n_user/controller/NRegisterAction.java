package com.oneclouder.pidm.n_user.controller;

import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.n_user.web_bean.RegisterFormBean;
import com.oneclouder.pidm.user.annotation.Token;
import com.oneclouder.pidm.util.FtpUtil;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-15
 * @Time: 下午11:13
 */
@Controller
@RequestMapping("new-register")
public class NRegisterAction {
    @Resource(name = "nUserService")
    private INUserService userService;

    @Resource(name = "nCompanyService")
    private INCompanyService companyService;

    @RequestMapping(value = "redirect")
    @Token(save = true)
    public String redirect(String msg, Model model) {
        if ("goto-register".equals(msg)) {
            return "foreground/n_user/register";
        } else if ("goto-login".equals(msg)) {
            return "foreground/n_user/login";
        } else if ("goto-reset".equals(msg)) {
            return "foreground/n_user/reset-step1";
        } else if ("请勿重复提交".equals(msg)) {
            return "redirect:../index";
        }
        //账号激活 信息判断跳转
        else if ("注册成功，请到邮箱中激活账号".equals(msg)) {
            return "redirect:../index";
        } else if ("验证码错误,请联系管理员".equals(msg)) {
            return "redirect:../index";
        } else if ("此用户邮箱已验证，请勿重复验证".equals(msg)) {
            return "foreground/n_user/login";
        }
        //用户登陆 信息判断跳转
        else if ("账号或密码错误".equals(msg)) {
            model.addAttribute("msg", msg);
            return "foreground/n_user/login";
        } else if ("账号未验证,请到邮箱验证".equals(msg)) {
            return "redirect:../index";
        } else if ("账号已被停用,请联系管理员".equals(msg) || "账号正在审核,请联系管理员".equals(msg)) {
            model.addAttribute("msg", msg);
            return "foreground/n_user/login";
        } else if ("forget-pw".equals(msg)) {
            return "foreground/n_user/reset-step1";
        }
        //忘记密码 信息判断跳转
        else if ("账号没激活,请到邮箱激活".equals(msg) || "账号未审核,请联系管理员".equals(msg) || "账号已停用,请联系管理员".equals(msg)) {
            return "redirect:../index";
        } else if ("账号存在,跳转到第二步".equals(msg)) {
            return "foreground/n_user/reset-step2";
        } else if ("账号不存在,跳转回第一步".equals(msg)) {
            model.addAttribute("msg", "账号不存在");
            return "foreground/n_user/reset-step1";
        } else if ("验证通过,跳转到第三步".equals(msg)) {
            return "foreground/n_user/reset-step3";
        } else if ("验证不通过,跳转回第二步".equals(msg)) {
            model.addAttribute("msg", "验证码错误");
            return "foreground/n_user/reset-step2";
        } else if ("密码修改成功".equals(msg)) {
            return "foreground/n_user/login";
        } else if ("服务器忙,请稍后操作".equals(msg)) {
            return "redirect:../index";
        }
        return "redirect:../index";
    }

    @RequestMapping(value = "existAccount", method = RequestMethod.POST)
    @ResponseBody
    public String existAccount(String account, HttpServletRequest request) throws SQLException {
        boolean exist = userService.isExist(account);
        if (exist) {
            return "123";
        } else {
            return null;
        }
    }

    @RequestMapping(value = "existCompany", method = RequestMethod.POST)
    @ResponseBody
    public String existCompany(String companyName, HttpServletRequest request) throws SQLException {
        boolean exist = companyService.isExist(companyName);
        if (exist) {
            return "123";
        } else {
            return null;
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @Token(remove = true)
    public String register(RegisterFormBean formBean, Model model, HttpServletRequest request) throws Exception {
        //TODO 防止重复账号(表单重复提交控制后删除)
        String account = formBean.getUser().getAccount();
        if (userService.isExist(account)) {
            return "redirect:../index";
        }
        //TODO 防止重复公司(表单重复提交控制后删除)
        String companyName = formBean.getCompany().getName();
        if (companyService.isExist(companyName)) {
            return "redirect:../index";
        }

        Integer userId = userService.register(formBean, request);
        /*if (userId != null) {
            workFlowService.start(userId,account,"User","Insert");
        }*/
        model.addAttribute("msg", "注册成功，请到邮箱中激活账号");
        return "foreground/n_user/msg";
    }

    /**
     * Created By IntelliJ IDEA
     * 激活账号
     *
     * @param code  邮箱验证码
     * @param model 转发对象
     * @return String
     * @throws SQLException
     * @Author: AngryFeng
     * @Date: 16-10-17 上午8:25
     */
    @RequestMapping(value = "active", method = RequestMethod.GET)
    public String active(String code, Model model) throws SQLException {
        NUser user = userService.verifyCode(code);
        if (user == null) {
            model.addAttribute("msg", "验证码错误,请联系管理员");
        } else {
            if (user.getStatus() != 0) {
                model.addAttribute("msg", "此用户邮箱已验证，请勿重复验证");
            } else {
                //将用户激活
                Integer row = userService.activeAccount(user);
                if (row == 1) {
                    model.addAttribute("msg", "验证成功");
                } else {
                    model.addAttribute("msg", "服务器忙");
                }
            }
        }
        return "foreground/n_user/msg";
    }

    /**
     * Created By IntelliJ IDEA
     * 附件下载
     *
     * @param filePath ftp路径
     * @return
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-20 下午4:25
     */
    @RequestMapping(value = "downloadApplication")
    public void downloadAnnex(HttpServletResponse response, String filePath) throws IOException {
        String fileName = filePath.substring(filePath.lastIndexOf("/"));
        FtpUtil.downloadFile(response, filePath, fileName);
    }

}

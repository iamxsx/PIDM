package com.oneclouder.pidm.n_user.controller;

import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.n_user.web_bean.EmailBean;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.user.annotation.Token;
import com.oneclouder.pidm.util.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-15
 * @Time: 下午11:05
 */
@Controller
@RequestMapping("new-login")
public class NLoginAction {
    @Resource(name = "nUserService")
    private INUserService nUserService;

    private Log log = LogFactory.getLog(this.getClass());

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @Token(remove = true)
    public String login(String account, String password, String isRemember,
                        HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, SQLException {
//        System.out.println("account = " + account + " password=" + password + " isRemember=" + isRemember);
//        System.out.println(request.getSession().getAttribute("token"));
        String msg = new String();
        NUser user = null;
        user = nUserService.login(account, password);
        if (user != null) {
            //判断当前用户是否可用
            if (user.getStatus() == 0) {
                msg = "账号未验证,请到邮箱验证";
                model.addAttribute("msg", msg);
                return "forward:/foreground/n_user/msg";
            } else if (user.getStatus() == 4) {
                msg = "账号已被停用,请联系管理员";
                return "forward:/new-register/redirect?msg=" + msg;
            } else if (user.getStatus() == 1) {
                msg = "账号正在审核,请联系管理员";
                return "forward:/new-register/redirect?msg=" + msg;
            }

            //创建userInfo,封装用户必要的登陆信息
            NUserInfo userInfo = new NUserInfo();
            userInfo.setLoginDate(new Date());
            userInfo.setIp(GetIPUtil.getRemortIP(request));
            //TODO 是否完全封装
            userInfo = (NUserInfo) RegisterEntity.register(userInfo, user);
            System.out.println(userInfo);

            //获取当前会话的session
            HttpSession currSession = request.getSession();
            currSession.setAttribute("userInfo", userInfo);
            // 在context域中存放一个map 用于记录所有登录用户的必要信息 key:account value:session
            ServletContext context = request.getServletContext();
            Map<String, HttpSession> userInfos = (HashMap<String, HttpSession>) context.getAttribute("userInfos");
            //第一个用户登陆
            if (userInfos == null || userInfos.size() == 0) {
                userInfos = new HashMap<String, HttpSession>();
            } else {
                //用户不同ip登陆
                if (userInfos.containsKey(userInfo.getAccount())) {
                    //获取前一个用户的session 并将他的登陆信息注销掉
                    HttpSession oldSession = userInfos.get(userInfo.getAccount());
                    NUserInfo oldUserInfo = (NUserInfo) oldSession.getAttribute("userInfo");
                    log.info("账号" + oldUserInfo.getAccount() + "在" + userInfo.getIp() + "已经登录，该登录将被迫下线！");
                    oldSession.removeAttribute("userInfo");
                    oldSession.setAttribute("loginmsg", "您的账号已经在其他机器上登录,您被迫下线！");
                }
            }
            //将新登录的用户session放进map
            userInfos.put(userInfo.getAccount(), currSession);
            //更新context域中的map
            context.setAttribute("userInfos", userInfos);
            //若记住密码 存放到cookie中
            if (isRemember != null) {
                CookieUtil.addCookie(response, "account", user.getAccount(), 7);
                CookieUtil.addCookie(response, "password", user.getPassword(), 7);
            }
            return "redirect:../index";
        } else {
//            model.addAttribute("msg", "login-error");
            //TODO 考虑直接转发到action 参数情况(自动添加了.jsp)
            msg = "账号或密码错误";
            return "forward:/new-register/redirect?msg=" + msg;
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        ServletContext context = session.getServletContext();
        Map<String, HttpSession> userInfos = (HashMap<String, HttpSession>) context.getAttribute("userInfos");
        if (userInfos != null && userInfos.size() > 0) {
            NUserInfo userInfo = (NUserInfo) session.getAttribute("userInfo");
            if (userInfo != null) {
                if (userInfos.containsKey(userInfo.getAccount())) {
                    userInfos.remove(userInfo.getAccount());
                }
            }
        }
        session.removeAttribute("userInfo");
        return "redirect:../index";
    }

    /**
     * Created By IntelliJ IDEA
     * 重置密码第一步
     *
     * @param account
     * @param model
     * @return
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午8:43
     */
    @RequestMapping(value = "reset-step1", method = RequestMethod.POST)
    @Token(remove = true)
    public String testAccount(String account, Model model, HttpSession session) {
        String msg = new String();
        if (!StringUtils.isEmpty(account)) {
            NUser user = nUserService.findByAccount(account);
            if (user != null) {
                if (user.getStatus() == 0) {
                    msg = "账号没激活,请到邮箱激活";
                    model.addAttribute("msg", msg);
                    return "foreground/n_user/msg";
                } else if (user.getStatus() == 1) {
                    msg = "账号未审核,请联系管理员";
                    model.addAttribute("msg", msg);
                    return "foreground/n_user/msg";
                } else if (user.getStatus() == 4) {
                    msg = "账号已停用,请联系管理员";
                    model.addAttribute("msg", msg);
                    return "foreground/n_user/msg";
                } else {
                    msg = "账号存在,跳转到第二步";
                    //将账号保存到session
                    session.setAttribute("account", account);
                }
            } else {
                msg = "账号不存在,跳转回第一步";
            }
        }
        return "forward:/new-register/redirect?msg=" + msg;
    }

    /**
     * Created By IntelliJ IDEA
     * 异步请求获取邮箱验证码
     *
     * @param email
     * @return
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午8:43
     */
    @RequestMapping(value = "get-verifycode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getVerifycode(String email, HttpSession session) throws SQLException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String account = (String) session.getAttribute("account");
        //根据账号和邮箱 查看是否有匹配的记录
        boolean isExist = nUserService.isExist(account, email);

        if (isExist) {
            //前6位
            String verifycode = UUIDUtil.generateUid().substring(0, 6);
            //更改账号的邮箱验证码
            Integer row = nUserService.addVerifycode(account, verifycode);
            if (row == 1) {
                //另开线程 发送验证码
                EmailBean emailBean = new EmailBean(email, verifycode, 2);
                Thread thread = new Thread(emailBean);
                thread.start();

                map.put("msg", "success");
            }
        }
        return map;
    }

    /**
     * Created By IntelliJ IDEA
     * 重置密码第二步
     *
     * @param email      邮箱
     * @param verifycode 验证码
     * @return
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午11:26
     */
    @RequestMapping(value = "reset-step2", method = RequestMethod.POST)
    @Token(remove = true)
    public String checkEmailCode(String email, String verifycode, Model model, HttpSession session) {
        String msg = new String();
        String account = (String) session.getAttribute("account");
        boolean pass = nUserService.isVerifyPass(account, email, verifycode);
        //验证通过
        if (pass) {
            msg = "验证通过,跳转到第三步";
        } else {
            msg = "验证不通过,跳转回第二步";
        }
        return "forward:/new-register/redirect?msg=" + msg;
    }

    /**
     * Created By IntelliJ IDEA
     * 重置密码第三步
     *
     * @param password 密码
     * @return
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午11:27
     */
    @RequestMapping(value = "reset-step3", method = RequestMethod.POST)
    @Token(remove = true)
    public String changePassword(String password, Model model, HttpSession session) {
        String msg = new String();
        String account = (String) session.getAttribute("account");
        Integer row = nUserService.resetPassword(account, password);
        if (row == 1) {
            msg = "密码修改成功";
            model.addAttribute("msg", msg);
            return "foreground/n_user/reset-step4";
        } else {
            msg = "服务器忙,请稍后操作";
            model.addAttribute("msg", msg);
            return "foreground/n_user/msg";
        }
    }

    @RequestMapping(value = "testVerifycode", method = RequestMethod.POST)
    @ResponseBody
    public String testVerifycode(String verifycode, HttpSession session) {
        String sVerifycode = (String) session.getAttribute("verifycode");
        if (verifycode.equals(sVerifycode)) {
            return "T";
        }
        return null;
    }


}

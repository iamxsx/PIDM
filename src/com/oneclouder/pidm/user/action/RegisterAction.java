package com.oneclouder.pidm.user.action;

import com.oneclouder.pidm.user.annotation.Token;
import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.service.ICompanyEmployeeService;
import com.oneclouder.pidm.user.service.ICompanyService;
import com.oneclouder.pidm.user.service.IDescriptionService;
import com.oneclouder.pidm.user.service.IUserService;
import com.oneclouder.pidm.user.webBean.RegisterFormBean;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/10/16
 * Time: 3:51 PM
 */
@Controller
@RequestMapping("register")
public class RegisterAction {
    @Resource(name = "companyService")
    private ICompanyService companyService;

   /* @Resource(name = "associationService")
    private IAssociationService associationService;

    @Resource(name = "associationUnitService")
    private IAssociationUnitService associationUnitService;*/

    @Resource(name = "companyEmployeeService")
    private ICompanyEmployeeService companyEmployeeService;

    @Resource(name = "descriptionService")
    private IDescriptionService descriptionService;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name ="workFlowService")
    private IWorkFlowService workFlowService;

    @RequestMapping("goto-register")
    public String gotoRegister() {
        return "redirect:get-company";
    }

    @RequestMapping(value = "redirect")
    @Token(save = true)
    public ModelAndView redirect(String msg, ModelAndView modelAndView, HttpServletRequest request) {
        System.out.println(request.getSession(false).getAttribute("token"));
        if("验证码错误".equals(msg)) {
            modelAndView.setViewName("redirect:../index");
            return modelAndView;
        } else if("此用户邮箱已验证，请勿重复验证".equals(msg)) {
            modelAndView.setViewName("redirect:../index");
            return modelAndView;
        } else if("验证成功".equals(msg)) {
            modelAndView.setViewName("redirect:../index");
            return modelAndView;
        } else if("login".equals(msg)) {
            modelAndView.setViewName("/foreground/user/login");
            return modelAndView;
        } else if("login-error.jsp".equals(msg)) {
            modelAndView.addObject("msg","账号或密码不正确");
            modelAndView.setViewName("/foreground/user/login");
            return modelAndView;
        } else if("注册成功，请注意邮箱查收，激活帐号".equals(msg)) {
            modelAndView.setViewName("redirect:../index");
            return modelAndView;
        } else if ("forget".equals(msg)) {
            modelAndView.setViewName("/foreground/user/password-reset");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:../index");
        return modelAndView;
    }

    /**
     * 获取已注册公司
     * @param model
     * @return
     */
    @RequestMapping(value = "get-company", method = RequestMethod.GET)
    public String registeredCompanies(Model model) throws SQLException {
        List<Company> companies = new ArrayList<>();
        companies = companyService.findAllCompanies();
        model.addAttribute("companies", companies);
        return "foreground/user/register-step1";
    }

    @RequestMapping(value = "existCompany")
    @Token(save = true)
    public ModelAndView existCompany(Company company, ModelAndView modelAndView, HttpServletRequest request) throws SQLException {
//        System.out.println("1--" + request.getSession().getAttribute("token"));
        if (companyService.companyIsExist(company.getName())) {
            /*
            * traget:0表示新注册公司用户注册
            *        1表示已存在公司用户注册
            */
//            model.addAttribute("traget", 1);
            Company company1 = companyService.findCompanyByName(company.getName());
            modelAndView.addObject("company", company1);
        } else {
//            model.addAttribute("traget", 0);
            modelAndView.addObject("company", company);
        }
        modelAndView.setViewName("foreground/user/register-step2");
        return modelAndView;
    }

    @RequestMapping(value = "noExistAccount", method = RequestMethod.POST)
    @ResponseBody
    public String noExistAccount(@RequestParam String account) throws SQLException {
        boolean exist = userService.isExist(account);
        if(exist) {
            return "123";
        } else {
            return null;
        }
    }

    //已存在公司注册记得传id
    @Token(remove = true)
    @RequestMapping(value = "register-user", method = RequestMethod.POST)
    public String register(RegisterFormBean registerFormBean, HttpServletRequest request, Model model) throws SQLException {
//        System.out.println("hello");
//        return "";
//        System.out.println("2--" + request.getSession().getAttribute("token"));
        User user = registerFormBean.getUser();
        if (userService.isExist(user.getAccount())) {
            model.addAttribute("msg","请勿重复提交");
            return "foreground/user/redirect";
        }
        Integer userId = userService.register(registerFormBean, request.getSession());
        if (userId != null){
            String account = registerFormBean.getUser().getAccount();
            workFlowService.start(userId,account,"User","Insert");
            model.addAttribute("msg","注册成功，请注意邮箱查收，激活帐号");
        }
        return "foreground/user/redirect";
    }

    @RequestMapping(value = "active", method = RequestMethod.GET)
    public String active(String code, Model model) throws SQLException {
        Integer userId = userService.verifyCode(code);
        if (userId == null) {
            model.addAttribute("msg", "验证码错误");
        } else {
            if (userService.isActive(userId)) {
                model.addAttribute("msg", "此用户邮箱已验证，请勿重复验证");
            } else {
                //将用户激活
                userService.active(userId);
                model.addAttribute("msg", "验证成功");
            }
        }
        return "foreground/user/redirect";
    }


    // TODO: 9/16/16 代码有点多 有时间合并一下
    // TODO 参数要有公司id
}

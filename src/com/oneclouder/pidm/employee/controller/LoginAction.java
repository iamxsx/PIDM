package com.oneclouder.pidm.employee.controller;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.menu.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by PhychoLee on 9/18/16 8:31 AM.
 * Description:
 */
@Controller
@RequestMapping("")
public class LoginAction {

    @Autowired
    private IMenuService menuService;

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/back/login")
    public String login(HttpServletRequest request, HttpServletResponse response){


        if (request.getSession().getAttribute("PhoneMsgValidate")==null){
            request.getSession().setAttribute("PhoneMsgValidate", "");
        }

        //如果登陆了，将不能进入登陆页面
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return "/back/welcome";
        }

        //登陆失败的异常类名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (exceptionClassName != null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)){
                //发送错误信息
                System.out.println("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
                System.out.println("用户名或密码错误");
            } else if ("IncorrectCaptchaCode".equals(exceptionClassName)){
                System.out.println("验证码错误");
            } else{
                System.out.println("未知错误");
            }
        }

        return "/back/login";
    }

    /**
     * 获取后台登陆信息
     * @return
     */
    @RequestMapping("/back/getCurrentEmployee")
    @ResponseBody
    public String getCurrentEmployee(){

        //从shiro的session中获取登陆信息
        Subject subject = SecurityUtils.getSubject();

        Employee employee = (Employee) subject.getPrincipal();

        return employee.getRealName();
    }

    @RequestMapping("/back/logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        return "redirect:/back/login";
    }
}

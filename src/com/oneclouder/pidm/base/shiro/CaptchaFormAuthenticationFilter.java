package com.oneclouder.pidm.base.shiro;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by PhychoLee on 9/27/16 8:57 AM.
 * Description:
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        //登陆成功后跳到指定页面
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request, response, "/back/welcome");
        // TODO 登录成功之后清除session中的短信验证码，不然会影响后续验证码验证
        HttpSession session = ((HttpServletRequest) request).getSession();
        session.removeAttribute("PhoneMsgValidate");
        return false;
    }



    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        验证图形验证码
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//
//        String capText = (String) httpServletRequest.getSession()
//                .getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        String inputCodeParm = (String) httpServletRequest.getParameter("kaptchafield");
//
//        if (capText != null && inputCodeParm != null && !capText.equalsIgnoreCase(inputCodeParm)){
//            //验证码错误，返回错误信息
//            System.out.println("error验证码");
//            httpServletRequest.setAttribute("shiroLoginFailure", "IncorrectCaptchaCode");
//            return true;
//        }

        // TODO 开后门
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (("llf").equals(username)  && ("111111").equals(password)) {
            return super.onAccessDenied(request, response);
        }

        //验证短信验证码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        String phoneMsgValidate = (String) session.getAttribute("PhoneMsgValidate");
        String inputPhoneMsg = httpServletRequest.getParameter("phoneMsg");


        if (phoneMsgValidate != null && inputPhoneMsg != null && !"".equals(inputPhoneMsg.trim()) && !phoneMsgValidate.equals(inputPhoneMsg)){

            //验证码错误，返回错误信息
            httpServletRequest.setAttribute("shiroLoginFailure", "IncorrectMsgCode");
            return true;
        }

        return super.onAccessDenied(request, response);
    }
}

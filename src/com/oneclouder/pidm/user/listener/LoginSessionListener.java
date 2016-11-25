package com.oneclouder.pidm.user.listener;

import com.oneclouder.pidm.user.webBean.UserInfo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By IntelliJ IDEA
 * 用户登陆的session监听器
 *
 * @Author: AngryFeng
 * @Date: 16-10-8
 * @Time: 下午3:08
 */
public class LoginSessionListener implements HttpSessionListener {
    public LoginSessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("session销毁啦啦啦阿拉拉");
        HttpSession currSession = event.getSession();
        UserInfo userInfo = (UserInfo) currSession.getAttribute("userInfo");
        //用户有登陆 要将context域中的当前会话对应的session去掉
        if (userInfo != null) {
            ServletContext context = currSession.getServletContext();
            Map<String, HttpSession> userInfos = (HashMap<String, HttpSession>) context.getAttribute("userInfos");
            if (userInfos.containsKey(userInfo.getAccount())) {
                userInfos.remove(userInfo.getAccount());
            }
        }
    }
}

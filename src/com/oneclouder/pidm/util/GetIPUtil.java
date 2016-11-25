package com.oneclouder.pidm.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By IntelliJ IDEA
 * 获取用户的真是ip
 * @Author: AngryFeng
 * @Date: 16-10-17
 * @Time: 上午10:11
 */
public class GetIPUtil {
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}


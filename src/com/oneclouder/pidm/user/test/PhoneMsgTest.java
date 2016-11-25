package com.oneclouder.pidm.user.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by clouder on 16-9-29.
 */
public class PhoneMsgTest {
    @Test
    public void testSendMsg() throws Exception{
        //发送内容
        String content = "你的验证码是：1234";

        String key = "0CAADF35400FACE564C69D40D76080EB";

        // 创建StringBuffer对象用来操作字符串
//        http://api.cnsms.cn/?
        StringBuffer sb = new StringBuffer("http://api.cnsms.cn/?");

        // 向StringBuffer追加用户名
        sb.append("ac=send&uid=112246");
//        sb.append("ac=send&uid=9999");

        // 向StringBuffer追加密码（密码采用MD5 32位 小写）
        sb.append("&pwd=25d55ad283aa400af464c76d713c07ad");

        // 向StringBuffer追加手机号码
        sb.append("&mobile=18813973767,18813973403");

        // 向StringBuffer追加消息内容转URL标准码
        sb.append("&content="+ URLEncoder.encode(content));

        sb.append("&encode=utf8");

        // 创建url对象
        URL url = new URL(sb.toString());

        // 打开url连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置url请求方式 ‘get’ 或者 ‘post’
        connection.setRequestMethod("POST");

        // 发送
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        // 返回发送结果
        String inputline = in.readLine();

        // 返回结果为‘100’ 发送成功
        System.out.println(inputline);
    }
}

package com.oneclouder.pidm.util;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


//发邮件的工具类
public class MailUtil {


    public static String NAME;

    public static String PASSWORD;

    public static String HOSTIP;

    public static String PORT;

    public static String MSH;

    private static Properties ini;

    static {
        try {
            ini = new Properties();
            InputStream fis =MailUtil.class.getResourceAsStream("/email.properties");
            ini.load(fis);
            NAME = ini.getProperty("NAME");
            PASSWORD = ini.getProperty("PASSWORD");
            HOSTIP = ini.getProperty("HOSTIP");
            PORT = ini.getProperty("PORT");
            MSH = ini.getProperty("mail.smtp.host");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送注册激活码
     * @param to
     * @param code
     */
    public static void sendMail(String to, String code) {
        /*
         * 1.获得一个Session对象
		 * 2.创建一个代表邮件的Message
		 * 3.发送邮件Transport
		 * 
		 * */
        //获得连接对象
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", MSH);
        props.put("mail.smtp.auth", true);
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        //创建一个Message
        Message message = new MimeMessage(mailSession);
        try {
            //设置发件人
            message.setFrom(new InternetAddress(NAME));
            //设置收件人
            message.setRecipient(RecipientType.TO, new InternetAddress(to));
            //抄送CC 密送BCC
            //设置标题
            message.setSubject("欢迎注册 广东价格和产业发展协会");
            //设置邮箱正文
            //TODO need to change the link
            message.setContent(
                    "你好，你已经成功注册为广东价格和产业发展协会会员，请点击下方链接激活帐号：<br>"
                            + "<a href='http://"+HOSTIP+":"+PORT+"/new-register/active?code=" + code + "'>"
                            + "激活"
                            + "</a>",
                    "text/html;charset=UTF-8");
            message.saveChanges();
            //发送邮件
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(MSH, NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送密码修改验证码
     * @param to
     * @param code
     */
    public static void sendVerifycode(String to, String code) {
		/*
		 * 1.获得一个Session对象
		 * 2.创建一个代表邮件的Message
		 * 3.发送邮件Transport
		 *
		 * */
        //获得连接对象
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", MSH);
        props.put("mail.smtp.auth", true);
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        //创建一个Message
        Message message = new MimeMessage(mailSession);
        try {
            //设置发件人
            message.setFrom(new InternetAddress(NAME));
            //设置收件人
            message.setRecipient(RecipientType.TO, new InternetAddress(to));
            //抄送CC 密送BCC
            //设置标题
            message.setSubject("『广东价格和产业发展协会』");
            //设置邮箱正文
            //TODO need to change the link
            message.setContent("验证码: " + code + " ,广东价格和产业发展协会用户，您正在进行通过邮箱找回密码操作(验证码告知他人将导致账户被盗，请勿泄露)", "text/plain;charset=UTF-8");
            message.saveChanges();
            //发送邮件
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(MSH, NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}

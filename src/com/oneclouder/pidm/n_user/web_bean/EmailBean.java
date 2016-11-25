package com.oneclouder.pidm.n_user.web_bean;

import com.oneclouder.pidm.util.MailUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created By IntelliJ IDEA
 * 发送邮件的线程bean
 * @Author: AngryFeng
 * @Date: 16-10-16
 * @Time: 下午4:58
 */
public class EmailBean implements Runnable {
    private String email;
    private String verifycode;
    /**
     * 1：发送注册邮件 2：发送修改密码邮件
     */
    private Integer target;

    public EmailBean() {
    }

    public EmailBean(String email, String verifycode, Integer target) {
        this.email = email;
        this.verifycode = verifycode;
        this.target = target;
    }

    @Override
    public void run() {
        //两个属性不为空 发送验证码
        if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(verifycode)) {
            if (target == 1) {
                MailUtil.sendMail(email, verifycode);
            } else if (target == 2) {
                MailUtil.sendVerifycode(email, verifycode);
            }
            return;
        } else {
            return;
        }
    }
}

package com.oneclouder.pidm.user.webBean;

import java.util.Date;

/**
 * Created By IntelliJ IDEA
 * 记录用户登陆后的信息
 *
 * @Author: AngryFeng
 * @Date: 16-10-8
 * @Time: 下午2:55
 */
public class UserInfo {
    private String realName;
    private String account;
    private Integer id;
    private String ip;
    private Date loginDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}

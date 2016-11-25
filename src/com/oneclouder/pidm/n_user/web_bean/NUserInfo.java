package com.oneclouder.pidm.n_user.web_bean;

import java.util.Date;

/**
 * Created By IntelliJ IDEA
 * 用户登录后 将必要信息存放于session的bean
 * @Author: AngryFeng
 * @Date: 16-10-17
 * @Time: 上午10:07
 */
public class NUserInfo {
    private String realName;
    private String account;
    private Integer id;
    private Integer userRole;
    private String ip;
    private Date loginDate;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "NUserInfo{" +
                "realName='" + realName + '\'' +
                ", account='" + account + '\'' +
                ", id=" + id +
                ", ip='" + ip + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}

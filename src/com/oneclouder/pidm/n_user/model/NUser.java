package com.oneclouder.pidm.n_user.model;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:15
 */
public class NUser {
    private Integer id;
    //账号
    private String account;
    //密码
    private String password;
    //密码的盐
    private String salt;
    //手机号
    private String phoneNum;
    //身份证号
    private String IDcard;
    //姓名
    private String realName;
    //邮箱
    private String email;
    //邮箱验证码
    private String verifycode;
    //账号状态
    private Integer status;
    //用户角色
    private Integer userRole;
    //所属公司
    private NCompany company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public NCompany getCompany() {
        return company;
    }

    public void setCompany(NCompany company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "NUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", IDcard='" + IDcard + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", verifycode='" + verifycode + '\'' +
                ", status=" + status +
                ", company=" + company +
                '}';
    }
}

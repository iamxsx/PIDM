package com.oneclouder.pidm.n_user.model;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:33
 */
public class NCompanyEmployee {
    private Integer id;
    //单位名称
    private String name;
    //单位职务
    private String jobPosition;
    //协会职务
    private String asctJobPosition;
    //分会职务
    private String chapterJobPosition;
    //邮箱
    private String email;
    //办公电话
    private String officePhoneNum;
    //手机号码
    private String cellPhoneNum;
    //传真号码
    private String faxNum;
    //微信号/QQ号
    private String onlineNum;
    //员工类型：0指定联系人 1推荐人 2法人
    private Integer nature;
    //所属企业
    private NCompany company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getAsctJobPosition() {
        return asctJobPosition;
    }

    public void setAsctJobPosition(String asctJobPosition) {
        this.asctJobPosition = asctJobPosition;
    }

    public String getChapterJobPosition() {
        return chapterJobPosition;
    }

    public void setChapterJobPosition(String chapterJobPosition) {
        this.chapterJobPosition = chapterJobPosition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficePhoneNum() {
        return officePhoneNum;
    }

    public void setOfficePhoneNum(String officePhoneNum) {
        this.officePhoneNum = officePhoneNum;
    }

    public String getCellPhoneNum() {
        return cellPhoneNum;
    }

    public void setCellPhoneNum(String cellPhoneNum) {
        this.cellPhoneNum = cellPhoneNum;
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    public String getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(String onlineNum) {
        this.onlineNum = onlineNum;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public NCompany getCompany() {
        return company;
    }

    public void setCompany(NCompany company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "NCompanyEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", asctJobPosition='" + asctJobPosition + '\'' +
                ", chapterJobPosition='" + chapterJobPosition + '\'' +
                ", email='" + email + '\'' +
                ", officePhoneNum='" + officePhoneNum + '\'' +
                ", cellPhoneNum='" + cellPhoneNum + '\'' +
                ", faxNum='" + faxNum + '\'' +
                ", onlineNum='" + onlineNum + '\'' +
                ", nature=" + nature +
                ", company=" + company +
                '}';
    }
}

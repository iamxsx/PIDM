package com.oneclouder.pidm.n_user.model;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:18
 */
public class NCompany {
    private Integer id;
    //单位编号
    private String identifier;
    //单位名称
    private String name;
    //单位性质
    private String nature;
    private String address;
    //邮编
    private String zipCode;
    /**
     * 注册时间(存放格式为 "2016-10-14" 的字符串)
     */
    private String registerTime;
    //注册的协会名称
    private String associationName;
    //注册的协会单位名称
    private String associationUnit;
    //注册的分会名称
    private String chapterName;
    //注册的分会单位名称
    private String chapterUnit;
    //单位简介
    private String introduction;
    //入会所需要的帮助
    private String demand;
    //审批意见
    private String auditOpinion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getAssociationUnit() {
        return associationUnit;
    }

    public void setAssociationUnit(String associationUnit) {
        this.associationUnit = associationUnit;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterUnit() {
        return chapterUnit;
    }

    public void setChapterUnit(String chapterUnit) {
        this.chapterUnit = chapterUnit;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    @Override
    public String toString() {
        return "NCompany{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", nature='" + nature + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", associationName='" + associationName + '\'' +
                ", associationUnit='" + associationUnit + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", chapterUnit='" + chapterUnit + '\'' +
                ", introduction='" + introduction + '\'' +
                ", demand='" + demand + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                '}';
    }
}

package com.oneclouder.pidm.user.model;

/**
 * Created by clouder on 9/10/16.
 */
public class UserTemporary {
    private Integer id;

    private String account;

    private String password;

    private String phoneNum;

    private String IDcard;

    private String realName;

    private String email;

    /**
     * 注册审核状态
     */
    private Integer status;

    private Integer associationId;

    /**
     * 指定联系人
     */
    private CompanyEmployee designatedContact;

    /**
     * 区别 会员/非会员 状态
     */
    private Integer registerStatus;

    private String jobPosition;

    private Company company;

    /**
     * 是否是常用联系人(后台)
     */
    private Integer frequentContact;

    /**
     * 所加协会单位
     */
    private AssociationUnit associationUnit;

    /**
     * 审批人
     */
    private String approver;

    // TODO 不知道改不改成实体类
    /**
     * 企业协会代表人(与企业协会代表中间表关联)
     */
//    private CompanyEmployee cpnyAcstRep;

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
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AssociationUnit getAssociationUnit() {
        return associationUnit;
    }

    public void setAssociationUnit(AssociationUnit associationUnit) {
        this.associationUnit = associationUnit;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition == null ? null : jobPosition.trim();
    }

    public Integer getFrequentContact() {
        return frequentContact;
    }

    public void setFrequentContact(Integer frequentContact) {
        this.frequentContact = frequentContact;
    }

    public CompanyEmployee getDesignatedContact() {
        return designatedContact;
    }

    public void setDesignatedContact(CompanyEmployee designatedContact) {
        this.designatedContact = designatedContact;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    /* public CompanyEmployee getCpnyAcstRep() {
        return cpnyAcstRep;
    }

    public void setCpnyAcstRep(CompanyEmployee cpnyAcstRep) {
        this.cpnyAcstRep = cpnyAcstRep;
    }*/
    // TODO mapper.xml文件最后要统一添加映射
}

package com.oneclouder.model;

public class TUser {
    private Integer id;

    private String account;

    private String password;

    private String phoneNum;

    private String idcart;

    private String realName;

    private String email;

    private Integer status;

    private String associationId;

    private Integer designatedContact;

    private Integer registerStatus;

    private String jobPosition;

    private Integer companyId;

    private Integer frequentContact;

    private Integer asctUnitId;

    private Integer cpnyAcstRepId;

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

    public String getIdcart() {
        return idcart;
    }

    public void setIdcart(String idcart) {
        this.idcart = idcart == null ? null : idcart.trim();
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

    public String getAssociationId() {
        return associationId;
    }

    public void setAssociationId(String associationId) {
        this.associationId = associationId == null ? null : associationId.trim();
    }

    public Integer getDesignatedContact() {
        return designatedContact;
    }

    public void setDesignatedContact(Integer designatedContact) {
        this.designatedContact = designatedContact;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getFrequentContact() {
        return frequentContact;
    }

    public void setFrequentContact(Integer frequentContact) {
        this.frequentContact = frequentContact;
    }

    public Integer getAsctUnitId() {
        return asctUnitId;
    }

    public void setAsctUnitId(Integer asctUnitId) {
        this.asctUnitId = asctUnitId;
    }

    public Integer getCpnyAcstRepId() {
        return cpnyAcstRepId;
    }

    public void setCpnyAcstRepId(Integer cpnyAcstRepId) {
        this.cpnyAcstRepId = cpnyAcstRepId;
    }
}
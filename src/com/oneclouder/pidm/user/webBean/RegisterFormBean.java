package com.oneclouder.pidm.user.webBean;

import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import com.oneclouder.pidm.user.model.Description;
import com.oneclouder.pidm.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装注册参数
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/12/16
 * Time: 9:09 AM
 */
public class RegisterFormBean {
    private User user;
    private Company company;
    private Description description;
    /**
     * 法人代表(company中的属性)
     */
    private CompanyEmployee legalRep;
    /**
     * 担任协会职务人选
     */
    private List<CompanyEmployee> introduceds = new ArrayList<CompanyEmployee>();
    /**
     * 指定联系人(user中的属性)
     */
    private CompanyEmployee designatedContact;
    /**
     * 单位代表人
     */
    private CompanyEmployee cpnyAcstRep;

    private Integer associationId;

    private Integer associationUnitId;
    /**
     * 判断是否是后台添加客户
     */
    private Integer  backClient;

    public Integer getBackClient() {
        return backClient;
    }

    public void setBackClient(Integer backClient) {
        this.backClient = backClient;
    }



    public RegisterFormBean() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public CompanyEmployee getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(CompanyEmployee legalRep) {
        this.legalRep = legalRep;
    }

    public List<CompanyEmployee> getIntroduceds() {
        return introduceds;
    }

    public void setIntroduceds(List<CompanyEmployee> introduceds) {
        this.introduceds = introduceds;
    }

    public CompanyEmployee getDesignatedContact() {
        return designatedContact;
    }

    public void setDesignatedContact(CompanyEmployee designatedContact) {
        this.designatedContact = designatedContact;
    }

    public CompanyEmployee getCpnyAcstRep() {
        return cpnyAcstRep;
    }

    public void setCpnyAcstRep(CompanyEmployee cpnyAcstRep) {
        this.cpnyAcstRep = cpnyAcstRep;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public Integer getAssociationUnitId() {
        return associationUnitId;
    }

    public void setAssociationUnitId(Integer associationUnitId) {
        this.associationUnitId = associationUnitId;
    }
}

package com.oneclouder.pidm.user.webBean;

import com.oneclouder.pidm.user.model.*;

import java.util.List;

/**
 * Created by clouder on 9/22/16.
 */
public class CheckUser {

    private AssociationUnit associationUnit;
    private List<CompanyEmployee> introduced;
    private CompanyEmployee designatedContact;
    private UserTemporary user;
    private Company company;
    private Description description;
    private Association association;
    private CompanyEmployee companyEmployee_legal;
    private CompanyEmployee companyEmployee_rep;

    public UserTemporary getUser() {
        return user;
    }

    public void setUser(UserTemporary user) {
        this.user = user;
    }

    public AssociationUnit getAssociationUnit() {
        return associationUnit;
    }

    public void setAssociationUnit(AssociationUnit associationUnit) {
        this.associationUnit = associationUnit;
    }

    public List<CompanyEmployee> getIntroduced() {
        return introduced;
    }

    public void setIntroduced(List<CompanyEmployee> introduced) {
        this.introduced = introduced;
    }

    public CompanyEmployee getDesignatedContact() {
        return designatedContact;
    }

    public void setDesignatedContact(CompanyEmployee designatedContact) {
        this.designatedContact = designatedContact;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

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

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public CompanyEmployee getCompanyEmployee_legal() {
        return companyEmployee_legal;
    }

    public void setCompanyEmployee_legal(CompanyEmployee companyEmployee_legal) {
        this.companyEmployee_legal = companyEmployee_legal;
    }

    public CompanyEmployee getCompanyEmployee_rep() {
        return companyEmployee_rep;
    }

    public void setCompanyEmployee_rep(CompanyEmployee companyEmployee_rep) {
        this.companyEmployee_rep = companyEmployee_rep;
    }

    @Override
    public String toString() {
        return "CheckUser{" +
                "associationUnit=" + associationUnit +
                ", introduced=" + introduced +
                ", designatedContact=" + designatedContact +
                ", user=" + user +
                ", company=" + company +
                ", description=" + description +
                ", association=" + association +
                ", companyEmployee_legal=" + companyEmployee_legal +
                ", companyEmployee_rep=" + companyEmployee_rep +
                '}';
    }
}

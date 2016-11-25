package com.oneclouder.pidm.user.model;

/**
 * Created by clouder on 9/10/16.
 */
public class CompanyEmployee {
    private Integer id;

    private String name;

    private String jobPosition;

    private String officePhoneNum;

    private String cellPhoneNum;

    private String faxNum;

    private String email;

    private Company company;

    private String asctJobPosition;

    private Integer nature;
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
        this.name = name == null ? null : name.trim();
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition == null ? null : jobPosition.trim();
    }

    public String getOfficePhoneNum() {
        return officePhoneNum;
    }

    public void setOfficePhoneNum(String officePhoneNum) {
        this.officePhoneNum = officePhoneNum == null ? null : officePhoneNum.trim();
    }

    public String getCellPhoneNum() {
        return cellPhoneNum;
    }

    public void setCellPhoneNum(String cellPhoneNum) {
        this.cellPhoneNum = cellPhoneNum == null ? null : cellPhoneNum.trim();
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum == null ? null : faxNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAsctJobPosition() {
        return asctJobPosition;
    }

    public void setAsctJobPosition(String asctJobPosition) {
        this.asctJobPosition = asctJobPosition == null ? null : asctJobPosition.trim();
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    @Override
    public String toString() {
        return "CompanyEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", officePhoneNum='" + officePhoneNum + '\'' +
                ", cellPhoneNum='" + cellPhoneNum + '\'' +
                ", faxNum='" + faxNum + '\'' +
                ", email='" + email + '\'' +
                ", company=" + company +
                ", asctJobPosition='" + asctJobPosition + '\'' +
                ", nature=" + nature +
                '}';
    }
}

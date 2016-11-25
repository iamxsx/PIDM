package com.oneclouder.model;

public class TCompanyEmployee {
    private Integer id;

    private String name;

    private String jobPosition;

    private String officePhoneNum;

    private String cellPhoneNum;

    private String faxNum;

    private Integer companyId;

    private String asctJobPosition;

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAsctJobPosition() {
        return asctJobPosition;
    }

    public void setAsctJobPosition(String asctJobPosition) {
        this.asctJobPosition = asctJobPosition == null ? null : asctJobPosition.trim();
    }
}
package com.oneclouder.pidm.t_user.model;

public class Company_t {
    private Integer id;

    private String identifier;

    private String name;

    /**
     * 企业性质
     */
    private String nature;

    private String city;

    private String county;

    private String address;

    private String zipCode;

    /**
     * 公司网址
     */
    private String internetSite;

    /**
     * 传真
     */
    private String faxNum;

    /**
     * 注册性质
     */
    private String registerNature;

    /**
     * 所在行业
     */
    private String industry;

    private String adminDepartment;

    private Integer employeeNum;

    private String email;

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
        this.name = name == null ? null : name.trim();
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getInternetSite() {
        return internetSite;
    }

    public void setInternetSite(String internetSite) {
        this.internetSite = internetSite == null ? null : internetSite.trim();
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum == null ? null : faxNum.trim();
    }

    public String getRegisterNature() {
        return registerNature;
    }

    public void setRegisterNature(String registerNature) {
        this.registerNature = registerNature == null ? null : registerNature.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getAdminDepartment() {
        return adminDepartment;
    }

    public void setAdminDepartment(String adminDepartment) {
        this.adminDepartment = adminDepartment == null ? null : adminDepartment.trim();
    }

    public Integer getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", nature='" + nature + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", internetSite='" + internetSite + '\'' +
                ", faxNum='" + faxNum + '\'' +
                ", registerNature='" + registerNature + '\'' +
                ", industry='" + industry + '\'' +
                ", adminDepartment='" + adminDepartment + '\'' +
                ", employeeNum=" + employeeNum +
                ", email='" + email + '\'' +
                '}';
    }
}
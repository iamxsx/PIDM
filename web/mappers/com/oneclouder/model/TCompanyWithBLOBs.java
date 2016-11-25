package com.oneclouder.model;

public class TCompanyWithBLOBs extends TCompany {
    private String description1;

    private String description2;

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1 == null ? null : description1.trim();
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2 == null ? null : description2.trim();
    }
}
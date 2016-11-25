package com.oneclouder.pidm.user.model;

/**
 * Created by clouder on 9/10/16.
 */
public class Description {
    private Integer id;

    private Integer companyId;

    private Integer associationId;

    private String description1;

    private String description2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", associationId=" + associationId +
                ", description1='" + description1 + '\'' +
                ", description2='" + description2 + '\'' +
                '}';
    }
}

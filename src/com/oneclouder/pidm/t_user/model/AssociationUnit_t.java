package com.oneclouder.pidm.t_user.model;

import com.oneclouder.pidm.t_user.model.Association_t;

public class AssociationUnit_t {
    private Integer id;

    private Association_t association;

    private String name;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Association_t getAssociation() {
        return association;
    }

    public void setAssociation(Association_t association) {
        this.association = association;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
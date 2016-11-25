package com.oneclouder.pidm.employee.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhychoLee on 9/10/16 9:21 AM.
 * Description:部门实体类
 */
public class Department {
    private Integer id;

    private String code;

    private String name;

    private String description;

    private Integer parentId;

    private List<Department> childList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Department> getChildList() {
        return childList;
    }

    public void setChildList(List<Department> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                ", childList=" + childList +
                '}';
    }
}
package com.oneclouder.pidm.employee.model;

import com.oneclouder.pidm.menu.model.Menu;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PhychoLee on 9/10/16 9:21 AM.
 * Description:员工实体类
 */
public class Employee implements Serializable{
    public final static Integer GENDER_FEMALE = 0;
    public final static Integer GENDER_MALE = 1;

    public final static Integer JOBSTATUS_ACTIVE = 1;
    public final static Integer JOBSTATUS_LEFT = 2;
    public final static Integer JOBSTATUS_SUSPEND = 3;
    public final static Integer JOBSTATUS_RETIRED = 4;



    private Integer id;

    private String userName;

    private String password;

    private String employeeNum;

    private Integer jobStatus;

    private Integer gender;

    private Integer jobPositionId;

    private String cellPhoneNum;

    private String officePhoneNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String qq;

    private String email;

    private String address;

    private String realName;

    private String salt;

    private String jobPositionName;

    private String departmentName;

    private Integer departmentId;

    private String roleIds;

    private String phoneMsg;

    private List<Menu> menuList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum == null ? null : employeeNum.trim();
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(Integer jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public String getCellPhoneNum() {
        return cellPhoneNum;
    }

    public void setCellPhoneNum(String cellPhoneNum) {
        this.cellPhoneNum = cellPhoneNum == null ? null : cellPhoneNum.trim();
    }

    public String getOfficePhoneNum() {
        return officePhoneNum;
    }

    public void setOfficePhoneNum(String officePhoneNum) {
        this.officePhoneNum = officePhoneNum == null ? null : officePhoneNum.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getJobPositionName() {
        return jobPositionName;
    }

    public void setJobPositionName(String jobPositionName) {
        this.jobPositionName = jobPositionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getPhoneMsg() {
        return phoneMsg;
    }

    public void setPhoneMsg(String phoneMsg) {
        this.phoneMsg = phoneMsg;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", employeeNum='" + employeeNum + '\'' +
                ", jobStatus=" + jobStatus +
                ", gender=" + gender +
                ", jobPositionId=" + jobPositionId +
                ", cellPhoneNum='" + cellPhoneNum + '\'' +
                ", officePhoneNum='" + officePhoneNum + '\'' +
                ", birthday=" + birthday +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", realName='" + realName + '\'' +
                ", salt='" + salt + '\'' +
                ", jobPositionName='" + jobPositionName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentId=" + departmentId +
                ", roleIds='" + roleIds + '\'' +
                ", menuList=" + menuList +
                '}';
    }
}
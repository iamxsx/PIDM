package com.oneclouder.pidm.user.webBean;

/**客户部分信息封装类
 * Created by clouder on 9/10/16.
 */
public class UserSimpleInfo {
    private Integer id;
    private String account;
    private String compayName;
    private String address;
    private String desContactName;
    private String phoneNum;
    private String faxNum;
    private String email;
    private String keyWord;             //关键字
    private Integer searchCondition;   //检索字段
    private Integer limitFrom;         //从哪条记录开始查询
    private Integer limitNumber;      //每页显示到记录条数
    private Integer recordNumber;       //总记录条数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Integer recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getDesContactName() {
        return desContactName;
    }

    public void setDesContactName(String desContactName) {
        this.desContactName = desContactName;
    }

    public Integer getLimitFrom() {
        return limitFrom;
    }

    public void setLimitFrom(Integer limitFrom) {
        this.limitFrom = limitFrom;
    }

    public String getCompayName() {
        return compayName;
    }

    public void setCompayName(String compayName) {
        this.compayName = compayName;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }


    public Integer getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(Integer searchCondition) {
        this.searchCondition = searchCondition;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

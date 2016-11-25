package com.oneclouder.pidm.n_user.web_bean;

import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.model.NUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By IntelliJ IDEA
 * 封装注册数据的pojo
 *
 * @Author: AngryFeng
 * @Date: 16-10-16
 * @Time: 上午10:44
 */
public class RegisterFormBean {
    private NUser user;
    private NCompany company;
    /**
     * 法人代表
     */
    private NCompanyEmployee legalRep;
    /**
     * 推荐人
     */
    private List<NCompanyEmployee> introduceds = new ArrayList<NCompanyEmployee>();
    /**
     * 指定联系人
     */
    private List<NCompanyEmployee> designatedContacts = new ArrayList<NCompanyEmployee>();

    /**
     * 文件路径
     */
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public NUser getUser() {
        return user;
    }

    public void setUser(NUser user) {
        this.user = user;
    }

    public NCompany getCompany() {
        return company;
    }

    public void setCompany(NCompany company) {
        this.company = company;
    }

    public NCompanyEmployee getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(NCompanyEmployee legalRep) {
        this.legalRep = legalRep;
    }

    public List<NCompanyEmployee> getIntroduceds() {
        return introduceds;
    }

    public void setIntroduceds(List<NCompanyEmployee> introduceds) {
        this.introduceds = introduceds;
    }

    public List<NCompanyEmployee> getDesignatedContacts() {
        return designatedContacts;
    }

    public void setDesignatedContacts(List<NCompanyEmployee> designatedContacts) {
        this.designatedContacts = designatedContacts;
    }

    @Override
    public String toString() {
        return "RegisterFormBean{" +
                "user=" + user +
                ", company=" + company +
                ", legalRep=" + legalRep +
                ", introduceds=" + introduceds +
                ", designatedContacts=" + designatedContacts +
                '}';
    }
}

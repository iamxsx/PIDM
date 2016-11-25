package com.oneclouder.pidm.test;

import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import com.oneclouder.pidm.user.service.impl.CompanyServiceImpl;
import com.oneclouder.pidm.user.webBean.RegisterFormBean;
import com.oneclouder.pidm.util.RegisterEntity;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/13/16
 * Time: 4:36 PM
 */
public class RegisterEntityTest {
    @Test
    public void testRegister1() throws  Exception {
        //模拟前台数据
        CompanyEmployee companyEmployee = new CompanyEmployee();
        Company company = new Company();
        company.setId(1);
        company.setName("宝力华电力有限公司");
        company.setIdentifier(CompanyServiceImpl.genCompanyIdentifiter(company.getName()));
        Integer nature = 1;
        companyEmployee.setCompany(company);
        companyEmployee.setNature(nature);

        RegisterFormBean formBean = new RegisterFormBean();
        formBean.setLegalRep(companyEmployee);


        //模拟后台封装的实体
        CompanyEmployee companyEmployee1 = new CompanyEmployee();
        companyEmployee1 = (CompanyEmployee) RegisterEntity.register(companyEmployee1, formBean.getLegalRep());
        System.out.println(companyEmployee1.toString());
    }

    @Test
    public void testRegister2 () throws Exception {
        NUserInfo userInfo = new NUserInfo();
        userInfo.setIp("192.168.31.222");
        userInfo.setLoginDate(new Date());
        NUser user = new NUser();
        user.setId(22);
        user.setAccount("angryfeng");
        user.setPassword("12345678");
        user.setRealName("马云");
        userInfo = (NUserInfo) RegisterEntity.register(userInfo, user);
        System.out.println(userInfo);
    }
}

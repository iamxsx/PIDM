package com.oneclouder.pidm.n_user.test;

import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午10:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestNCompanyService {
    //--------------------------------成员变量----------------------------------
    @Resource(name = "nCompanyService")
    INCompanyService nCompanyService;

    //--------------------------------hxj----------------------------------



    //--------------------------------zcb----------------------------------



    //--------------------------------zjf----------------------------------
    @Test
    public void testFindById () throws Exception {
        NCompany nCompany = nCompanyService.findById(1);
        System.out.println(nCompany);
    }

    @Test
    public void testIsExist () throws Exception {
        boolean exist = nCompanyService.isExist("广州亦云");
        System.out.println(exist);
    }

    @Test
    public void testCreatCompany() throws Exception {
        NCompany company = new NCompany();
        company.setName("阿里巴巴");
        company.setNature("股份有限公司");
        company.setAddress("浙江杭州的一条大街上");
        company.setZipCode("569874");
        company.setAssociationName("广东价格和产业发展协会");
        company.setAssociationUnit("副会长单位");
        company.setChapterName("价格与公平竞争分会");
        company.setChapterUnit("会长单位");
        company.setIntroduction("全中国人都喜欢的协会");
        company.setDemand("赖啊赖啊，过赖玩啊");
        nCompanyService.creatCompany(company);
        System.out.println(company.getId());
    }


}

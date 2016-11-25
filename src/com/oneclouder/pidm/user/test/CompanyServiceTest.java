package com.oneclouder.pidm.user.test;

import com.itextpdf.text.ExceptionConverter;
import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.service.ICompanyService;
import com.oneclouder.pidm.user.service.impl.CompanyServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/12/16
 * Time: 8:15 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CompanyServiceTest {
    @Resource(name = "companyService")
    private ICompanyService companyService;

    @Test
    public void testAccountCompanyByName() throws Exception {
        boolean isExist = companyService.companyIsExist("恒大地产");
        Company company = companyService.findById(3);
        System.out.println(isExist);
    }

    @Test
    public void testInsert() throws Exception {
        Company company = new Company();
        company.setName("宝力华电力有限公司");
        company.setIdentifier(CompanyServiceImpl.genCompanyIdentifiter(company.getName()));
        companyService.insert(company);
        System.out.println(company.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        Company company = companyService.findById(16);
        System.out.println("ID wei:"+company.getId());
//        Company company = new Company();
//        company.setId(22);
//        company.setName("腾讯家族");
//        company.setEmail("tengxun@.qq.com");
//        company.setFaxNum("8008208820");
//        companyService.update(company);

    }
}

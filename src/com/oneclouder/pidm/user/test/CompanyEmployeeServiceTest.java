package com.oneclouder.pidm.user.test;

import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import com.oneclouder.pidm.user.service.ICompanyEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/13/16
 * Time: 3:12 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CompanyEmployeeServiceTest {
    @Resource(name = "companyEmployeeService")
    private ICompanyEmployeeService companyEmployeeService;

    @Test
    public void testInsert() throws Exception {
        Company company = new Company();
        company.setId(3);
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setCompany(company);
        companyEmployee.setName("许灿斌");
        companyEmployee.setJobPosition("信息部员工");
        companyEmployee.setCellPhoneNum("18813973403");
        companyEmployee.setNature(0);
        companyEmployeeService.insert(companyEmployee);
        System.out.println(companyEmployee.getId());
    }

    @Test
    public void testAddCpnyAcstRep() throws Exception {
        CompanyEmployee employee = new CompanyEmployee();
        employee.setName("郑创标");
        employee.setJobPosition("信息部员工");
        employee.setCellPhoneNum("18813973403");
        System.out.println(companyEmployeeService.addCpnyAcstRep(employee,1,3));
    }
    //更新公司员工 法定 指定
    @Test
    public void testUpdate() throws Exception{
//        CompanyEmployee employee1 = companyEmployeeService.findById(251);
//        System.out.println("名字为"+employee1.getName());
        CompanyEmployee employee = new CompanyEmployee();
        employee.setId(251);
        employee.setName("更改的名字");
        employee.setJobPosition("董事长");
        employee.setOfficePhoneNum("8008208820");
        employee.setNature(0);
        employee.setCellPhoneNum("8008208820");

        Company company = new Company();
        company.setId(5);
        employee.setCompany(company);

        companyEmployeeService.update(employee);

    }
}

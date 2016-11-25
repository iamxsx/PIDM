package com.oneclouder.pidm.n_user.test;

import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.service.INCompanyEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午10:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestNCompanyEmployeeService {
    //--------------------------------成员变量----------------------------------
    @Resource(name = "nCompanyEmployeeService")
    INCompanyEmployeeService nCompanyEmployeeService;

    //--------------------------------hxj----------------------------------



    //--------------------------------zcb----------------------------------



    //--------------------------------zjf----------------------------------

    @Test
    public void testFindById () throws Exception {
        NCompanyEmployee nCompanyEmployee = nCompanyEmployeeService.findById(1);
        System.out.println(nCompanyEmployee);
    }

    @Test
    public void testCreatCompanyEmployee () throws Exception {
        NCompany company = new NCompany();
        company.setId(1);
        NCompanyEmployee employee = new NCompanyEmployee();
        employee.setName("马云");
        employee.setJobPosition("总裁");
        employee.setAsctJobPosition("副会长");
        employee.setChapterJobPosition("会长");
        employee.setEmail("angryfeng@163.com");
        employee.setOfficePhoneNum("020-6987459");
        employee.setCellPhoneNum("18813973767");
        employee.setFaxNum("020-5689751");
        employee.setOnlineNum("553783307");
        employee.setNature(2);
        nCompanyEmployeeService.creatCompanyEmployee(company.getId(), employee, 2);
        System.out.println(employee.getId());
    }

    @Test
    public void testCreatCompanyEmployees () throws Exception {
        NCompany company = new NCompany();
        company.setId(1);
        NCompanyEmployee employee1 = new NCompanyEmployee();
        employee1.setName("李彦宏");
        employee1.setJobPosition("总裁");
        employee1.setAsctJobPosition("副会长");
        employee1.setChapterJobPosition("会长");
        employee1.setEmail("angryfeng@163.com");
        employee1.setOfficePhoneNum("020-6987459");
        employee1.setCellPhoneNum("18813973767");
        employee1.setFaxNum("020-5689751");
        employee1.setOnlineNum("553783307");
        employee1.setNature(2);
        NCompanyEmployee employee2 = new NCompanyEmployee();
        employee2.setName("马化腾");
        employee2.setJobPosition("总裁");
        employee2.setAsctJobPosition("副会长");
        employee2.setChapterJobPosition("会长");
        employee2.setEmail("angryfeng@163.com");
        employee2.setOfficePhoneNum("020-6987459");
        employee2.setCellPhoneNum("18813973767");
        employee2.setFaxNum("020-5689751");
        employee2.setOnlineNum("553783307");
        employee2.setNature(2);
        List<NCompanyEmployee> employees = new ArrayList<NCompanyEmployee>();
        employees.add(employee1);
        employees.add(employee2);
        System.out.println(nCompanyEmployeeService.creatCompanyEmployees(company.getId(), employees, 2));
    }
}

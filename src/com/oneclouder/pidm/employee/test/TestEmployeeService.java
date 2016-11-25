package com.oneclouder.pidm.employee.test;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * Created by PhychoLee on 9/20/16 9:55 PM.
 * Description:
 */
public class TestEmployeeService {
    private ApplicationContext ac = null;
    private IEmployeeService employeeService = null;
    {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeService = ac.getBean(IEmployeeService.class);
    }

    @Test
    public void test() throws SQLException {
        Employee employee = employeeService.findById(1);
        System.out.println(employee);
    }
}

package com.oneclouder.pidm.employee.test;

import com.alibaba.fastjson.JSON;
import com.oneclouder.pidm.employee.model.Department;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.model.JobPosition;
import com.oneclouder.pidm.employee.model.Role;
import com.oneclouder.pidm.employee.service.IDepartmentService;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.employee.service.IJobPositionService;
import com.oneclouder.pidm.system.service.IRoleService;
import com.oneclouder.pidm.util.EncryptUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 9:29 AM.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class TestEmployee {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IJobPositionService jobPositionService;
    @Autowired
    private IRoleService roleService;

    @Test
    public void testEmpAdd() throws SQLException {
        Employee employee = new Employee();
        employee.setUserName("wjj11112");
        employee.setRealName("吴基将");
        employee.setBirthday(new Date());
        employee.setGender(Employee.GENDER_MALE);
        Map<String, String> encrypt = EncryptUtils.encrypt("111111");
        employee.setPassword(encrypt.get("password"));
        employee.setSalt(encrypt.get("salt"));
        employee.setEmail("wjj@boiwa.com");
        employee.setJobStatus(Employee.JOBSTATUS_ACTIVE);
        employee.setCellPhoneNum("18813958445");

        String ids = new String("1,6");
        employee.setRoleIds(ids);

        employeeService.insertEmployee(employee);
    }

    @Test
    public void testEmpFind() throws SQLException {
        Employee employee = employeeService.findById(1);
        System.out.println(employee);
//        employeeService.findByRole(1);
//        String ids = new String("1,6");
//        String[] roleIds = ids.split(",");
//        for (String s : roleIds){
//            System.out.println(s);
//        }
    }

    @Test
    public void testEmpFindByDept() throws SQLException {
//        List<Employee> employeeList = employeeService.findByDept(null);
//        for (Employee employee : employeeList){
//            System.out.println(employee);
//        }
    }

    @Test
    public void findEmpByRole() throws SQLException {
        List<Employee> employeeList = employeeService.findByRoleName("超级管理员");
        employeeList.forEach(System.out::println);
    }

    @Test
    public void findRoleIdByEmp() throws SQLException {
        List<Integer> roleIdList = employeeService.findRoleIdByEmp(10);
        System.out.println(roleIdList.toString());
//        roleIdList.forEach(System.out::println);
        String ids = "";
        for(Integer id : roleIdList){
            ids += id +",";
        }
        System.out.println("ids:"+ids);
    }

    @Test
    public void testEmpCount() throws SQLException {
        Integer integer = employeeService.countByDept(null);
        System.out.println(integer);
    }

    @Test
    public void testEmpFindByRole() throws SQLException {
        List<Employee> employeeList = employeeService.findByRole(1);
        employeeList.forEach(System.out::println);
    }

    @Test
    public void testEmpUpdate() throws SQLException {
        Employee employee = employeeService.findById(1);
        employee.setUserName("Jack Ma");
        employeeService.update(employee);
    }

    @Test
    public void tsetEmpCheckUserName() throws SQLException {
        Employee llf = employeeService.findByUserName("llf");
        System.out.println(llf);
    }

    @Test
    public void testDeptAdd() throws SQLException {
//        Department parentDept = departmentService.findById(1);
//
//        Department dept = new Department();
//        dept.setName("信息部");
//        dept.setCode("XXB001");
//        dept.setParentDept(parentDept);
//
//        departmentService.insert(dept);
//
//        Employee employee = new Employee();
    }

    @Test
    public void testDeptFind() throws SQLException {
        Department department = departmentService.findById(3);
        System.out.println("======="+department);
    }

    @Test
    public void testDeptIterate() throws SQLException {
        List<Department> departments = departmentService.iterateDepartment(null);
        departments.forEach(System.out::println);
    }

    @Test
    public void testFindAllChild() throws SQLException {
        long begin = System.nanoTime();
        List<Department> allChild = departmentService.findAllChild(5);
//        allChild.forEach(System.out::println);

        long end = System.nanoTime() - begin;

        System.out.println("time:"+end);
        System.out.println("==================");

        long begin2 = System.nanoTime();
        List<Department> allChild2 = departmentService.findAllChild(5);
//        allChild2.forEach(System.out::println);

        long end2 = System.nanoTime() - begin;

        System.out.println("time:"+end2);
    }

    @Test
    public void testDeptFindByJbId() throws SQLException {
        Department department = departmentService.findByJB(1);
        System.out.println(department);
    }

    @Test
    public void testDeptFindByParent() throws SQLException {
        List<Department> departmentList = departmentService.findByParent(null);
        for (Department department : departmentList){
            List<Department> departments = departmentService.findByParent(department.getId());
            department.setChildList(departments);
            System.out.println(department);
        }
    }

    @Test
    public void testJBAdd() throws SQLException {
        Department department = departmentService.findById(3);

        JobPosition jobPosition = new JobPosition();
        jobPosition.setName("部长");
        jobPosition.setDepartment(department);

        jobPositionService.insert(jobPosition);
    }

    @Test
    public void testJBFind() throws SQLException {
        JobPosition jobPosition = jobPositionService.findById(1);
        System.out.println(jobPosition);
    }

    @Test
    public void testJBFindByDept() throws SQLException {
//        List<JobPosition> jobPositionList = jobPositionService.findByDeptByPage(null, 0, 10);
        List<JobPosition> jobPositionList = jobPositionService.findByDeptByPage(7, null);
        jobPositionList.forEach(System.out::println);
    }

    @Test
    public void testDeleteJP() throws SQLException {
        Integer count = employeeService.countByJP(1);
        System.out.println(count);
    }

    @Test
    public void testRoleAdd() throws SQLException {
        Role role = new Role();
        role.setName("管理员");
    }

    @Test
    public void testEncrypt(){
        /*Map<String, String> encrypt = EncryptUtils.encrypt("111");
        String salt = encrypt.get("salt");
        String password = encrypt.get("password");
        System.out.println(salt+"==========="+password);

        String encrypt1 = EncryptUtils.encrypt(salt, "111");
        System.out.println(encrypt1);*/
    }

    @Test
    public void testSub(){
        String url = "/client";
        String substring = url.substring(1, url.length());
        System.out.println(substring);
    }

    @Test
    public void testParse(){
        String userInfo = "{\"nick\":\"马玎\",\"gmt_created\":\"2016-10-25 14:54:41\",\"mobile\":\"18520217080\",\"email\":\"1075787627@qq.com\",\"ip_whitelist\":null,\"api_version\":\"v2\",\"alarm_balance\":0,\"emergency_contact\":\"\",\"emergency_mobile\":\"\",\"balance\":0.445}\n";
        Map<String,Object> infoMap = JSON.parseObject(userInfo, Map.class);
        BigDecimal balance1 = (BigDecimal) infoMap.get("balance");
        Double balance = balance1.doubleValue();
        System.out.println(balance>0.055);
    }
}

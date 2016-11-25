package com.oneclouder.pidm.employee.controller;

import com.oneclouder.pidm.employee.model.Department;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.service.IDepartmentService;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.employee.service.IJobPositionService;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.util.EncryptUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/14/16 10:30 AM.
 * Description:员工Action类
 */
@Controller
@RequestMapping("/back/employee")
public class EmployeeAction {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IJobPositionService jobPositionService;
    @Autowired
    private IDepartmentService departmentService;

    /**
     * 链接到jsp
     * @return
     */
    @RequestMapping("")
    public String employee(HttpServletRequest request){
        return "/back/system/employee";
    }


    @RequestMapping(value = "/getEmployees", method = RequestMethod.POST)
    @ResponseBody
    public List<Employee> getEmployees(@RequestBody Map<String, Object> params){
        Integer deptId = (Integer) params.get("deptId");
        Integer jobStatusSH = null;
        if (params.get("jobStatusSH")!=null && !"".equals(params.get("jobStatusSH").toString().trim())){
            jobStatusSH = Integer.valueOf((String) params.get("jobStatusSH"));
        }
        String realNameSH = (String) params.get("realNameSH");
        String userNameSH = (String) params.get("userNameSH");

        List<Employee> employeeList = null;
        try {
            if (deptId == null){
                employeeList = employeeService.findByDept(null, jobStatusSH, realNameSH, userNameSH);
            }else{
                employeeList = employeeService.findByDept(deptId, jobStatusSH, realNameSH, userNameSH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    /**
     * 保存员工
     * @param employee
     * @return
     */
    @RequestMapping(value="/saveEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody Employee employee, HttpServletRequest request){
        System.out.println(employee);
        try {
            //检查验证码是否正确
            String phoneMsgValidate = (String) request.getSession().getAttribute("PhoneMsgValidate");
            String phoneMsg = employee.getPhoneMsg();
            if (phoneMsgValidate != null && phoneMsg != null && phoneMsgValidate.equals(phoneMsg)){
                //验证通过
            }else{
                throw new RuntimeException("验证码错误!");
            }



            //检查用户名是否被占用
            Employee checkEmployee = employeeService.findByUserName(employee.getUserName());
            if (checkEmployee != null){
                throw new RuntimeException("用户名已存在!");
            }

            //加密
            Map<String, String> encrypt = EncryptUtils.encrypt(employee.getPassword());
            employee.setPassword(encrypt.get("password"));
            employee.setSalt(encrypt.get("salt"));

            if(employee.getBirthday() == null){
                employee.setBirthday(new Date());
            }

            employeeService.insertEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    /**
     * 检查账号有没有被占用
     * @param userName
     * @return
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public Map<String, Object> checkUserName(String userName){
        Map<String, Object> map = new HashMap<>();
        try {
            Employee employee = employeeService.findByUserName(userName);
            if (employee == null){
                map.put("valid", "true");
            }else {
                map.put("valid", "false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 检查账号有没有被占用
     * @param userName
     * @return
     */
    @RequestMapping("/checkUserName2")
    @ResponseBody
    public Map<String, Object> checkUserName2(String userName, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            Employee employee = employeeService.findByUserName(userName);
            Integer id = (Integer) request.getSession().getAttribute("updateEmployeeId");
            if (employee != null && !employee.getId().equals(id)){
                map.put("valid", "false");
            }else {
                map.put("valid", "true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据id查找员工
     * @param id
     * @return
     */
    @RequestMapping("/getEmployee")
    @ResponseBody
    public Employee getEmployee(Integer id, HttpServletRequest request){

        Employee employee =null;
        try {
            employee = employeeService.findById(id);
            employee.setPassword("");
            employee.setSalt("");
            request.getSession().setAttribute("updateEmployeeId", employee.getId());
            if (employee.getJobPositionId() != null){
                //传入部门信息
                Department department = departmentService.findByJB(employee.getJobPositionId());
                employee.setDepartmentName(department.getName());
                employee.setDepartmentId(department.getId());
            }
            //获取角色id
            List<Integer> roleIdList = employeeService.findRoleIdByEmp(employee.getId());
            if (roleIdList != null && roleIdList.size() != 0){
                String ids = "";
                for(Integer roleId : roleIdList){
                    ids += roleId +",";
                }
                ids = ids.substring(0, ids.length()-1);
                employee.setRoleIds(ids);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    /**
     * 修改员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
    @ResponseBody
    public String updateEmployee(@RequestBody Employee employee, HttpServletRequest request){
        try {
            //检查不能为空项
            String realName = employee.getRealName();
            Integer jobStatus = employee.getJobStatus();
            Integer jobPositionId = employee.getJobPositionId();
            String userName = employee.getUserName();
            String password = employee.getPassword();
            Integer gender = employee.getGender();
            String cellPhoneNum = employee.getCellPhoneNum();
            String email = employee.getEmail();
            if ("".equals(realName)){
                throw new RuntimeException("姓名不能为空!");
            }else if (jobStatus == null){
                throw new RuntimeException("在职状态不能为空!");
            }else if (jobPositionId == null){
                throw new RuntimeException("所在岗位不能为空!");
            }else if("".equals(userName)){
                throw new RuntimeException("工号/账号不能为空!");
            }else if(gender == null){
                throw new RuntimeException("性别不能为空!");
            }else if("".equals(cellPhoneNum)){
                throw new RuntimeException("手机号码不能为空!");
            }else if ("".equals(email)){
                throw new RuntimeException("邮箱不能为空!");
            }


           //检查验证码是否正确
//            String phoneMsgValidate = (String) request.getSession().getAttribute("PhoneMsgValidate");
//            String phoneMsg = employee.getPhoneMsg();
//
//            if (!StringUtils.isEmpty(phoneMsgValidate)){
//                if (!phoneMsgValidate.equals(phoneMsg)){
//                    throw new RuntimeException("验证码错误!");
//                }
//            }
//            if (!StringUtils.isEmpty(phoneMsgValidate) && !StringUtils.isEmpty(phoneMsg)  && phoneMsgValidate.equals(phoneMsg)){
//                //验证通过
//            }else{
//                throw new RuntimeException("验证码错误!");
//            }

            Employee byUserName = employeeService.findByUserName(employee.getUserName());
            if (byUserName != null && !byUserName.getId().equals(employee.getId()) ){
                throw new RuntimeException("用户名已存在");
            }

            if (!"".equals(employee.getPassword().trim())){
                //密码有值将重新加密新密码
                Map<String, String> encrypt = EncryptUtils.encrypt(employee.getPassword());
                employee.setPassword(encrypt.get("password"));
                employee.setSalt(encrypt.get("salt"));
            }else{
                employee.setPassword(null);
            }

            if(employee.getBirthday() == null){
                employee.setBirthday(new Date());
            }

            employeeService.updateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "success";
    }

    @RequestMapping("/deleteEmployee")
    @ResponseBody
    public String deleteEmployee(String ids){
        try {
            employeeService.deleteEmployee(ids);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}

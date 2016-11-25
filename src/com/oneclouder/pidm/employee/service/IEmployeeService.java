package com.oneclouder.pidm.employee.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.employee.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PhychoLee on 9/10/16 9:20 AM.
 */
public interface IEmployeeService extends IBaseService<Employee> {

    List<Employee> findByDept(Integer deptId, Integer jobStatus, String realName, String userName) throws SQLException;

    List<Employee> findByRole(Integer roleId) throws SQLException;

    Employee findByUserName(String userName) throws SQLException;

    Integer countByDept(Integer deptId) throws SQLException;

    void insertEmployee(Employee employee) throws SQLException;

    List<Employee> findByRoleName(String name) throws  SQLException;

    List<Integer> findRoleIdByEmp(Integer employeeId) throws SQLException;

    void updateEmployee(Employee employee) throws SQLException;

    Integer findEmpRole(Integer employeeId, Integer roleId) throws SQLException;

    void deleteEmployee(String ids) throws SQLException;

    Integer countByJP(Integer jpId) throws  SQLException;

    Employee findByPhoneNum(String phoneNum) throws SQLException;
}

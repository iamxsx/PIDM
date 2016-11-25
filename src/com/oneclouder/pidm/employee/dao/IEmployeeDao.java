package com.oneclouder.pidm.employee.dao;


import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.employee.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 9:00 AM.
 */

@MyBatisRepository
@Repository
@Lazy
public interface IEmployeeDao extends IBaseDao<Employee> {

    List<Employee> findByDept(Map<String, Object> params) throws SQLException;

    List<Employee> findByRole(@Param("roleId") Integer roleId) throws SQLException;

    Employee findByUserName(@Param("userName") String userName) throws SQLException;

    String findNameById(@Param("employee_id") Integer employee_id) throws SQLException;

    Integer countByDept(@Param("deptId") Integer deptId) throws SQLException;

    void insertEmpRole(Map<String, Object> params) throws  SQLException;

    List<Integer> findRoleIdByEmp(@Param("employeeId") Integer employeeId) throws SQLException;

    Integer findEmpRole(Map<String, Object> params) throws SQLException;

    void deleteERByEmp(@Param("employeeId") Integer employeeId) throws SQLException;

    Integer countByJP(@Param("jpId") Integer jpId) throws SQLException;

    Employee findByPhoneNum(@Param("phoneNum")String phoneNum) throws SQLException;
}

package com.oneclouder.pidm.employee.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.employee.dao.IEmployeeDao;
import com.oneclouder.pidm.employee.model.Department;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.service.IDepartmentService;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.employee.service.IJobPositionService;
import com.oneclouder.pidm.system.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 9:21 AM.
 */
@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {


    private IEmployeeDao employeeDao;
    @Autowired
    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
        super.setDao(employeeDao);
    }

    @Autowired
    private IJobPositionService jobPositionService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    /**
     * 根据部门id查找员工
     * @param deptId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Employee> findByDept(Integer deptId, Integer jobStatus, String realName, String userName) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("jobStatus", jobStatus);
        params.put("realName", realName);
        params.put("userName", userName);

        List<Employee> employeeList =new ArrayList<>();
        if (deptId == null){
            //没有传入部门id，查找数据库中所有员工
            params.put("deptId", deptId);
            employeeList = employeeDao.findByDept(params);
        }else {
            //传入部门id时，根据部门id查找子部门，找出部门下的所有员工
            List<Department> departmentList = departmentService.findAllChild(deptId);
            departmentList.add(0, departmentService.findById(deptId));
            for (Department department : departmentList){
                params.put("deptId", department.getId());
                List<Employee> childEmpList = employeeDao.findByDept(params);
                if (childEmpList != null && childEmpList.size() != 0){
                    employeeList.addAll(childEmpList);
                }
            }
        }

        if (employeeList != null && employeeList.size() != 0){
            for(Employee employee : employeeList){
                /**
                 * 岗位id不为空时，查找员工的所在部门和岗位的名称
                 */
                if (employee.getJobPositionId() != null){
                    employee.setJobPositionName(jobPositionService.findById(employee.getJobPositionId()).getName());
                    employee.setDepartmentName(departmentService.findByJB(employee.getJobPositionId()).getName());
                }
            }
        }
        return employeeList;
    }

    /**
     * 根据角色id查找员工
     * @param roleId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Employee> findByRole(Integer roleId) throws SQLException {
        return employeeDao.findByRole(roleId);
    }

    /**
     * 根据角色名查找员工
     * @param name
     * @return
     * @throws SQLException
     */
    public List<Employee> findByRoleName(String name) throws  SQLException{
        Integer roleId = roleService.findByName(name);
        List<Employee> employeeList = findByRole(roleId);

        return employeeList;
    }

    @Override
    public Employee findByUserName(String userName) throws SQLException {
        return employeeDao.findByUserName(userName);
    }

    /**
     * 根据部门获取员工总数
     * @param deptId
     * @return
     * @throws SQLException
     */
    @Override
    public Integer countByDept(Integer deptId) throws SQLException {
        return employeeDao.countByDept(deptId);
    }

    /**
     * 插入员工和员工角色中间表
     * @param employee
     * @throws SQLException
     */
    @Transactional
    public void insertEmployee(Employee employee) throws SQLException {
        employeeDao.insert(employee);
        Integer employeeId = employee.getId();

        //添加员工角色中间表
        String[] roleIds = employee.getRoleIds().split(",");
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        for (String id : roleIds){
            if (!"".equals(id.trim())){
                Integer roleId = Integer.valueOf(id);
                params.put("roleId", roleId);
                employeeDao.insertEmpRole(params);
            }

        }
    }

    /**
     * 根据员工id获取所有角色id
     * @param employeeId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Integer> findRoleIdByEmp(Integer employeeId) throws SQLException {
        return employeeDao.findRoleIdByEmp(employeeId);
    }

    /**
     * 修改员工信息
     * @param employee
     * @throws SQLException
     */
    @Transactional
    public void updateEmployee(Employee employee) throws SQLException {
        employeeDao.update(employee);
        Integer employeeId = employee.getId();

        //先删除本员工已有的角色关联
        deleteERByEmp(employeeId);

        //添加员工角色中间表
        String[] roleIds = employee.getRoleIds().split(",");
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        for (String id : roleIds){
            if (!"".equals(id.trim())){
                Integer roleId = Integer.valueOf(id);
                params.put("roleId", roleId);
                employeeDao.insertEmpRole(params);
            }

        }
    }

    /**
     * 删除员工以及角色关联
     * @param ids
     * @throws SQLException
     */
    @Transactional
    public void deleteEmployee(String ids) throws SQLException {
        String[] employeeIds = ids.split(",");
        for (String id : employeeIds){
            if (!"".equals(id.trim())){
                Integer employeeId = Integer.valueOf(id);

                //先删除本员工已有的角色关联
                deleteERByEmp(employeeId);

                employeeDao.deleteById(employeeId);
            }
        }

    }

    /**
     * 查找员工和角色有无关联
     * @param employeeId
     * @param roleId
     * @return
     * @throws SQLException
     */
    @Override
    public Integer findEmpRole(Integer employeeId, Integer roleId) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("roleId", roleId);
        return employeeDao.findEmpRole(params);
    }

    /**
     * 根据员工id批量删除员工角色关联表
     *
     * @param employeeId
     * @throws SQLException
     */
    private void deleteERByEmp(Integer employeeId) throws SQLException {
        employeeDao.deleteERByEmp(employeeId);
    }

    /**
     * 根据岗位id查找员工数
     * @param jpId
     * @return
     * @throws SQLException
     */
    @Override
    public Integer countByJP(Integer jpId) throws SQLException {
        return employeeDao.countByJP(jpId);
    }

    /**
     * 根据手机号获取员工
     * @param phoneNum
     * @return
     * @throws SQLException
     */
    @Override
    public Employee findByPhoneNum(String phoneNum) throws SQLException {
        return employeeDao.findByUserName(phoneNum);
    }
}

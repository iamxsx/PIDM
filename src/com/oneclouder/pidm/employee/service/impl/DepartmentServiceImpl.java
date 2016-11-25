package com.oneclouder.pidm.employee.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.employee.dao.IDepartmentDao;
import com.oneclouder.pidm.employee.model.Department;
import com.oneclouder.pidm.employee.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 10:19 AM.
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    private IDepartmentDao departmentDao;
    @Autowired
    public void setIDepartmentDao(IDepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
        super.setDao(departmentDao);
    }

    /**
     * 根据父部门id查找部门集合，如果传入参数为null，则查找顶层部门
     * @param parentId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Department> findByParent(Integer parentId) throws SQLException {
        return departmentDao.findByParent(parentId);
    }

    /**
     * 查找所有子孙部门，装进一个List
     * @param deptId
     * @return
     * @throws SQLException
     */
    public List<Department> findAllChild(Integer deptId) throws SQLException{
        List<Department> departmentList;
        if (deptId == null){
            departmentList = findByParent(null);
        } else {
            departmentList = findByParent(deptId);
        }

        if (departmentList != null && departmentList.size() != 0){
            List<Department> tempList = new ArrayList<>();
            for (Department department : departmentList){
                List<Department> departments = findAllChild(department.getId());
                tempList.addAll(departments);
            }
            departmentList.addAll(tempList);
        }
        return departmentList;
    }

    public List<Department> findAllChild2(Integer deptId) throws SQLException {
        List<Department> departmentList = departmentDao.findAll();
        recurseAllChild(deptId, departmentList);
        return null;
    }

    public List<Department> recurseAllChild(Integer deptId, List<Department> deptList){
        List<Department> resultList = new ArrayList<>();
        for (Department dept : deptList){
            if (deptId==null && dept.getParentId() == null){
//                resultList.add(dept);
            } else if(deptId!=null && deptId.equals(dept.getParentId())){

            }
        }

        return null;
    }

    /**
     * 将部门按层级输出
     * @param dept
     * @return
     * @throws SQLException
     */
    public List<Department> iterateDepartment(Department dept) throws SQLException {
        List<Department> departmentList;
        if (dept == null){
            departmentList = findByParent(null);
        } else {
            departmentList = findByParent(dept.getId());
        }
        //添加子部门集合
        if (departmentList != null && departmentList.size() != 0){
            if (dept != null){
                dept.setChildList(departmentList);
            }
            for (Department department : departmentList){
                iterateDepartment(department);
            }
        }
        return departmentList;
    }

    /**
     * 根据岗位查找部门
     * @param jbId
     * @return
     * @throws SQLException
     */
    @Override
    public Department findByJB(Integer jbId) throws SQLException {
        return departmentDao.findByJB(jbId);
    }

    /**
     * 根据标号查询部门
     * @param code
     * @return
     * @throws SQLException
     */
    @Override
    public Department findByCode(String code) throws SQLException {
        return departmentDao.findByCode(code);
    }

    /**
     * 根据部门名称和 父部门查找部门
     * @param name
     * @param parentId
     * @return
     * @throws SQLException
     */
    @Override
    public Department findByNameAndParent(String name, Integer parentId) throws SQLException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("parentId", parentId);
        return departmentDao.findByNameAndParent(params);
    }
}

package com.oneclouder.pidm.employee.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.employee.model.Department;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 10:19 AM.
 */
public interface IDepartmentService extends IBaseService<Department> {

    List<Department> findByParent(Integer parentId) throws SQLException;

    List<Department> iterateDepartment(Department dept) throws SQLException;

    Department findByJB(Integer jbId) throws SQLException;

    List<Department> findAllChild(Integer deptId) throws SQLException;

    Department findByCode(String code) throws SQLException;

    Department findByNameAndParent(String name, Integer parentId) throws SQLException;
}

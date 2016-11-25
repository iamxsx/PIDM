package com.oneclouder.pidm.employee.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.employee.model.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 10:02 AM.
 */
@MyBatisRepository
@Repository
@Lazy
public interface IDepartmentDao extends IBaseDao<Department> {

    List<Department> findByParent(@Param("parentId") Integer parentId) throws SQLException;

    Department findByJB(@Param("jbId")Integer jbId) throws SQLException;

    Department findByCode(@Param("code")String code) throws SQLException;

    Department findByNameAndParent(Map<String, Object> params) throws SQLException;

    List<Department> findAll() throws SQLException;
}

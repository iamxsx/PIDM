package com.oneclouder.pidm.employee.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.employee.model.JobPosition;
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
public interface IJobPositionDao extends IBaseDao<JobPosition> {

    List<JobPosition> findByDeptByPage(Map<String, Object> params) throws SQLException;

    Integer countByDept(@Param("deptId") Integer deptId) throws SQLException;

    List<JobPosition> findByDept(@Param("deptId") Integer deptId) throws SQLException;
}

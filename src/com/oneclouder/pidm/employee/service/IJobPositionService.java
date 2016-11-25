package com.oneclouder.pidm.employee.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.employee.model.JobPosition;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PhychoLee on 9/10/16 11:02 AM.
 */
public interface IJobPositionService extends IBaseService<JobPosition> {

    List<JobPosition> findByDeptByPage(Integer deptId, String jbName) throws SQLException;

    Integer countByDept(Integer deptId) throws SQLException;

    List<JobPosition> findByDept(Integer deptId) throws SQLException;

    public void deleteJP(String ids) throws SQLException;
}

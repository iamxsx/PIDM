package com.oneclouder.pidm.employee.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.employee.dao.IJobPositionDao;
import com.oneclouder.pidm.employee.model.Department;
import com.oneclouder.pidm.employee.model.JobPosition;
import com.oneclouder.pidm.employee.service.IDepartmentService;
import com.oneclouder.pidm.employee.service.IJobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 11:03 AM.
 */
@Service
public class JobPositionServiceImpl extends BaseServiceImpl<JobPosition> implements IJobPositionService {

    private IJobPositionDao jobPositionDao;
    @Autowired
    public void setJobPositionDao(IJobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
        super.setDao(jobPositionDao);
    }

    @Autowired
    private IDepartmentService departmentService;

    /**
     * 根据部门查找岗位；传入参数为空时，返回所有
     * @param deptId
     * @return
     * @throws SQLException
     */
    @Override
    public List<JobPosition> findByDeptByPage(Integer deptId, String jbName) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("jbName",jbName);

        List<JobPosition> jbList = new ArrayList<>();

        if (deptId == null){
            params.put("deptId", deptId);
            jbList = jobPositionDao.findByDeptByPage(params);
        }else{
            List<Department> departmentList = departmentService.findAllChild(deptId);
            departmentList.add(0, departmentService.findById(deptId));
            for (Department department : departmentList){
                params.put("deptId", department.getId());
                List<JobPosition> childJBList = jobPositionDao.findByDeptByPage(params);
                if (childJBList != null && childJBList.size() != 0){
                    jbList.addAll(childJBList);
                }
            }
        }

        return jbList;
    }

    /**
     * 根据部门查找总数
     * @param deptId
     * @return
     * @throws SQLException
     */
    @Override
    public Integer countByDept(Integer deptId) throws SQLException {
        return jobPositionDao.countByDept(deptId);
    }

    @Override
    public List<JobPosition> findByDept(Integer deptId) throws SQLException {
        return jobPositionDao.findByDept(deptId);
    }

    /**
     * 批量删除岗位
     * @param ids
     * @throws SQLException
     */
    @Transactional
    public void deleteJP(String ids) throws SQLException {
        String[] jpIds = ids.split(",");
        for (String id : jpIds) {
            if (!"".equals(id.trim())) {
                Integer jpId = Integer.valueOf(id);

                jobPositionDao.deleteById(jpId);
            }
        }
    }
}

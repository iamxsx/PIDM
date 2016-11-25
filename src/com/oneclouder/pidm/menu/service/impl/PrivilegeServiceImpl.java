package com.oneclouder.pidm.menu.service.impl;

import com.oneclouder.pidm.menu.dao.IPrivilegeDao;
import com.oneclouder.pidm.menu.model.Privilege;
import com.oneclouder.pidm.menu.service.IPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PhychoLee on 9/14/16 9:02 AM.
 * Description:
 */
@Service
public class PrivilegeServiceImpl implements IPrivilegeService {
    @Autowired
    private IPrivilegeDao privilegeDao;

    /**
     * 根据员工id查找菜单的权限
     * @param employeeId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Privilege> findByEmployee(Integer employeeId) throws SQLException {
        return privilegeDao.findByEmployee(employeeId);
    }
}

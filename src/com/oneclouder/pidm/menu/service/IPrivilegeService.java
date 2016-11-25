package com.oneclouder.pidm.menu.service;

import com.oneclouder.pidm.menu.model.Privilege;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PhychoLee on 9/14/16 9:01 AM.
 * Description:
 */
public interface IPrivilegeService {

    List<Privilege> findByEmployee(Integer employeeId) throws SQLException;
}

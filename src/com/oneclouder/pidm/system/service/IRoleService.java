package com.oneclouder.pidm.system.service;



import com.oneclouder.pidm.system.model.Role;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-19.
 */
public interface IRoleService {

    public List<Role> findRoles();

    public int getRoleCount();

    public void bindRoleMenus(Map<String, Object> params);

    public void addRole(Role role);

    public void deleteRole(Integer id);

    Integer findByName(String name) throws SQLException;

    public void updateRoleMenus(Integer rid,String roleName, String ids);
}

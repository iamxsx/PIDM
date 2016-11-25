package com.oneclouder.pidm.system.service.impl;

import com.oneclouder.pidm.system.dao.IRoleDao;
import com.oneclouder.pidm.system.model.Role;
import com.oneclouder.pidm.system.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-19.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Resource(name = "roleDao")
    private IRoleDao roleDao;


    @Override
    public List<Role> findRoles() {
        return roleDao.findRoles();
    }

    @Override
    public int getRoleCount() {
        return roleDao.getRoleCount();
    }

    @Override
    public void bindRoleMenus(Map<String, Object> params) {
        roleDao.bindRoleMenus(params);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteRole(id);
    }

    @Override
    public Integer findByName(String name) throws SQLException {
        return roleDao.findByName(name);
    }

    @Override
    @Transactional
    public void updateRoleMenus(Integer roleId,String roleName, String ids) {
        roleDao.deleteRoleMenus(roleId);
        Map<String,Object> params = new HashMap<>();
        params.put("roleId",roleId);
        params.put("ids",Arrays.asList(ids.split(",")));
        roleDao.updateRoleMenus(params);
        params.clear();
        params.put("roleId",roleId);
        params.put("roleName",roleName);
        roleDao.updateRoleName(params);
    }
}

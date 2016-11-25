package com.oneclouder.pidm.system.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.system.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-19.
 */
@MyBatisRepository
@Repository("roleDao")
public interface IRoleDao {

    public List<Role> findRoles();

    public int getRoleCount();

    public void bindRoleMenus(Map<String, Object> params);

    public void addRole(Role role);

    public void deleteRole(Integer id);

    Integer findByName(@Param("name") String name) throws SQLException;

    public void deleteRoleMenus(Integer roleId);

    public void updateRoleMenus(Map<String, Object> params);

    public void updateRoleName(Map<String,Object> params);
}

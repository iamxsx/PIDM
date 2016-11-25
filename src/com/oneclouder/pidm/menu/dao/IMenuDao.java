package com.oneclouder.pidm.menu.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.menu.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/09/16.
 */
@MyBatisRepository
@Repository("menuDao")
public interface IMenuDao {

    public List<Menu> queryLevel1Menu();

    public List<Menu> querySecondMenu(Integer parentId);

    public Menu queryMenuById(Integer id);

    List<Menu> findMenuByEmployee(Map<String, Object> params) throws SQLException;

    List<Menu> findMenuByParent(Map<String, Object> params) throws SQLException;

    public List<Menu> queryFrontMenu();

    public List<Menu> queryBackMenu();

    List<Menu> queryPrivilegeMenu();

    public List<Menu> findMenusByRoleId(Integer roleId);

    public void updateMenu(Map<String, Object> params);

    List<Menu> getSecondMenu(List<Integer> menuIds);

    String getSecondMenuIds(Integer menuId);

    List<Menu> getSecondMenuByMenuName(String menuName);

}

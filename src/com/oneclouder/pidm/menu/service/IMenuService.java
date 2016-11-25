package com.oneclouder.pidm.menu.service;

import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Node;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/09/16.
 */
public interface IMenuService {

    public List<Menu> queryLevel1Menu();

    public List<Menu> querySecondMenu(Integer parendId);

    public Menu queryMenuById(Integer id);

    List<Menu> findMenuByEmployee(Integer employeeId, Integer location) throws SQLException;

    List<Menu> findMenuByParent(Integer parentId, Integer location) throws SQLException;

    public List<Menu> queryFrontMenu();

    public List<Menu> queryBackMenu();

    List<Menu> queryPrivilegeMenu();

    public List<Menu> findMenusByRoleId(Integer roleId);

    public void updateMenu(Map<String, Object> params);

    List<Menu> getSecondMenu(Integer menuId);

    public void setBackMenu(HttpServletRequest request);

    List<Menu> getSecondMenuByMenuName(String menuName);

    public List getSonMenuIds(Integer menuId);

    public List<Node> getMenusNode(List<Menu> menus);

    public List<Node> getMenusAndPermsNode(List<Menu> menus, Integer roleId);

}

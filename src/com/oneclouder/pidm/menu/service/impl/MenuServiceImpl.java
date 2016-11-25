package com.oneclouder.pidm.menu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.menu.dao.IMenuDao;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Node;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.redis.service.IRedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by clouder on 10/09/16.
 */
@Service("menuService")
public class  MenuServiceImpl implements IMenuService{

    @Resource(name = "menuDao")
    private IMenuDao menuDao;

    @Override
    public List<Menu> queryLevel1Menu() {
        return menuDao.queryLevel1Menu();
    }

    @Override
    public List<Menu> querySecondMenu(Integer parendId) {
        return menuDao.querySecondMenu(parendId);
    }

    @Override
    public Menu queryMenuById(Integer id) {
        return menuDao.queryMenuById(id);
    }

    /**
     * 根据员工id查找菜单
     * @param employeeId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Menu> findMenuByEmployee(Integer employeeId, Integer location) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("location", location);
        return menuDao.findMenuByEmployee(params);
    }

    /**
     * 根据父菜单id查找菜单集合
     * @param parentId
     * @param location
     * @return
     * @throws SQLException
     */
    @Override
    public List<Menu> findMenuByParent(Integer parentId, Integer location) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        params.put("location", location);
        return menuDao.findMenuByParent(params);
    }

    @Override
    public List<Menu> queryFrontMenu() {
        return menuDao.queryFrontMenu();
    }

    @Override
    public List<Menu> queryBackMenu() {
        return menuDao.queryBackMenu();
    }

    @Override
    public List<Menu> queryPrivilegeMenu() {
        return menuDao.queryPrivilegeMenu();
    }

    @Override
    public List<Menu> findMenusByRoleId(Integer roleId) {
        return menuDao.findMenusByRoleId(roleId);
    }

    @Override
    public void updateMenu(Map<String, Object> params) {
        menuDao.updateMenu(params);
    }

    /**
     * 从当前登陆用户中获取二级菜单
     * @param id
     * @return
     */
    @SuppressWarnings("Duplicates")
    private List<Menu> getSecondMenuList(Integer id){
        List<Menu> secondMenuList = new ArrayList<>();

        //从shiro的session中获取登陆信息
        Subject subject = SecurityUtils.getSubject();

        Employee currentEmployee = (Employee) subject.getPrincipal();
        List<Menu> menuList = currentEmployee.getMenuList();
//        secondMenuList.addAll(menuList.stream().filter(menu -> menu.getParentId().equals(id)).collect(Collectors.toList()));
        for (Menu menu : menuList){
            if (id.equals(menu.getParentId())){
                secondMenuList.add(menu);
            }
        }

        return secondMenuList;
    }

    /**
     * 获取第二级链接，放进request域中
     * @param request
     */
    @SuppressWarnings("Duplicates")
    public void setBackMenu(HttpServletRequest request){
        List<Menu> topMenuList = new ArrayList<>();
        //从shiro的session中获取登陆信息
        Subject subject = SecurityUtils.getSubject();
        Employee currentEmployee = (Employee) subject.getPrincipal();
        List<Menu> menuList1 = currentEmployee.getMenuList();
        topMenuList.addAll(menuList1.stream().filter(menu -> menu.getParentId() == null).collect(Collectors.toList()));
        //将后台顶级菜单放进request域中
        if (request.getSession().getAttribute("backTopMenu") == null){
            request.getSession().setAttribute("backTopMenu",topMenuList);
        }

        try {
//            List<Menu> menuList = findMenuByParent(null, Menu.LOCATION_BACK);

            for (Menu menu : topMenuList){
                List<Menu> secondMenuList = getSecondMenuList(menu.getId());

                String url = menu.getUrl();
                String substring = url.substring(1, url.length());

                if (request.getSession().getAttribute(substring + "Menu") == null) {
                    request.getSession().setAttribute(substring + "Menu", secondMenuList);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getSecondMenuByMenuName(String menuName) {
        return menuDao.getSecondMenuByMenuName(menuName);
    }

    @Override
    public List<Menu> getSecondMenu(Integer menuId) {
        String menuIds = menuDao.getSecondMenuIds(menuId);
        menuIds = menuIds.substring(2);
        List list = Arrays.asList(menuIds.split(","));
        return menuDao.getSecondMenu(list);
    }


    /**
     * 获得子菜单的ｉｄ
     * @param menuId
     * @return
     */
    @Override
    public List getSonMenuIds(Integer menuId){
        String menuIds = menuDao.getSecondMenuIds(menuId);
//        menuIds = menuIds.substring(2);
        Arrays.asList(menuIds.split(","));
        return Arrays.asList(menuIds.split(","));
    }

    /**
     * 将菜单转化为节点
     *
     * @param menus
     * @return
     */
    @Override
    public List<Node> getMenusNode(List<Menu> menus) {
        List<Node> root = new LinkedList<>();
        Map<Integer, Node> nodeList = new HashMap();
        for (Menu menu : menus) {
            Node node = menu2Node(menu);
            nodeList.put(node.getId(), node);
        }
        Set<Map.Entry<Integer, Node>> set = nodeList.entrySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.getParentId() == null) {
                root.add(node);
            } else {
                Node parentNode = nodeList.get(node.getParentId());
                //可能parentNode被隐藏了
                if (parentNode != null) {
                    parentNode.getNodes().add(node);
                }
                //获取权限
                List<Node> privilegeList = getPrivilege(node.getId());
                if (privilegeList != null && privilegeList.size() != 0) {
                    node.getNodes().addAll(privilegeList);
                }
            }
        }
        for (Node node : root) {
            parseNode(node);
        }
        root.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getOrderNum() - o2.getOrderNum();
            }
        });
        return root;
    }

    /**
     * 将节点中没有孩子节点的节点的ｌｉｓｔ清空
     */
    public void parseNode(Node node) {
        if (node.getNodes().isEmpty()) {
            node.setNodes(null);
        } else {
            for (Node node1 : node.getNodes()) {
                parseNode(node1);
            }
        }
    }

    /**
     * 转换Menu为Node
     *
     * @param menu
     * @return
     */
    private Node menu2Node(Menu menu) {
        Node node = new Node();
        node.setId(menu.getId());
        node.setText(menu.getName());
        node.setUrl(menu.getUrl());
        node.setParentId(menu.getParentId());
        node.setOpenWay(menu.getOpenWay());
        node.setIsHidden(menu.getIsHidden());
        node.setIcon(menu.getIcon());
        node.setCanPublish(menu.getCanPublish());
        node.setOrderNum(menu.getOrderNum());
        node.getState().checked = menu.isChecked();
        return node;
    }


    /**
     * 获取权限
     *
     * @param id
     * @return
     */
    private List<Node> getPrivilege(Integer id) {
        List<Node> privilegeNodes = new LinkedList<>();
        try {
            List<Menu> privilegeList = this.findMenuByParent(id, Menu.LOCATION_PRIVILEGE);
            if (privilegeList != null && privilegeList.size() != 0) {
                for (Menu menu : privilegeList) {
                    Node node = menu2Node(menu);

                    privilegeNodes.add(node);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privilegeNodes;
    }

    /**
     * 将菜单和权限转化为节点
     *
     * @param menus
     * @param roleId
     * @return
     */
    @SuppressWarnings("Duplicates")
    public List<Node> getMenusAndPermsNode(List<Menu> menus, Integer roleId) {
        List<Node> root = new LinkedList<>();
        Map<Integer, Node> nodeList = new LinkedHashMap();
        for (Menu menu : menus) {
            Node node = menu2Node(menu);
            nodeList.put(node.getId(), node);
        }
        Set<Map.Entry<Integer, Node>> set = nodeList.entrySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.getParentId() == null) {
                root.add(node);
            } else {
                nodeList.get(node.getParentId())
                        .getNodes().add(node);
                //获取权限
                List<Node> privilegeNodes = getPrivilege(node.getId());
                //获取角色拥有权限
                List<Menu> roleMenus = this.findMenusByRoleId(roleId);
                if (privilegeNodes != null && privilegeNodes.size() != 0) {
                    for (Node permsNode : privilegeNodes) {
                        for (Menu menu : roleMenus) {
                            if (permsNode.getId().equals(menu.getId())) {
                                permsNode.getState().checked = true;
                            }
                        }
                    }
                    node.getNodes().addAll(privilegeNodes);
                }
            }
        }
        for (Node node : root) {
            parseNode(node);
        }

        return root;
    }

}

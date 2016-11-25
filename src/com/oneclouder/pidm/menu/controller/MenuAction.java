package com.oneclouder.pidm.menu.controller;

import com.alibaba.fastjson.JSON;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Node;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.redis.service.IRedisService;
import com.oneclouder.pidm.user.webBean.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by clouder on 16-9-18.
 */
@Controller
@RequestMapping("/back/menu")
public class MenuAction {

    @Resource
    private IMenuService menuService;

    @Resource
    private IRedisService redisService;

    public static final String KEY_MENU_NODES = "KEY_MENU_NODES";

    public static final Integer EXPIRE_TIME = 60 * 60 * 24 * 7;


    @RequestMapping("")
    public String toMenuManage() {
        return "back/system/menu";
    }

    @RequestMapping("get-menu")
    @ResponseBody
    public List<Node> getMenu() {
        List<Menu> menus = menuService.queryFrontMenu();
        List<Node> nodes = menuService.getMenusNode(menus);
        List<Node> root = new LinkedList<>();
        Node node1 = new Node();
        node1.setText("前台");
        node1.setNodes(nodes);
        root.add(node1);
        Node node2 = new Node();
        node2.setText("后台");
        node2.setNodes(null);
        root.add(node2);
        return root;
    }

    @RequestMapping("get-front-menu")
    @ResponseBody
    public List<Node> getFrontMenu(HttpSession session) {
        //先查缓存
        String nodes = redisService.get(KEY_MENU_NODES);
        if (!StringUtils.isEmpty(nodes)) {
            //缓存命中,转化为json返回
            return JSON.parseArray(nodes, Node.class);
        } else {
            List<Menu> menus = menuService.queryFrontMenu();
            List<Node> nodeList = menuService.getMenusNode(menus);
            //设置到缓存中
            redisService.set(KEY_MENU_NODES, JSON.toJSONString(nodeList), EXPIRE_TIME);
            return nodeList;
        }
    }

    @RequestMapping("get-back-menu")
    @ResponseBody
    public List<Node> getBackMenu() {
        List<Menu> menus = menuService.queryBackMenu();
        List<Node> nodes = menuService.getMenusNode(menus);
        return nodes;
    }


    /**
     * 获得某个角色的菜单
     *
     * @return
     */
    @RequestMapping("get-role-menu")
    @ResponseBody
    public List<Node> getRoleMenu(Integer roleId, Integer location) {
        //获得前台或后台的菜单
        List<Menu> menus = null;
        if (location == 1) {
            menus = menuService.queryFrontMenu();
        } else {
            menus = menuService.queryBackMenu();
//            List<Menu> privilegeMenu = menuService.queryPrivilegeMenu();
//            menus.addAll(privilegeMenu);
        }
        //获得角色的菜单
        List<Menu> roleMenus = menuService.findMenusByRoleId(roleId);

        //将属于角色的状态置于选中状态
        for (Menu menu : menus) {
            for (Menu roleMenu : roleMenus) {
                if (menu.getId().equals(roleMenu.getId())) {
                    menu.setChecked(true);
                    break;
                }
            }
        }
        List<Node> nodes;
        if (location == 1) {
            nodes = menuService.getMenusNode(menus);
        } else {
            nodes = menuService.getMenusAndPermsNode(menus, roleId);
        }
        return nodes;
    }

    @RequestMapping("updateMenu")
    @ResponseBody
    public void updateMenu(Integer id, String name, String url, String icon, Integer canPublish, Integer isHidden) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("url", url);
        params.put("icon", icon);
        params.put("canPublish", canPublish);
        params.put("isHidden", isHidden);
        menuService.updateMenu(params);
        //更新菜单后删除缓存
        redisService.delete(KEY_MENU_NODES);
    }



    @RequestMapping("getSecondMenuByMenuName")
    @ResponseBody
    public List<Menu> getSecondMenuByMenuName(String menuName) {
        if (menuName != null && !menuName.equals("")) {
            List<Menu> menus = menuService.getSecondMenuByMenuName(menuName);
            return menus;
        }
        return null;
    }

    @RequestMapping("getSecondMenu")
    @ResponseBody
    public List<Node> getSecondMenu(Integer menuId) {
        List<Menu> menus = menuService.queryFrontMenu();
        List<Node> nodes = menuService.getMenusNode(menus);
        return nodes.get(menuId).getNodes();
    }

}

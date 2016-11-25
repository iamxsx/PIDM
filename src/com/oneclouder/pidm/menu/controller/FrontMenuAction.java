package com.oneclouder.pidm.menu.controller;

import com.alibaba.fastjson.JSON;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Node;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.redis.service.IRedisService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by clouder on 16-10-26.
 */
@Controller
@RequestMapping("menu")
public class FrontMenuAction {

    @Resource
    private IMenuService menuService;

    @Resource
    private IRedisService redisService;

    public static final String KEY_MENU_NODES = "KEY_MENU_NODES";

    public static final Integer EXPIRE_TIME = 60 * 60 * 24 * 7;
    public static final String KEY_DEFAULT_MENU_NODES = "KEY_DEFAULT_MENU_NODES";


    @RequestMapping("get-front-menu")
    @ResponseBody
    public List<Node> getFrontMenu(HttpSession session) {
        //根据当前登陆的角色,查询出响应的菜单
        NUserInfo userInfo = (NUserInfo) session.getAttribute("userInfo");
        /*String key;
        Integer roleId = 34;
        if (userInfo == null) {
            key = KEY_DEFAULT_MENU_NODES;
        } else {
            roleId = userInfo.getUserRole();
            key = KEY_MENU_NODES + roleId;
        }
        //先查缓存
        String nodes = redisService.get(key);
        if (!StringUtils.isEmpty(nodes)) {
            //缓存命中,转化为json返回
            return JSON.parseArray(nodes, Node.class);
        } else {
            //List<Menu> menus = menuService.queryFrontMenu();
            List<Menu> menus = menuService.findMenusByRoleId(roleId);
            List<Node> nodeList = menuService.getMenusNode(menus);
            //设置到缓存中
            redisService.set(key, JSON.toJSONString(nodeList), EXPIRE_TIME);
            return nodeList;
        }*/
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
}

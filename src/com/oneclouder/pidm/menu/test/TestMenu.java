package com.oneclouder.pidm.menu.test;

import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Node;
import com.oneclouder.pidm.menu.model.Privilege;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.menu.service.IPrivilegeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by clouder on 16-9-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestMenu {

    @Resource(name = "menuService")
    private IMenuService menuService;

    @Autowired
    private IPrivilegeService privilegeService;

    @Test
    public void testQueryMenuById(){
        Menu menu = menuService.queryMenuById(1);
        System.out.println(menu);
    }

    @Test
    public void testFindByParent() throws SQLException {
        List<Menu> menuList = menuService.findMenuByParent(null, Menu.LOCATION_BACK);
        menuList.forEach(System.out::println);
    }

    @Test
    public void testFindByEmployee() throws SQLException {
        List<Menu> menuList = menuService.findMenuByEmployee(1, 2);
        menuList.forEach(System.out::println);
    }

    @Test
    public void testPrivFindByEmployee() throws SQLException {
        List<Menu> privilegeList = menuService.findMenuByEmployee(1,Menu.LOCATION_PRIVILEGE);
        privilegeList.forEach(System.out::println);
    }


    @Test
    public void testQueryMenu(){
        /*List<Menu> menus = menuService.queryMenu();
        List<Node> root = new LinkedList<>();
        Map<Integer,Node> nodeList = new HashMap();
        for (Menu menu : menus){
            Node node = new Node();
            node.setId(menu.getId());
            node.setText(menu.getName());
            node.setUrl(menu.getUrl());
            node.setParentId(menu.getParentId());
            nodeList.put(node.getId(),node);
        }

        Set<Map.Entry<Integer,Node>> set = nodeList.entrySet();
        for (Iterator it = set.iterator(); it.hasNext();){
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.getParentId() == null) {
                root.add(node);
            } else {
                nodeList.get(node.getParentId()).getNodes().add(node);
            }
        }
        System.out.println(root.toString());
*/
    }

    @Test
    public void testEquals(){
        Integer i = 130;
        Integer j = 130;
        System.out.println(j.equals(i));
    }
}


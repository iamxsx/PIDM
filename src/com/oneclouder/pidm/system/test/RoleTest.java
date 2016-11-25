package com.oneclouder.pidm.system.test;

import com.oneclouder.pidm.system.model.Role;
import com.oneclouder.pidm.system.service.IRoleService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by clouder on 16-9-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RoleTest {

    @Resource(name = "roleService")
    private IRoleService roleService;

    @Test
    public void testFindRoles(){
        Map<String,Object> params = new HashMap<>();
        params.put("offset",0);
        params.put("limit",10);
        //List<Role> roles = roleService.findRoles(params);
        //System.out.println(roles);
    }

    @Test
    public void testUpdateRoleMenus(){
        String ids = "51,52,53";
        //roleService.updateRoleMenus(26,ids);
    }
}

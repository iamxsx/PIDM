package com.oneclouder.pidm.system.controller;

import com.oneclouder.pidm.redis.service.IRedisService;
import com.oneclouder.pidm.system.model.Role;
import com.oneclouder.pidm.system.service.IRoleService;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.oneclouder.pidm.index.filter.PermissionFilter.KEY_ROLE_MENUS;

/**
 * Created by clouder on 16-9-19.
 */
@Controller
@RequestMapping("/back/role")
public class RoleAction {

    @Resource
    private IRoleService roleService;
    @Resource
    private IRedisService redisService;

    @RequestMapping("")
    public String toRoleManage(ModelAndView modelAndView){
        return "back/system/role";
    }

    @RequestMapping("findRoles")
    @ResponseBody
    public List<Role> findRoles(){
        List<Role> roles = roleService.findRoles();
        int total = roleService.getRoleCount();
        /*Map<String,Object> map = new HashMap<>();
        map.put("rows",roles);
        map.put("total",total);*/
        return roles;
    }


    /**
     * 绑定角色和菜单
     * @param roleName 角色名
     * @param location　前或后台角色
     * @param ids　各个菜单的ｉｄ
     */
    @RequestMapping("bindRoleMenus")
    @ResponseBody
    public void bindRoleMenus(String roleName,Integer location,String ids){
        //先添加角色
        Role role = new Role();
        role.setName(roleName);
        role.setLocation(location);
        roleService.addRole(role);
        List<String> _ids = Arrays.asList(ids.split(","));
        Map<String,Object> params = new HashMap<>();
        params.put("roleId",role.getId());
        params.put("ids",_ids);
        roleService.bindRoleMenus(params);
    }

    @RequestMapping("updateRoleMenus")
    @ResponseBody
    public void updateRoleMenus(Integer roleId,String roleName,String ids){
        roleService.updateRoleMenus(roleId,roleName,ids);
        //删除缓存
        redisService.delete(KEY_ROLE_MENUS + roleId);
    }


    @RequestMapping("delete-role")
    @ResponseBody
    public void deleteRole(Integer id){
        roleService.deleteRole(id);
        //删除缓存
        redisService.delete(KEY_ROLE_MENUS + id);
    }




}

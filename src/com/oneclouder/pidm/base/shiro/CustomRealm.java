package com.oneclouder.pidm.base.shiro;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Privilege;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.menu.service.IPrivilegeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by PhychoLee on 9/12/16 11:40 AM.
 * Description:自定义Shiro Realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IPrivilegeService privilegeService;

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     * 授权认证
     * @param
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Employee employee = (Employee) principals.getPrimaryPrincipal();

        List<Menu> privilegeList = null;
        try {
            privilegeList = menuService.findMenuByEmployee(employee.getId(), Menu.LOCATION_PRIVILEGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> privilegeUrls = new ArrayList<>();
        if (privilegeList != null){
//            privilegeUrls.addAll(privilegeList.stream().map(Menu::getUrl).collect(Collectors.toList()));
            for (Menu menu : privilegeList){
                privilegeUrls.add(menu.getUrl());
            }
        }

        SimpleAuthorizationInfo sAInfo = new SimpleAuthorizationInfo();
        sAInfo.addStringPermissions(privilegeUrls);

        return sAInfo;
    }

    /**
     * 登陆认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取登陆用户名
        String username = (String) token.getPrincipal();

        //查询用户名是否为空
        Employee currentEmployee = null;
        try {
            currentEmployee = employeeService.findByUserName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(currentEmployee == null){
            return null;
        }

        String password = currentEmployee.getPassword();
        String salt = currentEmployee.getSalt();

        List<Menu> menuList = null;
        try {
            menuList = menuService.findMenuByEmployee(currentEmployee.getId(), Menu.LOCATION_BACK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将菜单集合给当前登陆员工
        currentEmployee.setMenuList(menuList);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                currentEmployee, password, ByteSource.Util.bytes(salt), this.getName()
        );
        return simpleAuthenticationInfo;
    }
}

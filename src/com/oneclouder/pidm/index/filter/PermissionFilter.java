package com.oneclouder.pidm.index.filter;

import com.alibaba.fastjson.JSON;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Node;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.menu.service.impl.MenuServiceImpl;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.redis.service.IRedisService;
import com.oneclouder.pidm.redis.service.impl.RedisServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 拦截菜单
 * Created by clouder on 16-10-26.
 */
@Component("permissionFilter")
public class PermissionFilter implements Filter{

    public static final String KEY_ROLE_MENUS = "KEY_ROLE_MENUS";
    public static final Integer EXPIRE_TIME = 60 * 60 * 24 * 7;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        IMenuService menuService = wc.getBean("menuService", MenuServiceImpl.class);
        IRedisService redisService = wc.getBean("redisService", RedisServiceImpl.class);

        //获取当前访问的链接参数
        String queryString = request.getQueryString();
        queryString = URLDecoder.decode(queryString,"utf-8");
        //获取菜单部分
        String menuString = StringUtils.split(queryString,"&")[0];
        //获得当前用户的角色
        NUserInfo userInfo = (NUserInfo) request.getSession().getAttribute("userInfo");
        Integer roleId = 34;
        String key;
        if (userInfo == null) {
            key = KEY_ROLE_MENUS + roleId;
        } else {
            //拿到当前用户的 roleId
            roleId = userInfo.getUserRole();
            key = KEY_ROLE_MENUS + roleId;
        }
        //先查缓存
        List<Menu> menus = null;
        String nodes = redisService.get(key);
        if (!StringUtils.isEmpty(nodes)) {
            //缓存命中,转化为json返回
            menus = JSON.parseArray(nodes, Menu.class);
        } else {
            menus = menuService.findMenusByRoleId(roleId);
            //设置到缓存中
            redisService.set(key, JSON.toJSONString(menus), EXPIRE_TIME);
        }
        //查询该角色所拥有的菜单
        for (Menu menu :menus){
            if (menu.getUrl().indexOf(menuString) > 0){
                //放行
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        ((HttpServletResponse)servletResponse).sendRedirect(request.getContextPath() + "/refuse.jsp");
    }

    @Override
    public void destroy() {

    }
}

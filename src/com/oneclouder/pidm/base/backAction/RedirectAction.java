package com.oneclouder.pidm.base.backAction;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by PhychoLee on 9/22/16 10:32 AM.
 * Description:
 */
@Controller
@RequestMapping("/back")
public class RedirectAction {

    @Autowired
    private IMenuService menuService;

    /**
     * 客户管理
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public String client(@PathVariable("id") Integer id, HttpServletRequest request){
        menuService.setBackMenu(request);

        List<Menu> secondMenuList = getSecondMenuList(id);
        if (secondMenuList != null && secondMenuList.size() != 0) {
            for (Menu menu : secondMenuList) {
                return "redirect:/back"+menu.getUrl();
            }
        }
        return "redirect:/back/user/clientList";
    }

    @RequestMapping(value = "/communicate/{id}", method = RequestMethod.GET)
    public String communicate(@PathVariable("id") Integer id, HttpServletRequest request){
        menuService.setBackMenu(request);

        List<Menu> secondMenuList = getSecondMenuList(id);
        if (secondMenuList != null && secondMenuList.size() != 0) {
            for (Menu menu : secondMenuList) {
                return "redirect:/back"+menu.getUrl();
            }
        }
        return "redirect:/back/communicate/showList";
    }

    /**
     * 信息发布
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/publish/{id}", method = RequestMethod.GET)
    public String publish(@PathVariable("id") Integer id, HttpServletRequest request){
        menuService.setBackMenu(request);

        List<Menu> secondMenuList = getSecondMenuList(id);
        if (secondMenuList != null && secondMenuList.size() != 0) {
            for (Menu menu : secondMenuList) {
                return "redirect:/back"+menu.getUrl();
            }
        }
        return "redirect:/back/article/to-publish";
    }

    /**
     * 我的工作台
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/work/{id}", method = RequestMethod.GET)
    public String work(@PathVariable("id") Integer id, HttpServletRequest request){
        menuService.setBackMenu(request);
        List<Menu> secondMenuList = getSecondMenuList(id);
        if (secondMenuList != null && secondMenuList.size() != 0) {
            for (Menu menu : secondMenuList) {
                return "redirect:/back"+menu.getUrl();
            }
        }
        return "redirect:/back/work/to-do-list";
    }


    /**
     * 下载中心
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String donwloadCenter(@PathVariable("id") Integer id, HttpServletRequest request){
        menuService.setBackMenu(request);
        return "redirect:/back/download-center";
    }

    /**
     * 系统管理
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/system/{id}", method = RequestMethod.GET)
    public String system(@PathVariable("id") Integer id, HttpServletRequest request){
        menuService.setBackMenu(request);

        List<Menu> secondMenuList = getSecondMenuList(id);
        if (secondMenuList != null && secondMenuList.size() != 0) {
            for (Menu menu : secondMenuList) {
                return "redirect:/back"+menu.getUrl();
            }
        }
        return "redirect:/back/refuse";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String back(HttpServletRequest request){
        menuService.setBackMenu(request);
        return "/back/welcome";
    }

    @RequestMapping(value = "/refuse", method = RequestMethod.GET)
    public String refuse(HttpServletRequest request){
        menuService.setBackMenu(request);
        return "/back/refuse";
    }

    @RequestMapping(value = "/welcome")
    public String welcome(HttpServletRequest request){
        menuService.setBackMenu(request);
        return "/back/welcome";
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
        secondMenuList.addAll(menuList.stream().filter(menu -> menu.getParentId() == id).collect(Collectors.toList()));

        return secondMenuList;
    }

    /**
     * 调查表管理
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/survey/{id}", method = RequestMethod.GET)
    public String survey(@PathVariable("id") Integer id, HttpServletRequest request){
        menuService.setBackMenu(request);

        List<Menu> secondMenuList = getSecondMenuList(id);
        if (secondMenuList != null && secondMenuList.size() != 0) {
            /*for (Menu menu : secondMenuList) {
                return "redirect:"+menu.getUrl();
            }*/
        }
        return "redirect:/back/survey/surveyManage";
    }
}

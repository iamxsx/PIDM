package com.oneclouder.pidm.n_user.controller;

import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INCompanyEmployeeService;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.n_user.web_bean.RegisterFormBean;
import com.oneclouder.pidm.user.annotation.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/15/16.
 */
@Controller
@RequestMapping("/back/user")
public class ClientAction {

    @Resource(name = "nUserService")
    private INUserService nUserService;

    @Resource(name = "nCompanyEmployeeService")
    private INCompanyEmployeeService nCompanyEmployeeService;

    @Resource(name = "nCompanyService")
    private INCompanyService nCompanyService;

    /**
     * 跳转到客户信息页面
     * @return
     */
    @RequestMapping(value = "clientList",method = RequestMethod.GET)
    public String clientList(){
        return "/back/client/clientList";
    }

    /**
     * 查看详细信息
     * @param uid
     */
    @RequestMapping("clientRecordInfo")
    public ModelAndView clientInfo(ModelAndView model,Integer uid){
        model.addObject("clientInfo",nUserService.selectClientAll(uid));
        model.setViewName("/back/client/clientInfo");
        return model;
    }

    /**
     * 跳转到后台添加公司帐号页面
     */
    @RequestMapping("getCompany")
    public ModelAndView addCompany(ModelAndView model){
        model.setViewName("/back/client/addClient");
        return model;
    }

    /**
     * 跳转到修改客户信息页面
     * @param uid
     */
    @RequestMapping("changeclient")
    public ModelAndView changeclient(ModelAndView model,Integer uid) throws SQLException {
        List<Map> clientInfo = nUserService.selectClientAll(uid);
        Integer desNum = 0;
        Integer introNum = 0;
        for (Map<String,Object> empInfo:clientInfo) {
            Integer nature = (Integer) empInfo.get("emnature");
            if(nature != null){
                if (nature == 0){
                    desNum++;
                }else if(nature == 1){
                    introNum++;
                }
            }
        }
        clientInfo.get(0).put("des",desNum);
        clientInfo.get(0).put("intro",introNum);
        model.addObject("clientInfo",clientInfo);
        model.setViewName("/back/client/changeClient");
        return model;
    }

    /**
     * 删除帐号
     * @param uid
     */
    @RequestMapping("deleteUserInfo")
    @ResponseBody
    public void deleteUserInfo(Integer uid){
        nUserService.deleteUserInfo(uid);
    }

    /**
     * 导出客户信息(excel)
     * @param searchCondition   搜索条件
     * @param keyWord       搜索关键字
     */
    @RequestMapping("getUserSimpleInfo")
    public void getUserSimpleInfo(Integer searchCondition, String keyWord, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        nUserService.getUserSimpleInfo(searchCondition,keyWord,response);
    }

    /**
     * 导出客户信息(PDF)
     * @param uid
     */
    @RequestMapping("exportPDF")
    @ResponseBody
    public void exportPDF(HttpServletResponse response,Integer uid){
        nUserService.exportPDF(response,uid);
    }

    /**
     * 分页查询客户信息
     * @param keyWord  搜索关键字
     * @param searchCondition   搜索条件
     * @param offset    从第几条开始查
     * @param limit     每页显示记录条数
     */
    @RequestMapping("getUserSimpleInfoLimit")
    @ResponseBody
    public Map<String, Object> getUserSimpleInfoLimit(String keyWord, Integer searchCondition, Integer offset, Integer limit){
        Map<String,Object> mapParam = new LinkedHashMap<>();
        mapParam.put("searchCondition",searchCondition);
        mapParam.put("keyWord",keyWord);
        mapParam.put("offset",offset);
        mapParam.put("limit",limit);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("rows",nUserService.getUserSimpleInfoLimit(mapParam));
        map.put("total",nUserService.getUserSimpleInfoLimit(mapParam).size());
        return map;
    }

    /**
     * 修改客户信息
     * @param changeInfo
     * @return
     */
    @RequestMapping("changeUser")
    public String changeUser(RegisterFormBean changeInfo,Integer desNum,Integer introNum) throws SQLException {
//        Integer desNums = Integer.parseInt(desNum);
//        Integer introNums = Integer.parseInt(introNum);
        nUserService.changeClientInfo(changeInfo,desNum,introNum);
        return "redirect:clientRecordInfo?uid="+changeInfo.getUser().getId();
    }

    /**
     * 后台添加客户
     * @param formBean
     * @return
     * @throws SQLException
     */
    @RequestMapping("addUser")
    public String addUser(RegisterFormBean formBean) throws SQLException {
        nUserService.addClient(formBean);
        return "redirect:clientList";
    }
}

/*
package com.oneclouder.pidm.user.action;

import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import com.oneclouder.pidm.user.model.Description;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.service.ICompanyEmployeeService;
import com.oneclouder.pidm.user.service.ICompanyService;
import com.oneclouder.pidm.user.service.IDescriptionService;
import com.oneclouder.pidm.user.service.IUserService;
import com.oneclouder.pidm.user.webBean.RegisterFormBean;
import com.oneclouder.pidm.user.webBean.UserSimpleInfo;
import com.oneclouder.pidm.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

*/
/**后台客户管理
 * Created by clouder on 9/12/16.
 *//*

@Controller
@RequestMapping("back/user")
public class UserAction {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name="descriptionService")
    private IDescriptionService descriptionService;

    @Resource(name="companyEmployeeService")
    private ICompanyEmployeeService companyEmployeeService;

    @Resource(name = "companyService")
    private ICompanyService companyService;

    @RequestMapping(value = "clientList",method = RequestMethod.GET)
    public String clientList(){
        return "/back/client/clientList";
    }

    */
/**
     * 获取已注册公司
     * @param model
     * @return
     *//*

    @RequestMapping(value = "getCompany", method = RequestMethod.GET)
    public String registeredCompanies(Model model) throws SQLException {
        List<Company> companies = new ArrayList<>();
        companies = companyService.findAllCompanies();
        model.addAttribute("companies", companies);
        return "/back/client/clientAddStep-1";
    }

    @RequestMapping(value = "exist-company", method = RequestMethod.POST)
    public String existCompany(Company company, Model model) throws SQLException {
        if (companyService.companyIsExist(company.getName())) {
            */
/*
            * traget:0表示新注册公司用户注册
            *        1表示已存在公司用户注册
            *//*

//            model.addAttribute("traget", 1);
            Company company1 = companyService.findCompanyByName(company.getName());
            model.addAttribute("company", company1);
        } else {
//            model.addAttribute("traget", 0);
            model.addAttribute("company", company);
        }
        return "/back/client/clientAddStep-2";
    }


    @RequestMapping(value = "add-user", method = RequestMethod.POST)
    @Transactional
    public void addUser(RegisterFormBean registerFormBean, HttpSession session,HttpServletResponse response) throws SQLException, IOException {
        userService.register(registerFormBean,session);
        response.sendRedirect("/PIDM/user/clientList");
    }

    @RequestMapping(value = "changeclient",method = RequestMethod.GET)
    @Transactional
    public ModelAndView changeclient(ModelAndView model,Integer uid){
        User user = userService.getUserInfoEle(uid);
        CompanyEmployee companyEmployee = companyEmployeeService.getLegalemployeeBycompayId(user.getCompany().getId());
        model.addObject(user);
        model.addObject(companyEmployee);
        Integer count = descriptionService.getDescriptionLength(user.getCompany().getId(),user.getAssociationId());
        if (count>0) {
            Description description = descriptionService.getDescriptionByAssAndComId(user.getCompany().getId(),user.getAssociationId());
            model.addObject(description);
        }
        model.setViewName("/back/client/changeclient");
        return model;
    }

    */
/**
     * 得到客户详细信息
     * @param model
     * @param uid
     * @return
     *//*

    @RequestMapping(value = "clientRecordInfo",method = RequestMethod.GET)
    public ModelAndView clientRecordInfo(ModelAndView model,Integer uid){
        User user = userService.getUserInfoEle(uid);
        CompanyEmployee companyEmployee = companyEmployeeService.getLegalemployeeBycompayId(user.getCompany().getId());
        Integer count = descriptionService.getDescriptionLength(user.getCompany().getId(),user.getAssociationId());
        model.addObject(user);
        model.addObject(companyEmployee);
        if (count>0){
            Description description = descriptionService.getDescriptionByAssAndComId(user.getCompany().getId(),user.getAssociationId());
            model.addObject(description);
        }

        model.setViewName("/back/client/clientRecordInfo");
        return model;
    }

    */
/**
     * 导出客户信息为PDF
     * @param uid
     *//*

    @RequestMapping("/exportPDF")
    @ResponseBody
    public void exportPDF(Integer uid,HttpServletRequest request,HttpServletResponse response){
        User user = userService.getUserInfoEle(uid);
        CompanyEmployee companyEmployee = companyEmployeeService.getLegalemployeeBycompayId(user.getCompany().getId());
        Description description = descriptionService.getDescriptionByAssAndComId(user.getCompany().getId(),user.getAssociationId());
        List<Object> Info = new ArrayList<>();
        Info.add(user);
        Info.add(companyEmployee);
        Info.add(description);
        userService.exportPDF(Info,response);
    }

    */
/**
     * 导出客户记录为excel
     * @param keyWord
     * @param searchCondition

     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *//*

    */
/*@RequestMapping("/getUserSimpleInfo")
    @ResponseBody
    public void getUserSimpleInfo(String keyWord, Integer searchCondition,HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        UserSimpleInfo userSimpleInfo = new UserSimpleInfo();
        userSimpleInfo.setKeyWord(keyWord);
        userSimpleInfo.setSearchCondition(searchCondition);
        userService.getUserSimpleInfo(userSimpleInfo,response);
    }*//*


    */
/**
     * 分页查询客户信息
     * @param keyWord
     * @param searchCondition
     * @param offset
     * @param limit
     * @return
     *//*

    @RequestMapping("/getUserSimpleInfoLimit")
    @ResponseBody
    public Map<String,Object> getUserSimpleInfoLimit(String keyWord, Integer searchCondition, Integer offset, Integer limit){
        UserSimpleInfo userSimpleInfo = new UserSimpleInfo();
        userSimpleInfo.setKeyWord(keyWord);
        userSimpleInfo.setSearchCondition(searchCondition);
        userSimpleInfo.setLimitNumber(limit);
        userSimpleInfo.setLimitFrom(offset);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("rows",userService.getUserSimpleInfoLimit(userSimpleInfo));
        map.put("total",userService.getUserSimpleInfoLimit(userSimpleInfo).size());
        return map;
    }

    */
/**
     * 修改客户信息
     * @param InfoList
     * @return
     *//*

    @RequestMapping("/changeUser")
    @ResponseBody
    @Transactional
    public Integer changeUserInfo(String[] InfoList){
        return userService.changeUserInfo(InfoList);
    }

    */
/**
     * 删除客户记录
     * @param uid
     *//*

    @RequestMapping("/deleteUserInfo")
    @ResponseBody
    @Transactional
    public void deleteUserInfo(Integer uid){
        userService.deleteUserInfo(uid);
    }

    */
/**
     * 获得客户记录条数
     * @param limitNumber
     * @return
     *//*

    */
/*@RequestMapping("/getUserCount")
    @ResponseBody
    public Integer getUserCount(Integer limitNumber){
        Double pageNumberDouble = Math.ceil(userService.userInfoNum()/limitNumber);
        Integer pageNumber = pageNumberDouble.intValue();
        return pageNumber;
    }*//*


}
*/

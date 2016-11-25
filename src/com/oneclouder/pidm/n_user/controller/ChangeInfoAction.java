package com.oneclouder.pidm.n_user.controller;

import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INCompanyEmployeeService;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.n_user.web_bean.RegisterFormBean;
import com.oneclouder.pidm.user.webBean.UserInfo;
import com.oneclouder.pidm.util.PropertiesUtil;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zheng.
 */
@Controller
@RequestMapping(value = "/nUser")
public class ChangeInfoAction {

    @Resource(name = "nUserService")
    INUserService nUserService;

    @Resource(name = "nCompanyEmployeeService")
    INCompanyEmployeeService nCompanyEmployeeService;

    @Resource(name = "nCompanyService")
    INCompanyService nCompanyService;

    @Resource(name = "workFlowService")
    private IWorkFlowService workFlowService;

    /**
     * 查看详细信息
     */
    @RequestMapping("showNUserInfo")
    public ModelAndView showNUserInfo(ModelAndView model,HttpSession session) throws SQLException{
        // TODO: 16-10-18 用户session
        NUserInfo u = (NUserInfo) session.getAttribute("userInfo");
        if(u != null) {
            Integer uId = u.getId();
            model.addObject("clientInfo", nUserService.selectClientAll(uId));
            model.setViewName("/foreground/n_user/showInfo");
        }else {
            model.setViewName("/foreground/index");
        }
        return model;
    }

    /**
     * 修改用户信息*
     **/
    @RequestMapping(value = "changeNUserInfo")
    public ModelAndView changeNUserInfo(ModelAndView model, HttpSession session) throws SQLException {
        // TODO: 16-10-15   用户session
        NUserInfo u = (NUserInfo) session.getAttribute("userInfo");
        if(u != null){
            int uId = u.getId();
            NUser nUser = nUserService.findById(uId);
            List<NCompanyEmployee> nCompanyEmployees = nCompanyEmployeeService.getCompanyEmployeeByCId(nUser.getCompany().getId());
            List<NCompanyEmployee> designatedContact = new ArrayList<>();
            List<NCompanyEmployee> introduceds = new ArrayList<>();
            for(NCompanyEmployee nCompanyEmployee : nCompanyEmployees){
                if(nCompanyEmployee.getNature() == 0){
                    designatedContact.add(nCompanyEmployee);
                }
                if(nCompanyEmployee.getNature() == 1){
                    introduceds.add(nCompanyEmployee);
                }
                if(nCompanyEmployee.getNature() == 2){
                    model.addObject("legalRep",nCompanyEmployee);
                }
            }
            model.addObject("designatedContact",designatedContact);
            model.addObject("introduceds",introduceds);
            //model封装数据到前台
            model.addObject("nUser",nUser);
            model.addObject("nCompany",nUser.getCompany());
            model.setViewName("/foreground/n_user/changeInfo");
            }else{
                model.setViewName("/foreground/index");
            }
            return model;
    }


    /**
     * 修改用户信息 到临时表
     */
    @RequestMapping(value = "saveNUserAllInfoToTemp")
    public ModelAndView saveNUserAllInfoToTemp(ModelAndView model, RegisterFormBean formBean, HttpServletRequest request, HttpSession session) throws Exception {
        /**存公司信息入 临时表   nTempUId为临时表用户ID**/
        Integer nTempUId = nUserService.saveNUserAllInfoToTemp(formBean,request);
        System.out.println("临时表用户ID ： "+nTempUId);
        // TODO: 16-10-18 把临时表用户ID 给工作流
        workFlowService.userUpdate(nTempUId,formBean.getUser().getAccount(),"User","Update");
        model.addObject("msg","提交修改信息申请成功！请耐心系统等待审核");
        model.setViewName("/foreground/n_user/changeSubmitMsg");
        return model;
    }

    //################ hxj #################

    /**
     * 跳转到前台修改被驳回信息页面
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("CheckUserInfo")
    public ModelAndView changeUserInfo(ModelAndView model,Integer uid) throws SQLException {
        List<Map> clientInfo = nUserService.selectTUserAll(uid);
        model.addObject("clientInfo",clientInfo);
        model.setViewName("/back/client/CheckUserInfo");
        return model;
    }


    @RequestMapping(value = "changeUserCheck")
    public String changeUserCheck(HttpServletRequest request, RegisterFormBean registerFormBean) throws Exception {
        String location = PropertiesUtil.getProperty("APPLICATION_PATH");
        nUserService.changeUserCheck(registerFormBean,request,location,registerFormBean.getUser().getId());
        workFlowService.userUpdate(registerFormBean.getUser().getId(),registerFormBean.getUser().getAccount(),"User","Insert");
        return "redirect:../work/to-personal";
    }



}

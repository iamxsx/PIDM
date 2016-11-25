package com.oneclouder.pidm.user.action;

import com.oneclouder.pidm.t_user.model.*;
import com.oneclouder.pidm.t_user.service.IInformationService;
import com.oneclouder.pidm.user.dao.ICompanyEmployeeDao;
import com.oneclouder.pidm.user.dao.IUserDao;
import com.oneclouder.pidm.user.dao.IUserTemporaryDao;
import com.oneclouder.pidm.user.model.*;
import com.oneclouder.pidm.user.service.*;
import com.oneclouder.pidm.user.webBean.CheckUser;
import com.oneclouder.pidm.user.webBean.UserInfo;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zheng.
 */
@Controller
@RequestMapping("/user")
public class ChangeUserInfo {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name="descriptionService")
    private IDescriptionService descriptionService;

    @Resource(name="companyEmployeeService")
    private ICompanyEmployeeService companyEmployeeService;

    @Resource(name = "companyEmployeeDao")
    private ICompanyEmployeeDao companyEmployeeDao;

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Resource(name = "userTemporaryDao")
    private IUserTemporaryDao userTemporaryDao;

    @Resource(name = "informationService")
    private IInformationService informationService;

    @Resource(name = "userTemporaryService")
    private IUserTemporaryService userTemporaryService;

    @Resource(name = "workFlowService")
    private IWorkFlowService workFlowService;

    @Resource(name = "associationUnitService")
    private IAssociationUnitService associationUnitService;

    @Resource(name = "companyService")
    private ICompanyService companyService;

    /**
     * 前台用户查看个人信息
     * @param model
     * @return
     */
    @RequestMapping(value = "showUserInfo",method = RequestMethod.GET)
    public ModelAndView showUserInfo(ModelAndView model, HttpSession session){
        //获得用户ID
        UserInfo u = (UserInfo) session.getAttribute("userInfo");
        if(u != null) {
            int uId = u.getId();

            User user = userService.getUserInfoEle(uId);
            CompanyEmployee companyEmployee = companyEmployeeService.getLegalemployeeBycompayId(user.getCompany().getId());
            Description description = descriptionService.getDescriptionByAssAndComId(user.getCompany().getId(), user.getAssociationId());
            model.addObject(user);
            model.addObject(companyEmployee);
            model.addObject(description);
            model.setViewName("/foreground/user/showUserInfo");
            return model;
        }else {
            model.setViewName("/foreground/index");
            return model;
        }
    }

    /**
     * 前台用户信息修改
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping(value = "changeUserInfo",method = RequestMethod.GET)
    @Transactional
    public ModelAndView changeUserInfo(ModelAndView model,Integer uid){
        User user = userService.getUserInfoEle(uid);
        CompanyEmployee companyEmployee = companyEmployeeService.getLegalemployeeBycompayId(user.getCompany().getId());
        Description description = descriptionService.getDescriptionByAssAndComId(user.getCompany().getId(),user.getAssociationId());
        model.addObject(user);
        model.addObject(companyEmployee);
        model.addObject(description);
        model.setViewName("/foreground/user/changeUserInfo");
        return model;
    }

    /**
     * 保存修改 指定人 信息
     */
    @RequestMapping(value = "saveCompanyEmployee")
    @ResponseBody
    @Transactional
    public Map<String,Integer> addEmployee(String name,String jobPosition,String officePhoneNum,String cellPhoneNum,String email,String faxNum, Integer companyId) throws SQLException {
        CompanyEmployee companyEmployee = new CompanyEmployee();
        Company company = new Company();
        company.setId(companyId);
        companyEmployee.setCompany(company);
        companyEmployee.setName(name);
        companyEmployee.setJobPosition(jobPosition);
        companyEmployee.setEmail(email);
        companyEmployee.setOfficePhoneNum(officePhoneNum);
        companyEmployee.setCellPhoneNum(cellPhoneNum);
        companyEmployee.setFaxNum(faxNum);
        companyEmployee.setNature(0);

        companyEmployeeDao.insert(companyEmployee);
        //返回主键
        Map<String,Integer> map = new HashMap<String,Integer>();
//        System.out.println(companyEmployee.getId());
        map.put("companyEmployeeId",companyEmployee.getId());
        return map;
    }

    /**
     * 保存修改信息 到 用户临时表
     */
    @RequestMapping(value = "savaUserTemporary")
    @ResponseBody
    @Transactional
    public Map<String,Object> savaUserTemporary(Integer designatedContactId,Integer uId,String realName,String phoneNum,String IDcard,String email){
        Map<String,Object> json = new HashMap<String,Object>();

        User user = userDao.getUserInfoEle(uId);
        user.setRealName(realName);
        user.setPhoneNum(phoneNum);
        user.setIDcard(IDcard);
        user.setEmail(email);
        CompanyEmployee a =new CompanyEmployee();
        a.setId(designatedContactId);
        user.setDesignatedContact(a);
        userTemporaryDao.insertById(user);
        int userTemporaryId = user.getId();
        // TODO: 16-9-22 把临时表ID 传到工作流
        workFlowService.userUpdate(userTemporaryId,user.getAccount(),"User","Update");

        json.put("userTemporaryId",userTemporaryId);

        return json;
    }

    /**
     * 工作流审批过  从临时表修改用户表
     */
//    @RequestMapping(value = "temporaryToUser")
//    public void temporaryToUser(Integer userTemporaryId){
//        UserTemporary userTemporary = userTemporaryDao.findUserTemporaryById(userTemporaryId);
//        Map<String,Object> params = new HashMap<String,Object>();
//        params.put("realName",userTemporary.getRealName());
//        params.put("phoneNum",userTemporary.getPhoneNum());
//        params.put("IDcard",userTemporary.getIDcard());
//        params.put("email",userTemporary.getEmail());
//        params.put("designatedContact",userTemporary.getDesignatedContact().getId());
//        //通过临时表的帐号 获得用户ID
//        int uId = userTemporaryDao.findUIdByTemporaryAccount(userTemporary.getAccount());
//
//        params.put("uId",uId);
//        //user修改信息更改 ，在userTemporaryDao 里面执行  避免冲突
//        userTemporaryDao.updateTemporaryToUser(params);
//    }

   /*     params.put("uId",uId);
        //user修改信息更改 ，在userTemporaryDao 里面执行  避免冲突
        userTemporaryDao.updateTemporaryToUser(params);
    }
*/
    /**
     * 显示用户全部信息
     * @param t_id
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkInfo" ,method = RequestMethod.GET)
    public ModelAndView info(@RequestParam(value = "t_id",required = true)Integer t_id, ModelAndView model)
    {
        Map<String,Object> map = new HashMap();
        try {
            /**
             * 获取用户临时表的User信息
             */
            User_t t_user  = informationService.findtUserpById(t_id);
            /**
             * 获取临时表中的账号，用于查询正式表，获取该用户在正式表中的id
             * 该id用于查询推荐表．
             */
            String account = t_user.getAccount();

            Integer id = informationService.findUserIdBytAccount(account);
            /**
             *   获取正式表中User对象
             */
            //User user = userService.findById(id);
            /**
             * 获取企业信息
             */
            Company_t company = t_user.getCompany();
            /**
             * 获取协会id,企业id,指定联系人id
             */
            Integer aid = t_user.getAssociationId();
            Integer cid = t_user.getCompany().getId();

            CompanyEmployee_t designatedContact = t_user.getDesignatedContact();
            CompanyEmployee_t companyEmployee_legal = informationService.findemplegById(cid);
            Map<String,Object> params = new HashMap<>();
            params.put("aid",aid);
            params.put("cid",cid);
            /**
             * 查找协会职务人选，得到员工集合，并判断员工性质，1为法人代表，0为普通员工
             * companyEmployee_legal为法人代表，intro为协会职务人选集合
             * 此处查找正式表的协会职务人选
             */
            List<Integer> intro =  informationService.findIntroducedById(id);

            List<CompanyEmployee_t> introduced =new ArrayList<>();


            for (int i:intro)
            {
                introduced.add(informationService.findempById(i));
            }

            /**
             * 查找企业描述信息description
             */
            Description_t description = informationService.findDecById(params);

            /**
             * 查找指定联系人freCon
             */
//                CompanyEmployee_t freCon = informationService.findempById(conid);

            /**
             * 查找协会association
             */
            Association_t association = informationService.findAssoById(aid);

            /**
             * 协会职务
             */
            AssociationUnit_t associationUnit = t_user.getAssociationUnit();
        /*
         *判断协会是否为农产品价格分会
         * 若是，查询单位代表字段
         */
            CompanyEmployee_t companyEmployee_rep = null;
            if (aid == 4)
            {
                Integer repid = informationService.findrepById(params);
                companyEmployee_rep = informationService.findempById(repid);
            }

            /**
             * 0:新公司注册
             * 1：旧公司，前台用户无法更改公司信息
             */
            Integer judgeNewCompany = userTemporaryService.ifcompanyBeHad(t_user.getId());

            map.put("judgeNewCompany",judgeNewCompany);
            map.put("associationUnit",associationUnit);
            map.put("introduced",introduced);
//            json.put("companyEmployee_legal",companyEmployee_legal);
            map.put("designatedContact",designatedContact);
            map.put("user",t_user);
            map.put("company",company);
            map.put("description",description);
            map.put("association",association);
            map.put("companyEmployee_legal",companyEmployee_legal);
            map.put("companyEmployee_rep",companyEmployee_rep);
            model.addAllObjects(map);
        } catch (SQLException e) {
            e.printStackTrace();
//            map.put("error","获取信息失败");
        }
        model.setViewName("/foreground/user/changeUserCheck");
        return model;
    }

    /**
     * 用户修改审核信息
     * @param checkUser
     * @throws SQLException
     */
    @RequestMapping("updateCheckInfo")
    @Transactional
    public String updateCheckInfo(CheckUser checkUser) throws SQLException {

        /*
         *判断协会是否为农产品价格分会
         * 修改单位代表字段
         */
        if (checkUser.getAssociation().getId() == 4)
        {
           companyEmployeeService.update(checkUser.getCompanyEmployee_rep());
        }

        /**
         * 0:新公司注册
         */
        Integer judgeNewCompany = userTemporaryService.ifcompanyBeHad(checkUser.getUser().getId());
        if (judgeNewCompany == 0){
            //修改新公司信息
            Company company = companyService.findById(checkUser.getCompany().getId());
            company.setName(checkUser.getCompany().getName());
            company.setRegisterNature(checkUser.getCompany().getRegisterNature());
            company.setAddress(checkUser.getCompany().getAddress());
            company.setCity(checkUser.getCompany().getCity());
            company.setCounty(checkUser.getCompany().getCounty());
            company.setEmail(checkUser.getCompany().getEmail());
            company.setZipCode(checkUser.getCompany().getZipCode());
            company.setInternetSite(checkUser.getCompany().getInternetSite());
            company.setFaxNum(checkUser.getCompany().getFaxNum());
            company.setNature(checkUser.getCompany().getNature());
            company.setIndustry(checkUser.getCompany().getIndustry());
            company.setAdminDepartment(checkUser.getCompany().getAdminDepartment());
            company.setEmployeeNum(checkUser.getCompany().getEmployeeNum());

            companyService.update(company);

            //修改法人代表
            companyEmployeeService.update(checkUser.getCompanyEmployee_legal());

            //修改描述信息
            userTemporaryService.updateDescription(checkUser.getDescription());
        }

        //修改指定联系人
        companyEmployeeService.update(checkUser.getDesignatedContact());

        //修改协会职务人选
        for (CompanyEmployee introduce:checkUser.getIntroduced()){
            companyEmployeeService.update(introduce);
        }

        //查询修改后协会单位id
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("associationId",checkUser.getAssociation().getId());
        map.put("name",checkUser.getAssociationUnit().getName());
        Integer unitid = associationUnitService.findId(map);
        if (unitid!=null){
            //修改用户协会单位
            checkUser.getUser().getAssociationUnit().setId(unitid);
        }

        //修改T_USER_TEMPORARY表
        userTemporaryDao.update(checkUser.getUser());
        //修改T_USER表
        Integer userid = userTemporaryService.userIdByUserTemporaryId(checkUser.getUser().getId());
        User user = new User();
        user.setId(userid);
        user.setRealName(checkUser.getUser().getRealName());
        user.setIDcard(checkUser.getUser().getIDcard());
        user.setEmail(checkUser.getUser().getEmail());
        user.setAssociationUnit(checkUser.getUser().getAssociationUnit());
        user.setPhoneNum(checkUser.getUser().getPhoneNum());
        user.setJobPosition(checkUser.getUser().getJobPosition());
        userService.update(user);
        workFlowService.userUpdate(checkUser.getUser().getId(),checkUser.getUser().getAccount(),"User","Insert");

        //跳转页面
        return "redirect:../work/to-personal";
    }

}

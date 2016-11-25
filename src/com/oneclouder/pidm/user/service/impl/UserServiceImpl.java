package com.oneclouder.pidm.user.service.impl;

import com.oneclouder.pidm.util.POIUtil;
import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.user.dao.IAssociationDao;
import com.oneclouder.pidm.user.dao.IAssociationUnitDao;
import com.oneclouder.pidm.user.dao.IUserDao;
import com.oneclouder.pidm.user.dao.IUserTemporaryDao;
import com.oneclouder.pidm.user.model.*;
import com.oneclouder.pidm.user.service.ICompanyEmployeeService;
import com.oneclouder.pidm.user.service.ICompanyService;
import com.oneclouder.pidm.user.service.IDescriptionService;
import com.oneclouder.pidm.user.service.IUserService;
import com.oneclouder.pidm.user.webBean.RegisterFormBean;
import com.oneclouder.pidm.user.webBean.UserSimpleInfo;
import com.oneclouder.pidm.util.EncryptUtils;
import com.oneclouder.pidm.util.MailUtil;
import com.oneclouder.pidm.util.PDFUtil;
import com.oneclouder.pidm.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 10:04 AM
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    private IUserDao userDao;

    @Resource(name = "userTemporaryDao")
    private IUserTemporaryDao userTemporaryDao;

    @Resource(name = "userDao")
    public void setDao(IUserDao userDao) {
        super.setDao(userDao);
        this.userDao = userDao;
    }

    @Resource(name = "companyService")
    private ICompanyService companyService;

    @Resource(name = "companyEmployeeService")
    private ICompanyEmployeeService companyEmployeeService;

    @Resource(name = "descriptionService")
    private IDescriptionService descriptionService;

    @Resource(name = "associationUnitDao")
    private IAssociationUnitDao associationUnitDao;

   /* *//**
     * 导出客户信息
     *
     * @param userSimpleInfo
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *//*
    @Override
    public void getUserSimpleInfo(UserSimpleInfo userSimpleInfo, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<UserSimpleInfo> contentList = userDao.getUserSimpleInfo(userSimpleInfo);
        String[] headerList = {"帐号", "单位名称", "通讯地址", "指定联系人", "指定联系人手机号码", "传真号码", "邮箱"};
        String[] key = {"getAccount", "getCompayName", "getAddress", "getDesContactName", "getPhoneNum", "getFaxNum", "getEmail"};
        POIUtil.exportToExcel(response, key, headerList, contentList);
    }*/

    /**
     * 分页显示客户记录
     *
     * @param userSimpleInfo
     * @return
     */
    @Override
    public List<UserSimpleInfo> getUserSimpleInfoLimit(UserSimpleInfo userSimpleInfo) {
        return userDao.getUserSimpleInfoLimit(userSimpleInfo);
    }

    /**
     * 查询客户详细信息
     *
     * @param id
     * @return
     */
    @Override
    public User getUserInfoEle(Integer id) {
        return userDao.getUserInfoEle(id);
    }

    /**
     * 更新客户信息
     *
     * @param InfoList
     */
    @Override
    public Integer changeUserInfo(String[] InfoList) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Map<String, String> passmap = EncryptUtils.encrypt(InfoList[2]);
        Integer[] parseList = new Integer[6];
        parseList[0] = Integer.parseInt(InfoList[0]);
        map.put("id", parseList[0]);
        map.put("account", InfoList[1]);
        map.put("password", passmap.get("password"));
        map.put("salt", passmap.get("salt"));
        map.put("realName", InfoList[3]);
        map.put("phoneNum", InfoList[4]);
        map.put("IDcard", InfoList[5]);
        map.put("email", InfoList[6]);
        map.put("compayname", InfoList[7]);
        map.put("nature", InfoList[8]);
        map.put("address", InfoList[9]);
        map.put("zip_code", InfoList[10]);
        map.put("lename", InfoList[11]);
        map.put("lejob", InfoList[12]);
        map.put("leofficephone", InfoList[13]);
        map.put("lecellphone", InfoList[14]);
        map.put("spname", InfoList[15]);
        map.put("spjob", InfoList[16]);
        map.put("spofficephone", InfoList[17]);
        map.put("spcellphone", InfoList[18]);
        map.put("spemail", InfoList[19]);
        map.put("spfaxnum", InfoList[20]);
        map.put("description1", InfoList[21]);
        User user = userDao.getUserInfoEle(parseList[0]);
        Integer accocitionId = user.getAssociationId();
        Map<String, Object> desmap = new LinkedHashMap<>();
        desmap.put("companyId", user.getCompany().getId());
        desmap.put("associationId", user.getAssociationId());
        desmap.put("description1", InfoList[21]);

        if (accocitionId == 1 || accocitionId == 2 || accocitionId == 5) {
            map.put("description2", InfoList[22]);
            desmap.put("description2", InfoList[22]);
        } else if (accocitionId == 3 || accocitionId == 4) {
            map.put("description2", "");
            desmap.put("description2", "");
        }
        Integer count = descriptionService.getDescriptionLength(user.getCompany().getId(), accocitionId);
        if (count > 0) {
            userDao.changeUserInfo(map);
        } else {
            userDao.changeUser(map);
            descriptionService.insertDes(desmap);
        }

        return accocitionId;
    }

    /**
     * 删除客户帐号
     *
     * @param uid
     */
    @Override
    public void deleteUserInfo(Integer uid) {
        userDao.deleteUserInfo(uid);
    }

    /**
     * 获得用户记录条数
     *
     * @return
     */
    @Override
    public Integer userInfoNum(UserSimpleInfo userSimpleInfo) {
        return userDao.userInfoNum(userSimpleInfo);
    }

    @Override
    public Integer isWriteSurvey(Integer id) {
        return userDao.isWriteSurvey(id);
    }

    @Override
    public void updataUserSurveyId(Integer surveyId, Integer id) {
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("surveyId",surveyId);
        map.put("id",id);
        userDao.updataUserSurveyId(map);
    }

    @Override
    public void updataSurveyId() {
        userDao.updataSurveyId();
    }

    @Override
    @Transactional
    public Integer registerUser(User user, Map<String, Object> map, HttpSession session) throws SQLException {
        //封装 协会id 协会单位id 指定联系人id 企业id
        user.setAssociationId((Integer) map.get("associationId"));
        AssociationUnit associationUnit = new AssociationUnit();
        associationUnit.setId((Integer) map.get("associationUnitId"));
        user.setAssociationUnit(associationUnit);
        if (map.get("designatedContactId") != null) {
            CompanyEmployee designatedContact = new CompanyEmployee();
            designatedContact.setId((Integer) map.get("designatedContactId"));
            user.setDesignatedContact(designatedContact);
        }

        //密码盐加密
        Map<String, String> passwordMap = EncryptUtils.encrypt(user.getPassword());
        user.setPassword((String) passwordMap.get("password"));
        user.setSalt((String) passwordMap.get("salt"));

        //生成邮箱激活码
        String verifyCode = UUIDUtil.generateUid();
        user.setVerifycode(verifyCode);

        //插入T_USER表
        insert(user);
        Integer userId = user.getId();
        // TODO 取名currentUser 这里是否需要存session
//        session.setAttribute("currentUser", user);
        List<Integer> introducedIds = (List<Integer>) map.get("introducedIds");
        //插入推荐表信息
        if (introducedIds != null) {
            Map<String, Object> params = new HashMap<>();
            if (introducedIds.size() > 0) {
                params.put("userId", userId);
                params.put("introducedIds", introducedIds);
                userDao.insertIntroduceds(params);
            }
        }

        MailUtil.sendMail(user.getEmail(), verifyCode);

        user.setId(null);
        User t_user = user;
        userTemporaryDao.insertById(t_user);
        return t_user.getId();
    }

    /**
     * 后台添加客户
     *
     * @param user
     * @param map
     * @param session
     * @return
     * @throws SQLException
     */
    public Integer addUser(User user, Map<String, Object> map, HttpSession session) throws SQLException {
        //封装 协会id 协会单位id 指定联系人id 企业id
        user.setAssociationId((Integer) map.get("associationId"));
        AssociationUnit associationUnit = new AssociationUnit();
        associationUnit.setId((Integer) map.get("associationUnitId"));
        user.setAssociationUnit(associationUnit);
        if (map.get("designatedContactId") != null) {
            CompanyEmployee designatedContact = new CompanyEmployee();
            designatedContact.setId((Integer) map.get("designatedContactId"));
            user.setDesignatedContact(designatedContact);

            //密码盐加密
            Map<String, String> passwordMap = EncryptUtils.encrypt(user.getPassword());
            user.setPassword((String) passwordMap.get("password"));
            user.setSalt((String) passwordMap.get("salt"));
            user.setStatus(2);
            user.setRegisterStatus(2);

            Integer userId = user.getId();

            //插入推荐表信息
            List<Integer> introducedId = (List<Integer>) map.get("introducedIds");
            if (introducedId != null) {
                List<Integer> introducedIds = (List<Integer>) map.get("introducedIds");
                Map<String, Object> params = new HashMap<>();
                params.put("userId", userId);
                params.put("introducedIds", introducedIds);
                userDao.insertIntroduceds(params);
            }

            //插入T_USER表
            userDao.insertUser(user);

        }
        return null;
    }

    @Override
    public Integer verifyCode(String code) throws SQLException {
        return userDao.verifyCode(code);
    }

    @Override
    public boolean isActive(Integer userId) throws SQLException {
        User user = userDao.findById(userId);
        Integer registerStatus = user.getRegisterStatus();
        if (registerStatus == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isExist(String account) throws SQLException {
        Integer count = userDao.findAccountNum(account);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void active(Integer userId) throws SQLException {
        userDao.active(userId);
    }

    @Override
    public User login(String account, String password) throws SQLException {
        //根据帐号查出盐
        String salt = userDao.findSaltByAccount(account);
        if (StringUtils.isEmpty(salt)) {
            return null;
        } else {
            password = EncryptUtils.encrypt(salt, password);
            Map<String, String> map = new HashMap<>();
            map.put("account", account);
            map.put("password", password);
            return userDao.findByAccountAndPassword(map);
        }
    }

    @Override
    public void addVerifycode(String account, String verifycode) throws SQLException {
        Map<String, String> map = new HashMap<>();
        map.put("account", account);
        map.put("verifycode", verifycode);
        userDao.insertVerifycode(map);
    }

    @Override
    public Integer resetPassword(User user) throws SQLException {
        Map<String, String> map = EncryptUtils.encrypt(user.getPassword());
        user.setPassword((String) map.get("password"));
        user.setSalt((String) map.get("salt"));
        return userDao.updatePasswordByCondition(user);
    }

    @Override
    @Transactional
    public Integer register(RegisterFormBean registerFormBean, HttpSession session) throws SQLException {
        Integer companyId = registerFormBean.getCompany().getId();
        Map<String, Object> map = new HashMap<>();  //封装user数据
        Integer associationId = registerFormBean.getAssociationId();
        map.put("associationId", associationId);
        Integer associationUnitId = registerFormBean.getAssociationUnitId();
        map.put("associationUnitId", associationUnitId);

        //判断是否是新公司注册(新公司)
        if (companyId == null) {
            // TODO 判断公司名字是否重复
//         插入company信息 获取返回的companyId
            companyId = companyService.createCompanny(registerFormBean.getCompany());
            /*
             * 插入法人代表信息
             */
            CompanyEmployee legalRep = registerFormBean.getLegalRep();
            legalRep.setNature(1);
            companyEmployeeService.addEmployee(legalRep, companyId);
        }
        /*
        * 插入指定联系人
        */
        CompanyEmployee designatedContact = registerFormBean.getDesignatedContact();
        if(designatedContact != null) {
            if (!StringUtils.isEmpty(designatedContact.getName())) {
                designatedContact.setNature(0);
                //返回指定联系人id
                Integer designatedContactId = companyEmployeeService.addEmployee(designatedContact, companyId);
                map.put("designatedContactId", designatedContactId);
            }
        }

        //该协会有单位代表人
        if (associationId == 4) {
            CompanyEmployee cpnyAcstRep = registerFormBean.getCpnyAcstRep();
            if(cpnyAcstRep != null) {
                if (!StringUtils.isEmpty(cpnyAcstRep.getName())) {
                    companyEmployeeService.addCpnyAcstRep(cpnyAcstRep, associationId, companyId);
                }
            }
        }

        /*
        * 插入顺带的入会人员
        */
        List<CompanyEmployee> introduceds = registerFormBean.getIntroduceds();
        List<Integer> introducedIds = companyEmployeeService.addEmployees(introduceds, companyId);
        if (introducedIds != null && introducedIds.size() > 0) {
            map.put("introducedIds", introducedIds);
        }

        /*try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        /*
        * 插入公司描述(科研成果等)
        */
        Description description = registerFormBean.getDescription();
        if (description != null && !StringUtils.isEmpty(description.getDescription1())) {
            description.setCompanyId(companyId);
            description.setAssociationId(associationId);
            //判断一下字符长度
            descriptionService.insert(description);
        }


        // TODO 判断用户帐号是否重复
        /*
         * 插入user
         */
        Company company = new Company();
        company.setId(companyId);
        User user = registerFormBean.getUser();
        user.setCompany(company);
        /**
         * 若registerFormBean.getBackClient()为1，即为后台添加客户
         */
        if (registerFormBean.getBackClient() != null) {
            return addUser(user, map, session);
        } else {
            return registerUser(user, map, session);
        }

    }


    /**
     * 导出PDF
     *
     * @param Info
     * @param response
     */
    public void exportPDF(List<Object> Info, HttpServletResponse response) {
        PDFUtil pdfUtil = new PDFUtil();
        /*pdfUtil.exportToPDF(response);*/
    }

}

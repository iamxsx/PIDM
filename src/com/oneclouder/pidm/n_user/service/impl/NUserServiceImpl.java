package com.oneclouder.pidm.n_user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.n_user.dao.*;
import com.oneclouder.pidm.n_user.dao.INTCompanyEmployeeDao;
import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.model.NDatum;
import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INCompanyEmployeeService;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import com.oneclouder.pidm.n_user.service.INDatumService;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.n_user.web_bean.EmailBean;
import com.oneclouder.pidm.n_user.web_bean.RegisterFormBean;
import com.oneclouder.pidm.util.*;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午9:00
 */
@Service("nUserService")
public class NUserServiceImpl extends BaseServiceImpl<NUser> implements INUserService {
    //--------------------------------成员变量----------------------------------
    private INUserDao nUserDao;

    @Resource(name = "nUserDao")
    public void setDao(INUserDao nUserDao) {
        super.setDao(nUserDao);
        this.nUserDao = nUserDao;
    }

    @Resource(name = "nTUserDao")
    private INTUserDao ntUserDao;

   /* @Resource(name = "nTUserDao")
    private INTUserDao nTUserDao;*/

    @Resource(name = "nTCompanyDao")
    private INTCompanyDao ntCompanyDao;

    @Resource(name = "nTCompanyEmployeeDao")
    private INTCompanyEmployeeDao ntCompanyEmployeeDao;

    @Resource(name = "nCompanyService")
    private INCompanyService companyService;

    @Resource(name = "nCompanyEmployeeService")
    private INCompanyEmployeeService companyEmployeeService;

    @Resource(name = "nTCompanyEmployeeDao")
    private INTCompanyEmployeeDao nTCompanyEmployeeDao;

    @Resource(name = "workFlowService")
    private IWorkFlowService workFlowService;

    @Resource(name = "nDatumService")
    private INDatumService nDatumService;

    @Resource(name = "nTDatumDao")
    private INTDatumDao nTDatumDao;


    //--------------------------------hxj----------------------------------
    //分页查询
    @Override
    public List<Map> getUserSimpleInfoLimit(Map map) {
        return nUserDao.getUserSimpleInfoLimit(map);
    }

    //导出excel
    @Override
    public void getUserSimpleInfo(Integer searchCondition, String keyWord, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> mapParam = new LinkedHashMap<>();
        mapParam.put("searchCondition", searchCondition);
        mapParam.put("keyWord", keyWord);
        List<Map> map = nUserDao.getUserSimpleInfoLimit(mapParam);
        String[] headerList = {"帐号", "单位名称", "通讯地址", "指定联系人", "指定联系人手机号码", "传真号码", "邮箱"};
        String[] key = {"account", "name", "address", "desContactName", "cell_phone_num", "fax_num", "email"};
        POIUtil.exportToExcel(response, key, headerList, map);
    }

    //删除客户记录
    @Override
    public void deleteUserInfo(Integer uid) {
        nUserDao.deleteUserInfo(uid);
    }

    //查询客户所有信息
    @Override
    public List<Map> selectClientAll(Integer uid) {
        return nUserDao.selectClientAll(uid);
    }

    //查询用户临时表全部信息
    @Override
    public List<Map> selectTUserAll(Integer uid) {
        return ntUserDao.selectTUserAll(uid);
    }

    //导出PDF
    @Override
    public void exportPDF(HttpServletResponse response, Integer uid) {
        PDFUtil.exportToPDF(response, nUserDao.selectClientAll(uid));
    }

    //修改客户信息
    @Override
    @Transactional
    public void updateUserInfo(NUser nUser) {
        nUserDao.updateUserInfo(nUser);
    }

    //添加客户信息到正式表
    @Override
    @Transactional
    public void addClient(RegisterFormBean formBean) throws SQLException {
        //添加company
        NCompany company = formBean.getCompany();
        //插入单位正式表
        Integer cpmpanyId = companyService.creatCompany(company);

        //添加legalRep
        NCompanyEmployee legalRep = formBean.getLegalRep();
        //插入员工正式表
        Integer legalRepId = companyEmployeeService.creatCompanyEmployee(cpmpanyId, legalRep, 1);

        //添加introduceds
        List<NCompanyEmployee> introduceds = formBean.getIntroduceds();
        //正式表
        if (introduceds != null && introduceds.size() > 0) {
            for (NCompanyEmployee introduced : introduceds) {
                if (introduced.getName() != "") {
                    companyEmployeeService.creatCompanyEmployee(cpmpanyId, introduced, 1);
                }
            }
        }
        //添加designatedContact
        List<NCompanyEmployee> designatedContacts = formBean.getDesignatedContacts();
        if (designatedContacts != null && designatedContacts.size() > 0) {
            for (NCompanyEmployee designatedContact : designatedContacts) {
                if (designatedContact.getName() != "") {
                    companyEmployeeService.creatCompanyEmployee(cpmpanyId, designatedContact, 1);
                }
            }
        }


        //添加user
        NUser user = formBean.getUser();
        user.setCompany(company);
        user.setStatus(3);
        //密码盐加密
        Map<String, String> passwordMap = EncryptUtils.encrypt(user.getPassword());
        user.setPassword((String) passwordMap.get("password"));
        user.setSalt((String) passwordMap.get("salt"));
        //插入正式表
        nUserDao.insertUser(user);
    }

    //修改客户,公司,公司员工
    @Override
    @Transactional
    public void changeClientInfo(RegisterFormBean changeInfo,Integer desNum,Integer introNum) throws SQLException {
        this.updateUserInfo(changeInfo.getUser());
        companyService.updateCompanyInfo(changeInfo.getCompany());
        companyEmployeeService.updateEmployeeInfo(changeInfo.getLegalRep());
        this.changeEmployee(changeInfo.getDesignatedContacts(),desNum);
        this.changeEmployee(changeInfo.getIntroduceds(),introNum);

    }

    /**
     *  员工修改操作
     * @param nCompanyEmployee 某类员工List数组
     * @param empNum    员工修改前数量
     * @throws SQLException
     */
    @Transactional
    public void changeEmployee(List<NCompanyEmployee> nCompanyEmployee,Integer empNum) throws SQLException {
        int i = 0;
        for (NCompanyEmployee des : nCompanyEmployee) {
            if(i<empNum){
                if (des.getId() != null) {
                    companyEmployeeService.updateEmployeeInfo(des);
                    i++;
                }
            }else if(i<4){     //一类员工最多有4个
                //如果某类员工数量超出修改之前的数量,添加这类员工
                companyEmployeeService.insert(des);
                i++;
            }

        }
    }

    //修改客户,公司,公司员工临时表信息
    @Override
    @Transactional
    public void changeUserCheck(RegisterFormBean registerFormBean,HttpServletRequest request, String location, Integer userId) throws Exception {
        ntUserDao.updateTUser(registerFormBean.getUser());
        ntCompanyDao.updateTCompany(registerFormBean.getCompany());
        ntCompanyEmployeeDao.updateTEmployee(registerFormBean.getLegalRep());
        for (NCompanyEmployee des : registerFormBean.getDesignatedContacts()) {
            if (des.getName() != null) {
                ntCompanyEmployeeDao.updateTEmployee(des);
            }
        }

        for (NCompanyEmployee intro : registerFormBean.getIntroduceds()) {
            if (intro.getId() != null) {
                ntCompanyEmployeeDao.updateTEmployee(intro);
            }
        }

        String filePath = uploadFile(request,location,userId);
        NDatum datum = new NDatum();
        datum.setUserId(userId);
        datum.setFileUrl(filePath);
        nTDatumDao.update(datum);
    }

    //更新用户填写过的调查表id
    @Override
    public void updataUserSurveyId(Integer surveyId, Integer id) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("surveyId", surveyId);
        map.put("id", id);
        nUserDao.updataUserSurveyId(map);
    }

    //更新所有会员填写过的调查表id
    @Override
    public void updataSurveyId() {
        nUserDao.updataSurveyId();
    }

    //查询用户survey_id
    @Override
    public Integer isWriteSurvey(Integer id) {
        return nUserDao.isWriteSurvey(id);
    }

    //--------------------------------zcb----------------------------------
    @Override
    /**插用户信息到 临时表**/
    public Integer insertToNTempUser(NUser nUser) {
        return nUserDao.insertToNTempUser(nUser);
    }

    /**
     * 插用户所有信息 到临时表
     *
     * @param formBean
     * @return
     */
    @Override
    @Transactional
    public Integer saveNUserAllInfoToTemp(RegisterFormBean formBean,HttpServletRequest request) throws Exception {
        NCompany nCompany = formBean.getCompany();
        companyService.insertToNTempCompany(nCompany);
//        Integer nTempCompanyId = nCompany.getId();
//        System.out.println("存进公司临时表ID："+nTempCompanyId);
        /**把用户信息 存入临时表**/
        //把临时表的公司ID set到nUser里面
        NUser user = formBean.getUser();
        user.setCompany(nCompany);
//        System.out.println("用户信息"+user);
        insertToNTempUser(user);
        Integer nTempUId = user.getId();

        /**存公司员工入 临时表**/
        NCompanyEmployee legalRep = formBean.getLegalRep();
        List<NCompanyEmployee> introduceds = formBean.getIntroduceds();
        List<NCompanyEmployee> designatedContacts = formBean.getDesignatedContacts();
        //把临时表的公司ID set到 员工 里面
        legalRep.setCompany(nCompany);
        companyEmployeeService.insertToNTempCompanyEmp(legalRep);
        for (int i = 0; i < introduceds.size(); i++) {
            NCompanyEmployee introduced = introduceds.get(i);
            if (introduced.getName() != null && !introduced.getName().equals("")) {
                introduced.setCompany(nCompany);
                companyEmployeeService.insertToNTempCompanyEmp(introduced);
            }
        }
        for (int i = 0; i < designatedContacts.size(); i++) {
            NCompanyEmployee designatedContact = designatedContacts.get(i);
            if (designatedContact.getName() != null && !designatedContact.getName().equals("")) {
                designatedContact.setCompany(nCompany);
                companyEmployeeService.insertToNTempCompanyEmp(designatedContact);
            }
        }
        // TODO: 16-10-20 上传文件
        NDatum datum = new NDatum();
        String fileUrl = uploadFile(request, PropertiesUtil.getProperty("APPLICATION_PATH"), nTempUId);
        System.out.println("文件上传的路径 .. ："+fileUrl);
        datum.setUserId(nTempUId);
        datum.setFileUrl(fileUrl);
        nDatumService.createDatum(datum, 2);


        return nTempUId;
    }

    /**
     * 工作流审核通过 信息从临时表 copy 到正式表
     *
     * @param nUserId 临时表用户ID
     */
    @Override
    @Transactional
    public void saveUserAllInfoFromTemp(Integer nUserId) throws SQLException {
        //通过临时表用户ID 查找正式表用户 ****
        NUser tempUser = ntUserDao.findById(nUserId);
        NUser user = nUserDao.findByAccount(tempUser.getAccount());
        // ****  正式表公司ID  ****
        Integer companyId = user.getCompany().getId();
        //****  临时表公司ID  ****
        Integer tempCompanyId = tempUser.getCompany().getId();

        //更新 正式表 用户信息 4个param
        tempUser.setId(user.getId());
        nUserDao.updateUserInfo(tempUser);

        //把临时表的公司信息 ID 更改为正式表公司 ID
        NCompany company = tempUser.getCompany();
        company.setId(companyId);
        //更新 正式表 公司信息
        companyService.updateCompanyInfo(company);

        //通过临时  公司ID 找临时表里面的员工
        companyEmployeeService.deleteByCompanyId(companyId);
        List<NCompanyEmployee> nCompanyEmployees = nTCompanyEmployeeDao.getCompanyEmployeeByCId(tempCompanyId);
        for (NCompanyEmployee employee : nCompanyEmployees) {
            employee.getCompany().setId(companyId);
            companyEmployeeService.insert(employee);
        }

        //插入上传文件路径到 正式表
        NDatum datum = nDatumService.findByUserId(nUserId);
//        String fileUrl = datum.getFileUrl();
        //user.getId() 为正式表用户ID
        datum.setUserId(user.getId());
        nDatumService.updateFileUrlByUId(datum);
    }




    //-------------------------------xucb----------------------------------

    /**
     * 根据临时表用户Id查找出用户信息
     *
     * @param uid
     * @return
     */
    @Override
    public NUser findTempUserById(Integer uid) throws SQLException {
        return ntUserDao.findById(uid);
    }

    /**
     * 根据临时表用户Id查询临时资源表中文件路径
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public String findTempUrl(Integer uid) throws SQLException {
        return ntUserDao.findTempUrl(uid);
    }

    //--------------------------------zjf----------------------------------
    @Override
    public boolean isExist(String account) {
        Integer count = nUserDao.findAccountNum(account);
        //数量大于0 账号已存在
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Integer register(RegisterFormBean formBean, HttpServletRequest request) throws Exception {
        //添加company
        NCompany company = formBean.getCompany();
        //插入单位正式表
        Integer cpmpanyId = companyService.creatCompany(company);
        //插入单位临时表
        companyService.insertToNTempCompany(company);
        Integer tCpmpanyId = company.getId();

        //添加legalRep
        NCompanyEmployee legalRep = formBean.getLegalRep();
        //插入员工正式表
        Integer legalRepId = companyEmployeeService.creatCompanyEmployee(cpmpanyId, legalRep, 1);
        //插入员工临时表
        Integer tLegalRepId = companyEmployeeService.creatCompanyEmployee(tCpmpanyId, legalRep, 2);

        //添加introduceds
        List<NCompanyEmployee> introduceds = formBean.getIntroduceds();
        //正式表
        List<Integer> introducedIds = companyEmployeeService.creatCompanyEmployees(cpmpanyId, introduceds, 1);
        //临时表
        List<Integer> tIntroducedIds = companyEmployeeService.creatCompanyEmployees(tCpmpanyId, introduceds, 2);

        //添加designatedContact
        List<NCompanyEmployee> designatedContacts = formBean.getDesignatedContacts();
        List<Integer> designatedContactIDs = companyEmployeeService.creatCompanyEmployees(cpmpanyId, designatedContacts, 1);
        List<Integer> tDesignatedContactIDs = companyEmployeeService.creatCompanyEmployees(tCpmpanyId, designatedContacts, 2);

        //添加user
        NUser user = formBean.getUser();
//        user.setStatus(3);
        Map<String, Integer> userIds = this.creatUser(cpmpanyId, tCpmpanyId, user);
        //正式表userId
        Integer userId = userIds.get("userId");
        String fileUrl = uploadFile(request, PropertiesUtil.getProperty("APPLICATION_PATH"), userId);
        NDatum datum = new NDatum();
        datum.setUserId(userId);
        datum.setFileUrl(fileUrl);
        nDatumService.createDatum(datum, 1);
        //临时表tUserId
        Integer tUserId = userIds.get("tUserId");
        fileUrl = uploadFile(request, PropertiesUtil.getProperty("APPLICATION_PATH"), tUserId);
        datum.setUserId(tUserId);
        datum.setFileUrl(fileUrl);
        nDatumService.createDatum(datum, 2);
        return tUserId;
    }

    @Override
    public Map<String, Integer> creatUser(Integer cpmpanyId, Integer tCpmpanyId, NUser user) throws Exception {
        Map<String, Integer> userIds = new HashMap<String, Integer>();
        //封装companyId
        NCompany company = new NCompany();
        company.setId(cpmpanyId);
        user.setCompany(company);

        //密码盐加密
        Map<String, String> passwordMap = EncryptUtils.encrypt(user.getPassword());
        user.setPassword((String) passwordMap.get("password"));
        user.setSalt((String) passwordMap.get("salt"));

        //生成邮箱激活码
        String verifyCode = UUIDUtil.generateUid();
        user.setVerifycode(verifyCode);

        //另开线程 发送验证码
        // TODO 待测
        EmailBean emailBean = new EmailBean(user.getEmail(), verifyCode, 1);
        Thread thread = new Thread(emailBean);
        thread.start();

//        MailUtil.sendMail(user.getEmail(), verifyCode);

        //插入正式表
        nUserDao.insert(user);
        userIds.put("userId", user.getId());
        //插入临时表
        company.setId(tCpmpanyId);
        user.setCompany(company);
        nUserDao.insertToNTempUser(user);
        userIds.put("tUserId", user.getId());
        return userIds;
    }

    @Override
    public NUser verifyCode(String code) {
        return nUserDao.findByCode(code);
    }

    @Override
    public boolean isActive(Integer userId) throws SQLException {
        NUser user = nUserDao.findById(userId);
        if (user.getStatus() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Integer active(Integer userId) {
        return nUserDao.active(userId);
    }

    @Override
    public NUser login(String account, String password) {
        NUser user = null;
        //根据帐号查出盐
        String salt = nUserDao.findSaltByAccount(account);
        if (StringUtils.isEmpty(salt)) {
        } else {
            password = EncryptUtils.encrypt(salt, password);
            Map<String, String> map = new HashMap<String, String>();
            map.put("account", account);
            map.put("password", password);
            user = nUserDao.findByAccountAndPassword(map);
        }
        return user;
    }

    @Override
    @Transactional
    public Integer addVerifycode(String account, String verifycode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("verifycode", verifycode);
        return nUserDao.updateVerifycode(params);
    }

    @Override
    public boolean isExist(String account, String email) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("email", email);
        Integer count = nUserDao.findByAccountAndEmail(params);
        if (count == 1) {
            return true;
        }
        return false;
    }

    @Override
    public NUser findByAccount(String account) {
        return nUserDao.findByAccount(account);
    }

    @Override
    public boolean isVerifyPass(String account, String email, String verifycode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("email", email);
        params.put("verifycode", verifycode);
        Integer count = nUserDao.updateCodeByAccoAndEmailAndVcode(params);
        if (count == 1) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Integer resetPassword(String account, String password) {
        Map<String, String> map = EncryptUtils.encrypt(password);
        password = map.get("password");
        String salt = map.get("salt");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("password", password);
        params.put("salt", salt);
        return nUserDao.updatePassword(params);
    }

    @Override
    @Transactional
    public Integer activeAccount(NUser user) {
        Integer row = this.active(user.getId());
        //根据验证码找出临时表的user
        user = ntUserDao.findByVerifycode(user.getVerifycode());
        workFlowService.start(user.getId(), user.getAccount(), "User", "Insert");
        return row;
    }

    /**
     * Created By IntelliJ IDEA
     * 上传附件
     * @param request
     * @param location 上传路径（统一写”/upload/doc/“）
     * @param userId 用户id
     * @return
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-20 下午4:02
     */
    public static String uploadFile(HttpServletRequest request, String location, Integer userId) throws Exception {
        //key为上传文件的name属性，value为上传的路径
        String fileUrl = new String();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                System.out.println(file.getName());
                if (file != null) {
                    String filename = file.getOriginalFilename();
                    if (filename.trim() != "") {
                        filename = generateFileName(filename, userId);
                        FtpUtil.upload(file.getInputStream(), filename, location);
                        fileUrl = location + filename;
                    }
                }
            }
        }
        return fileUrl;
    }

    /**
     * 生成上传文件的文件名
     *
     * @param fileName
     * @return
     */
    public static String generateFileName(String fileName, Integer userId) {
        return userId + DateUtil.getCurrentDateDesc() + fileName.substring(fileName.lastIndexOf("."));
    }

}

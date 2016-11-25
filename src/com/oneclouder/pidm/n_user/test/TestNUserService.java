package com.oneclouder.pidm.n_user.test;

import com.oneclouder.pidm.n_user.dao.INDatumDao;
import com.oneclouder.pidm.n_user.dao.INTCompanyEmployeeDao;
import com.oneclouder.pidm.n_user.dao.INTUserDao;
import com.oneclouder.pidm.n_user.dao.INUserDao;
import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.model.NDatum;
import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INCompanyEmployeeService;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.util.EncryptUtils;
import com.oneclouder.pidm.util.PDFUtil;
import com.oneclouder.pidm.util.UUIDUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.SQLException;
import java.util.List;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午10:17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestNUserService {
    //--------------------------------成员变量----------------------------------
    @Resource(name = "nUserService")
    INUserService nUserService;

    @Resource(name = "nUserDao")
    INUserDao nUserDao;

    @Resource(name = "nTUserDao")
    INTUserDao nTUserDao;

    @Resource(name = "nCompanyEmployeeService")
    INCompanyEmployeeService nCompanyEmployeeService;

    @Resource(name = "nTCompanyEmployeeDao")
    private INTCompanyEmployeeDao nTCompanyEmployeeDao;

    @Resource(name = "nCompanyService")
    INCompanyService nCompanyService;

    @Resource(name = "nDatumDao")
    INDatumDao nDatumDao;


    //--------------------------------hxj----------------------------------
    @Test
    public void testgetUserSimpleInfoLimit() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("searchCondition", null);
        map.put("keyWord", null);
        map.put("offset", 0);
        map.put("limit", 10);
        System.out.println(nUserService.getUserSimpleInfoLimit(map));
    }

    @Test
    public void textselectAll() {
        /*Map<String,Object> map = new LinkedHashMap<>();
        map.put("uid",1);*/
        System.out.println(nUserService.selectClientAll(1));
    }


    //--------------------------------zcb----------------------------------

    /**
     * 查找
     **/
    @Test
    public void getTest() throws SQLException {
        NUser nUser = nUserService.findById(1);
//        System.out.println(nUser);
        List<NCompanyEmployee> nCompanyEmployee = nCompanyEmployeeService.getCompanyEmployeeByCId(nUser.getCompany().getId());
        System.out.println(nCompanyEmployee.size());
    }

    /**
     * 插用户信息到 临时表
     **/
    @Test
    public void testInsertToNTempUser() throws SQLException {
        NUser nUser = nUserService.findById(1);
        nCompanyService.insertToNTempCompany(nUser.getCompany());

        Integer nTempCompanyId = nUser.getCompany().getId();
        nUser.getCompany().setId(nTempCompanyId);
        nUserService.insertToNTempUser(nUser);
//        System.out.println("返回临时表用户ID："+nTemUId);
    }

    /**
     * 负责临时表信息 到正式表
     */
    @Test
    public void saveUserAllInfoFromTemp() throws SQLException{

        nUserService.saveUserAllInfoFromTemp(28);

    }
    /**
     * nDatum从临时表复制到正式表
     */
    @Test
    public void test(){
        NDatum datum = new NDatum();
        datum.setUserId(1);
        datum.setFileUrl("/upload/doc/1320161020192911.png");
        nDatumDao.updateFileUrlByUId(datum);
    }


    //--------------------------------zjf----------------------------------

    /**
     * 查找
     */
    @Test
    public void testFindByid() throws Exception {
        NUser nUser = nUserService.findById(1);
        NCompany nCompany = nUser.getCompany();
        List<NCompanyEmployee> nCompanyEmployee = nCompanyEmployeeService.getCompanyEmployeeByCId(nUser.getCompany().getId());
        System.out.println("公司ID：" + nUser.getCompany().getId());
//        System.out.println(nCompany);
//        System.out.println(nUser);
        System.out.println(nCompanyEmployee);
    }

    @Test
    public void testIsExist() throws Exception {
        boolean exist = nUserService.isExist("jack");
        System.out.println(exist);
    }

    @Test
    public void testInsert() throws Exception {
        NUser user = new NUser();
        user.setAccount("Kobe");
        user.setPassword("12345678");
        user.setPhoneNum("18813973767");
        user.setIDcard("440883199425459847");
        user.setRealName("Kobe Bryant");
        user.setEmail("553783307@qq.com");
        //封装companyId
        Integer cpmpanyId = 1;
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

//        nUserDao.insert(user);
        //       nUserService.creatUser(cpmpanyId, user);
        System.out.println(user.getId());
    }

    @Test
    public void testVerifycode() throws Exception {
        String code = "d8f3c6cd66fa4e10b444464686149c51";
        NUser user = nUserService.verifyCode(code);
        System.out.println(user);
    }

    @Test
    public void testIsActive() throws Exception {
        System.out.println(nUserService.isActive(12));
    }

    @Test
    public void testActive() throws Exception {
        nUserService.active(11);
    }

    @Test
    public void testLogin() throws Exception {
        NUser user = nUserService.login("angryfeng", "12345678");
        System.out.println(user);
    }

    @Test
    public void testFindByAccount() throws Exception {
        NUser user = nUserService.findByAccount("angryfeng");
        System.out.println(user);
    }

    @Test
    public void testIsExist1() throws Exception {
        boolean exist = nUserService.isExist("angryfeng", "angryfeng@163.com");
        System.out.println(exist);
    }

    @Test
    public void testAddVerifycode() throws Exception {
        //前四位
        String verifycode = UUIDUtil.generateUid().substring(0, 6);
        Integer row = nUserService.addVerifycode("angryfeng", verifycode);
        System.out.println(row);
    }

    @Test
    public void testIsVerifyPass() throws Exception {
        String account = "angryfeng";
        String email = "angryfeng@163.com";
        String verifycode = "2626";
        boolean pass = nUserService.isVerifyPass(account, email, verifycode);
        System.out.println(pass);
    }

    @Test
    public void testResetPassword() throws Exception {
        String account = "angryfeng";
        String password = "12345678";
        Integer row = nUserService.resetPassword(account, password);
        System.out.println(row);
    }

}

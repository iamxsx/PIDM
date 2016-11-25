package com.oneclouder.pidm.user.test;

import com.oneclouder.pidm.user.dao.IUserDao;
import com.oneclouder.pidm.user.dao.IUserTemporaryDao;
import com.oneclouder.pidm.user.model.AssociationUnit;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.model.UserTemporary;
import com.oneclouder.pidm.user.service.IUserTemporaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zheng.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTemporaryTest {

    @Resource(name = "userDao")
    IUserDao userDao;
    @Resource(name = "userTemporaryDao")
    IUserTemporaryDao userTemporaryDao;

    @Resource(name = "userTemporaryService")
    IUserTemporaryService userTemporaryService;
    //返回 添加临时表的 ID
    @Test
    public void backIdTest(){
        User user = userDao.getUserInfoEle(26);
        Integer userTemporaryId = userTemporaryDao.insertById(user);
        System.out.println(user.getId());
    }

    //通过临时表更新用户表
    @Test
    public void updateUser(){
        UserTemporary userTemporary = userTemporaryDao.findUserTemporaryById(1);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("realName",userTemporary.getRealName());
        params.put("phoneNum",userTemporary.getPhoneNum());
        params.put("IDcard",userTemporary.getIDcard());
        params.put("email",userTemporary.getEmail());
        params.put("designatedContact",userTemporary.getDesignatedContact().getId());

        //通过临时表的帐号 获得用户ID
        int uId = userTemporaryDao.findUIdByTemporaryAccount(userTemporary.getAccount());
        params.put("uId",uId);
        //user修改信息更改 ，在userTemporaryDao 里面执行  避免冲突
        userTemporaryDao.updateTemporaryToUser(params);
    }
    //通过临时表companyId判断是否是已有企业
    @Test
    public void ifcompanyBeHad(){
        UserTemporary userTemporary = userTemporaryDao.findUserTemporaryById(36);
        Integer companyId = userTemporary.getCompany().getId();
        Integer count =  userTemporaryDao.findCountOfCompany(companyId);
        if(count<2){
            System.out.println(00000);
        }else {
            System.out.println(11111);
        }
    }

    //更新临时表
    @Test
    public void testUpdate() throws SQLException {
        UserTemporary u = new UserTemporary();
        AssociationUnit a = new AssociationUnit();
        a.setId(11);

        u.setAssociationUnit(a);
        u.setId(113);
        u.setRealName("屈臣氏");
        u.setPhoneNum("188139****");
        u.setEmail("188139****@qq.com");
        u.setPhoneNum("188139****");
        u.setIDcard("440582******");
        u.setJobPosition("董事长");

        userTemporaryDao.update(u);
    }


}

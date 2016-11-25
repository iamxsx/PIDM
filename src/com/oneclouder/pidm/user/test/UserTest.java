package com.oneclouder.pidm.user.test;

import com.oneclouder.pidm.user.dao.IUserDao;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.service.IUserService;
import com.oneclouder.pidm.user.webBean.UserSimpleInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by clouder on 9/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserTest {
    @Resource(name = "userService")
    IUserService userService;

    @Resource(name = "userDao")
    IUserDao userDao;

    /*@Test
    public void testchangeUser(){
        userDao.changeUser();
    }*/

    @Test
    public void testgetUserSimpleInfoLimit() {
        UserSimpleInfo user = new UserSimpleInfo();
        /*user.setSearchCondition(0);*/
        /*user.setKeyWord("");*/
        user.setLimitFrom(0);
        user.setLimitNumber(10);

        System.out.println(userService.getUserSimpleInfoLimit(user).get(0).getAccount());

    }

    @Test
    public void testUserInfoEle(){
        System.out.println(userDao.getUserInfoEle(26));
    }
    @Test
    public void testgetUserSimpleInfo() {
        UserSimpleInfo user = new UserSimpleInfo();
        user.setSearchCondition(0);
        user.setKeyWord("");
        System.out.println(userDao.getUserSimpleInfo(user));
        /*try {

            userService.getUserSimpleInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void testchangeUserInfo() {
        String[] InfoList = {"5", "saf", "123", "jack1", "123124", "123", "s@s",
                "宝丽华", "s", "sdf", "asdf",
                "as", "asdf", "123", "345",
                "fafsad", "sdf", "234", "123", "sa@d", "sdf",
                "asdddddddddd","sadf"};
        userService.changeUserInfo(InfoList);
    }

    @Test
    public void testUserInfoNum() {
        UserSimpleInfo userSimpleInfo  = new UserSimpleInfo();

        System.out.println(userService.userInfoNum(userSimpleInfo));
    }
    @Test
    public void testDeleteUserInfo() {
        UserSimpleInfo userSimpleInfo = new UserSimpleInfo();
        userSimpleInfo.setCompayName("宝丽华");
        userSimpleInfo.setAccount("assa");
        userService.deleteUserInfo(1);
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setAccount(UUID.randomUUID().toString());
        user.setPassword("123456");
        userService.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testOs(){
        System.out.println(System.getProperties().getProperty("os.name"));
    }
    @Test
    public void testInsertIntroduceds() throws  Exception {
    }
}

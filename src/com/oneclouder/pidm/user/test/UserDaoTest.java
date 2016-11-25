package com.oneclouder.pidm.user.test;

import com.oneclouder.pidm.user.dao.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/14/16
 * Time: 5:21 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserDaoTest {
    @Resource(name = "userDao")
    private IUserDao userDao;

    @Test
    public void testInsertIntroduceds() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        List<Integer> introducedIds = new ArrayList<>();
        introducedIds.add(3);
        introducedIds.add(5);
        map.put("introducedIds", introducedIds);
        userDao.insertIntroduceds(map);
    }
}

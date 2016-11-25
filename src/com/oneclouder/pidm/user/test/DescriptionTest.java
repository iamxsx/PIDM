package com.oneclouder.pidm.user.test;

import com.oneclouder.pidm.user.service.IDescriptionService;
import com.oneclouder.pidm.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by clouder on 9/18/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DescriptionTest {

    @Resource(name = "descriptionService")
    IDescriptionService descriptionService;

    @Test
    public void  test(){
        System.out.println(descriptionService.getDescriptionByAssAndComId(3,1));

    }

    @Test
    public void testlength(){
        System.out.println(descriptionService.getDescriptionLength(3,1));
    }

    @Test
    public void testinsert(){
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("companyId",3);
        map.put("associationId",4);
        map.put("description1", "asdf");
        map.put("description2", "sdf");
        descriptionService.insertDes(map);
    }
}

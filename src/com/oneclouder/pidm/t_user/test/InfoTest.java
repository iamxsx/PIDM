package com.oneclouder.pidm.t_user.test;

import com.oneclouder.pidm.t_user.service.IInformationService;
import com.oneclouder.pidm.t_user.model.*;
import com.oneclouder.pidm.t_user.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 9/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class InfoTest {

    @Resource(name = "informationService")
    private IInformationService informationService;

    @Test
    public void testtuser() throws SQLException {
        User_t t_user = informationService.findtUserpById(1);
        System.out.print(t_user.toString());

    }
    @Test
    public  void testuserid() throws SQLException {
        User_t t_user = informationService.findtUserpById(1);
        int id = informationService.findUserIdBytAccount(t_user.getAccount());
        Company_t company = t_user.getCompany();
//        String unitname = informationService.findUnitNameById(t_user.)
        AssociationUnit_t associationUnit_t = t_user.getAssociationUnit();
        Integer conid = t_user.getFrequentContact();
        Map<String,Object> params = new HashMap<>();
        params.put("aid",t_user.getAssociationId());
        params.put("cid",company.getId());
        CompanyEmployee_t designatedContact = t_user.getDesignatedContact();
        Association_t association = informationService.findAssoById(t_user.getAssociationId());
        Description_t description = informationService.findDecById(params);
        List<Integer> intro =  informationService.findIntroducedById(id);
//        iSystem.out.println("协会职务"+informationService.findUnitIdById(1));
        System.out.println("法人代表"+informationService.findemplegById(company.getId()));
        System.out.println(t_user.getAssociationUnit().getName());
        for (int i:intro)
        {
                System.out.println("推荐人："+informationService.findempById(i).getName());
        }

        CompanyEmployee_t companyEmployee_rep = null;
        if (t_user.getAssociationId() == 4)
        {
            Integer repid = informationService.findrepById(params);
            companyEmployee_rep = informationService.findempById(repid);
            System.out.println("农产品协会单位代表"+":"+repid+companyEmployee_rep.getId()+":"+companyEmployee_rep.getName());
        }
        System.out.println("指定联系人："+designatedContact.getName());
        System.out.println("描述表id："+description.getId());
        System.out.println("描述１"+description.getDescription1());
        System.out.println("描述２"+description.getDescription2());
        System.out.println("是否常用联系人："+t_user.getFrequentContact());
        System.out.println("协会名称："+association.getName());
        System.out.println("协会id："+t_user.getAssociationId());
        System.out.println("企业id"+company.getId());
        System.out.println("企业名称："+company.getName());
        System.out.println("前台用户id："+id);
        System.out.println("前台用户信息："+t_user.toString());
    }


}

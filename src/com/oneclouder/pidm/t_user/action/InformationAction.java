package com.oneclouder.pidm.t_user.action;

import com.oneclouder.pidm.t_user.service.IInformationService;
import com.oneclouder.pidm.t_user.model.*;
import com.oneclouder.pidm.t_user.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chan on 18/09/16.
 */
@Controller
@RequestMapping("info")
public class InformationAction {


    @Resource(name = "informationService")
    private IInformationService informationService;


    @ResponseBody
    @RequestMapping(value = "/showInfo" ,method = RequestMethod.GET)
    public Map<String,Object> info(@RequestParam(value = "t_id",required = true)Integer t_id)
    {
        Map<String,Object> json = new HashMap();
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
            CompanyEmployee_t companyEmployee_legal = informationService.findemplegById(cid);
            CompanyEmployee_t designatedContact = t_user.getDesignatedContact();
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

            json.put("associationUnit",associationUnit);
            json.put("introduced",introduced);
//            json.put("companyEmployee_legal",companyEmployee_legal);
            json.put("designatedContact",designatedContact);
            json.put("user",t_user);
            json.put("company",company);
            json.put("description",description);
            json.put("association",association);
            json.put("companyEmployee_legal",companyEmployee_legal);
            json.put("companyEmployee_rep",companyEmployee_rep);
        } catch (SQLException e) {
            e.printStackTrace();
            json.put("error","获取信息失败");
        }

        return json;
    }
}

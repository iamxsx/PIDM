package com.oneclouder.pidm.user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.user.dao.ICompanyEmployeeDao;
import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import com.oneclouder.pidm.user.service.ICompanyEmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 9:58 AM
 */
@Service("companyEmployeeService")
public class CompanyEmployeeServiceImpl extends BaseServiceImpl<CompanyEmployee> implements ICompanyEmployeeService {
    private ICompanyEmployeeDao companyEmployeeDao;

    @Resource(name = "companyEmployeeDao")
    public void setDao(ICompanyEmployeeDao dao) {
        super.setDao(dao);
        this.companyEmployeeDao = dao;
    }

    @Override
    @Transactional
    public Integer addEmployee(CompanyEmployee companyEmployee, Integer companyId) throws SQLException {
        Company company = new Company();
        company.setId(companyId);
        companyEmployee.setCompany(company);

        companyEmployeeDao.insert(companyEmployee);
        return companyEmployee.getId();
    }

    @Override
    @Transactional
    public List<Integer> addEmployees(List<CompanyEmployee> introduceds, Integer companyId) throws SQLException {
        List<Integer> introducedIds = new ArrayList<Integer>();
        if(introduceds != null && introduceds.size() > 0) {
            for (CompanyEmployee introduced : introduceds) {
                if(!StringUtils.isEmpty(introduced.getName())) {
                    introduced.setNature(0);
                    //插入数据库 返回id 装进introduceds中
                    introducedIds.add(addEmployee(introduced,companyId));
                }
            }
            return introducedIds;
        }
        return null;
    }

    @Override
    @Transactional
    public Integer addCpnyAcstRep(CompanyEmployee cpnyAcstRep, Integer associationId, Integer companyId) throws SQLException {
        cpnyAcstRep.setNature(0);
        //插入员工表
        Integer cpnyAcstRepId = addEmployee(cpnyAcstRep, companyId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cpnyAcstRepId", cpnyAcstRepId);
        map.put("associationId", associationId);
        map.put("companyId", companyId);
        //插入 T_COMPANY_ASSOCIATION_REP
        companyEmployeeDao.insertCpnyAcstRep(map);
        return cpnyAcstRepId;
    }

    @Override
    public CompanyEmployee getLegalemployeeBycompayId(Integer cid) {
        return companyEmployeeDao.getLegalemployeeBycompayId(cid);
    }

}

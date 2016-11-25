package com.oneclouder.pidm.user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 10:00 AM
 */
@Lazy
@MyBatisRepository
@Repository("companyEmployeeDao")
public interface ICompanyEmployeeDao extends IBaseDao<CompanyEmployee>{
    void insertCpnyAcstRep(Map<String, Object> map);

    public CompanyEmployee getLegalemployeeBycompayId(Integer cid);
}

package com.oneclouder.pidm.t_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.t_user.model.CompanyEmployee_t;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Lazy
@MyBatisRepository
@Repository("companyEmployeeDao_t")
public interface ICompanyEmployeeDao extends IBaseDao<CompanyEmployee_t>{
    void insertCpnyAcstRep(Map<String, Object> map);

    public CompanyEmployee_t getLegalemployeeBycompayId(Integer cid);
}

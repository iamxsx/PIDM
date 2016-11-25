package com.oneclouder.pidm.t_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.t_user.model.Company_t;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@MyBatisRepository
@Lazy
@Repository("companyDao_t")
public interface ICompanyDao extends IBaseDao<Company_t> {
    int accountCompanyByName(String companyName);

    Company_t findById(@Param("companyId") Integer company_id) throws SQLException;

    /**
     * 根据名字查询公司
     * @param companyName
     * @return
     */
    Company_t findCompanyByName(String companyName) throws SQLException;

    /**
     * 查找出所有的注册公司(只封装id与name)
     * @return
     */
    List<Company_t> findAllCompanies() throws SQLException;
}

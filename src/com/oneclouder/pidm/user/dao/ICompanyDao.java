package com.oneclouder.pidm.user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.user.model.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/10/16
 * Time: 4:33 PM
 */
@MyBatisRepository
@Lazy
@Repository("companyDao")
public interface ICompanyDao extends IBaseDao<Company> {
    int accountCompanyByName(String companyName);

    Company findById(@Param("companyId") Integer company_id) throws SQLException;

    /**
     * 根据名字查询公司
     * @param companyName
     * @return
     */
    Company findCompanyByName(String companyName) throws SQLException;

    /**
     * 查找出所有的注册公司(只封装id与name)
     * @return
     */
    List<Company> findAllCompanies() throws SQLException;
}

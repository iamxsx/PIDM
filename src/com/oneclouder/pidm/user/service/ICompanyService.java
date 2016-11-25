package com.oneclouder.pidm.user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.user.model.Company;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/10/16
 * Time: 4:29 PM
 */
public interface ICompanyService extends IBaseService<Company> {
    boolean companyIsExist(String companyName);

    /**
     * 创建新公司
     * @param company
     * @return
     * @throws SQLException
     */
    Integer createCompanny(Company company) throws SQLException;

    /**
     * 根据名字查询公司
     * @param companyName
     * @return
     */
    Company findCompanyByName(String companyName) throws SQLException;

    /**
     * 找出所有已注册的公司
     * @return
     */
    List<Company> findAllCompanies() throws SQLException;
}

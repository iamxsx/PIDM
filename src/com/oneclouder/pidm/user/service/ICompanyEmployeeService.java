package com.oneclouder.pidm.user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.user.model.CompanyEmployee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 9:55 AM
 */
public interface ICompanyEmployeeService extends IBaseService<CompanyEmployee>{
    /**
     * 插入一个公司的单个员工
     * @param companyEmployee
     * @param companyId
     * @return
     * @throws SQLException
     */
    Integer addEmployee(CompanyEmployee companyEmployee, Integer companyId) throws SQLException;

    /**
     * 插入一个公司的多个员工
     * @param introduceds
     * @param companyId
     * @return
     */
    List<Integer> addEmployees(List<CompanyEmployee> introduceds, Integer companyId) throws SQLException;

    /**
     * 添加公司协会代表人信息
     * @param cpnyAcstRep
     * @param associationId
     * @param companyId
     * @return
     * @throws SQLException
     */
    Integer addCpnyAcstRep(CompanyEmployee cpnyAcstRep, Integer associationId, Integer companyId) throws SQLException;

    public CompanyEmployee getLegalemployeeBycompayId(Integer cid);

}

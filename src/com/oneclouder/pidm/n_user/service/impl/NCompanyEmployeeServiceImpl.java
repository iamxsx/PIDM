package com.oneclouder.pidm.n_user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.n_user.dao.INCompanyDao;
import com.oneclouder.pidm.n_user.dao.INCompanyEmployeeDao;
import com.oneclouder.pidm.n_user.dao.INTCompanyEmployeeDao;
import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.service.INCompanyEmployeeService;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午9:00
 */
@Service("nCompanyEmployeeService")
public class NCompanyEmployeeServiceImpl extends
        BaseServiceImpl<NCompanyEmployee> implements INCompanyEmployeeService {
    //--------------------------------成员变量----------------------------------
    private INCompanyEmployeeDao nCompanyEmployeeDao;

    @Resource(name = "nCompanyEmployeeDao")
    public void setDao(INCompanyEmployeeDao nCompanyEmployeeDao) {
        super.setDao(nCompanyEmployeeDao);
        this.nCompanyEmployeeDao = nCompanyEmployeeDao;
    }

    @Resource(name = "nTCompanyEmployeeDao")
    private INTCompanyEmployeeDao nTCompanyEmployeeDao;

    //--------------------------------hxj----------------------------------
    /*@Override
    public void deleteUserInfo(Integer eid) {
        nCompanyEmployeeDao.deleteUserInfo(eid);
    }*/

    @Override
    @Transactional
    public void updateEmployeeInfo(NCompanyEmployee nCompanyEmployee) {
        nCompanyEmployeeDao.updateEmployeeInfo(nCompanyEmployee);
    }

    //--------------------------------zcb----------------------------------
    @Override
    /**通过公司ID 查找公司员工**/
    public List<NCompanyEmployee> getCompanyEmployeeByCId(Integer cId) {
        return nCompanyEmployeeDao.getCompanyEmployeeByCId(cId);
    }
    /**存公司员工入 临时表* */
    @Override
    public void insertToNTempCompanyEmp(NCompanyEmployee nCompanyEmployee) {
        nCompanyEmployeeDao.insertToNTempCompanyEmp(nCompanyEmployee);
    }
    /**
     * 根据公司ID 删除员工ID
     */
    @Override
    public void deleteByCompanyId(Integer companyId) {
       nCompanyEmployeeDao.deleteByCompanyId(companyId);
    }
    //------------------------------xucb----------------------------------
    /**
     * 通过临时表 公司ID 查找公司员工
     *
     * @param cId
     * @return
     */
    @Override
    public List<NCompanyEmployee> getTCompanyEmployeeByCId(Integer cId) {
        return nTCompanyEmployeeDao.getCompanyEmployeeByCId(cId);
    }


    //--------------------------------zjf----------------------------------
    public Integer creatCompanyEmployee(Integer cpmpanyId, NCompanyEmployee employee, Integer target) throws SQLException {
        if (employee != null) {
            if (!StringUtils.isEmpty(employee.getName())) {
                NCompany company = new NCompany();
                company.setId(cpmpanyId);
                employee.setCompany(company);
                //根据target的值来插入正式表还是临时表
                if (target == 1) {
                    nCompanyEmployeeDao.insert(employee);
                } else if (target == 2) {
                    nTCompanyEmployeeDao.insert(employee);
                }
                return employee.getId();
            }
        }
        return null;
    }

    @Override
    public List<Integer> creatCompanyEmployees(Integer cpmpanyId, List<NCompanyEmployee> employees, Integer target) throws SQLException {
        List<Integer> employeeIds = null;
        if (employees != null && employees.size() > 0) {
            employeeIds = new ArrayList<Integer>();
            // TODO 有时间做成批量插入
            for (NCompanyEmployee introduced : employees) {
                employeeIds.add(creatCompanyEmployee(cpmpanyId, introduced, target));
            }
        }
        return employeeIds;
    }

}

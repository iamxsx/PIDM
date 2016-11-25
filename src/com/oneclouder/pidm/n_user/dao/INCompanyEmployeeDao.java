package com.oneclouder.pidm.n_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:49
 */
@Lazy
@MyBatisRepository
@Repository("nCompanyEmployeeDao")
public interface INCompanyEmployeeDao extends IBaseDao<NCompanyEmployee> {
    //--------------------------------成员变量----------------------------------




    //--------------------------------hxj----------------------------------
//    void deleteUserInfo(Integer eid);
    void updateEmployeeInfo(NCompanyEmployee nCompanyEmployee);


    //--------------------------------zcb----------------------------------
    /**通过公司ID 查找公司员工**/
    List<NCompanyEmployee> getCompanyEmployeeByCId(Integer cId);
    /**存公司员工入 临时表* */
    void insertToNTempCompanyEmp(NCompanyEmployee nCompanyEmployee);
    /**
     * 根据公司ID 删除员工ID
     */
    void deleteByCompanyId(Integer companyId);


    //--------------------------------zjf----------------------------------


}

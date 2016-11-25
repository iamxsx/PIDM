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
@Repository("nTCompanyEmployeeDao")
public interface INTCompanyEmployeeDao extends IBaseDao<NCompanyEmployee> {
    //--------------------------------成员变量----------------------------------




    //--------------------------------hxj----------------------------------
    void updateTEmployee(NCompanyEmployee nCompanyEmployee);



    //--------------------------------zcb----------------------------------



    //--------------------------------xucb----------------------------------
    /**通过公司ID 查找公司员工**/
    List<NCompanyEmployee> getCompanyEmployeeByCId(Integer cId);
}

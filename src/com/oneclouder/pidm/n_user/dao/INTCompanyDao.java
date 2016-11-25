package com.oneclouder.pidm.n_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.n_user.model.NCompany;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:49
 */
@MyBatisRepository
@Lazy
@Repository("nTCompanyDao")
public interface INTCompanyDao extends IBaseDao<NCompany> {
    //--------------------------------成员变量----------------------------------




    //--------------------------------hxj----------------------------------
    void updateTCompany(NCompany nCompany);


    //--------------------------------zcb----------------------------------



    //--------------------------------zjf----------------------------------

}

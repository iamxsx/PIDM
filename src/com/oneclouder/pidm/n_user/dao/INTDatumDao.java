package com.oneclouder.pidm.n_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.n_user.model.NDatum;
import org.springframework.stereotype.Repository;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:48
 */
@MyBatisRepository
@Repository("nTDatumDao")
public interface INTDatumDao extends IBaseDao<NDatum> {

    //--------------------------------成员变量----------------------------------



    //--------------------------------hxj----------------------------------



    //--------------------------------zcb----------------------------------
    //***通过用户ID查找NDatum对象
    NDatum findByUserId(Integer nUserId);


    //--------------------------------zjf----------------------------------

}

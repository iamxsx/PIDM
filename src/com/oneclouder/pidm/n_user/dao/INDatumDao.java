package com.oneclouder.pidm.n_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.n_user.model.NDatum;
import com.oneclouder.pidm.n_user.model.NUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:48
 */
@MyBatisRepository
@Repository("nDatumDao")
public interface INDatumDao extends IBaseDao<NDatum> {


    //--------------------------------成员变量----------------------------------



    //--------------------------------hxj----------------------------------



    //--------------------------------zcb----------------------------------
    /**
     * 通过用户ID 更新 上传文件路径
     */
    void updateFileUrlByUId(NDatum datum);


    //--------------------------------zjf----------------------------------

}

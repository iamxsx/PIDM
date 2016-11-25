package com.oneclouder.pidm.n_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.n_user.model.NUser;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:48
 */
@MyBatisRepository
@Repository("nTUserDao")
public interface INTUserDao extends IBaseDao<NUser> {
    //--------------------------------成员变量----------------------------------


    //--------------------------------hxj----------------------------------
    List<Map> selectTUserAll(Integer uid);

    void updateTUser(NUser nUser);

    //--------------------------------zcb----------------------------------

    //--------------------------------xucb---------------------------------

    /**
     * 根据临时表用户Id查询临时资源表中文件路径
     * @param uid
     * @return
     */
    public String findTempUrl(Integer uid);


    //--------------------------------zjf----------------------------------

    /**
     * Created By IntelliJ IDEA
     * 根据verifycode查询tuser（只适用于账号激活前）
     * @param verifycode 验证码
     * @return user 临时表用户
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-19 下午3:07
     */
    NUser findByVerifycode(String verifycode);

}

package com.oneclouder.pidm.n_user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.n_user.dao.INDatumDao;
import com.oneclouder.pidm.n_user.dao.INTDatumDao;
import com.oneclouder.pidm.n_user.model.NDatum;
import com.oneclouder.pidm.n_user.service.INDatumService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-17
 * @Time: 上午8:09
 */
@Service("nDatumService")
public class NDatumServiceImpl extends BaseServiceImpl<NDatum> implements INDatumService {
    //-------------------成员变量--------------------
    private INDatumDao nDatumDao;

    @Resource(name = "nDatumDao")
    public void setDao(INDatumDao nDatumDao) {
        super.setDao(nDatumDao);
        this.nDatumDao = nDatumDao;
    }

    @Resource(name = "nTDatumDao")
    private INTDatumDao nTDatumDao;


    //-------------------hxj--------------------




    //-------------------zcb--------------------
    /**
     * 通过用户ID找NDatum
     * @param nUserId
     */
    @Override
    public NDatum findByUserId(Integer nUserId) {
        return nTDatumDao.findByUserId(nUserId);
    }

    /**
     * 通过用户ID 更新 上传文件路径
     */
    @Override
    public void updateFileUrlByUId(NDatum datum) {
        nDatumDao.updateFileUrlByUId(datum);
    }


    //-------------------zjf--------------------
    @Override
    public void createDatum(NDatum datum, Integer target) throws SQLException {
        if (target == 1) {
            nDatumDao.insert(datum);
        } else if (target == 2) {
            nTDatumDao.insert(datum);
        }
    }

}

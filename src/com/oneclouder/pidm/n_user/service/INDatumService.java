package com.oneclouder.pidm.n_user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.n_user.model.NDatum;

import java.sql.SQLException;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-17
 * @Time: 上午8:07
 */
public interface INDatumService extends IBaseService<NDatum> {
    /**
     * Created By IntelliJ IDEA
     * 插入根据不同标识，插入资源表记录
     * @param datum 资源bean
     * @param target 插入标识：1表示正式表 2表示临时表
     * @return
     * @throws SQLException
     * @Author: AngryFeng
     * @Date: 16-10-20 上午11:30
     */
    void createDatum(NDatum datum, Integer target) throws SQLException;

    /**
     * 通过用户ID找NDatum
     * @param nUserId
     */
    NDatum findByUserId(Integer nUserId);

    /**
     * 通过用户ID 更新 上传文件路径
     */
    void updateFileUrlByUId(NDatum datum);
}

package com.oneclouder.pidm.user.service;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.user.model.Description;

import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 10:01 AM
 */
public interface IDescriptionService extends IBaseService<Description> {
    public Description getDescriptionByAssAndComId(Integer companyId, Integer associationId);

    public Integer getDescriptionLength(Integer companyId, Integer associationId);

    public void insertDes(Map map);
}

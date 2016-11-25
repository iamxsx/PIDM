package com.oneclouder.pidm.user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.user.model.Description;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 10:03 AM
 */
@MyBatisRepository
@Repository("descriptionDao")
public interface IDescriptionDao extends IBaseDao<Description>{
    public Description getDescriptionByAssAndComId(Map map);

    public Integer getDescriptionLength(Map map);

    public void insertDes(Map map);
}

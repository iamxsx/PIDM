package com.oneclouder.pidm.t_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.t_user.model.Description_t;
import org.springframework.stereotype.Repository;

import java.util.Map;


@MyBatisRepository
@Repository("descriptionDao_t")
public interface IDescriptionDao extends IBaseDao<Description_t>{
    public Description_t getDescriptionByAssAndComId(Map map);

    public Integer getDescriptionLength(Map map);

    public void insertDes(Map map);
}

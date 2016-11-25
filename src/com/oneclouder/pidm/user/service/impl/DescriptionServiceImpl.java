package com.oneclouder.pidm.user.service.impl;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.user.dao.IDescriptionDao;
import com.oneclouder.pidm.user.model.Description;
import com.oneclouder.pidm.user.service.IDescriptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 10:01 AM
 */
@Service("descriptionService")
public class DescriptionServiceImpl extends BaseServiceImpl<Description> implements IDescriptionService {
    private IDescriptionDao descriptionDao;

    @Resource(name = "descriptionDao")
    public void setDao(IDescriptionDao descriptionDao) {
        super.setDao(descriptionDao);
        this.descriptionDao = descriptionDao;
    }

    @Override
    public Description getDescriptionByAssAndComId(Integer companyId, Integer associationId) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("companyId",companyId);
        map.put("associationId",associationId);
        return descriptionDao.getDescriptionByAssAndComId(map);
    }

    @Override
    public Integer getDescriptionLength(Integer companyId, Integer associationId) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("companyId",companyId);
        map.put("associationId",associationId);
        return descriptionDao.getDescriptionLength(map);
    }

    @Override
    public void insertDes(Map map) {
        descriptionDao.insertDes(map);
    }
}

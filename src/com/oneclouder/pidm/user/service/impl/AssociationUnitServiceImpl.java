package com.oneclouder.pidm.user.service.impl;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.user.dao.IAssociationUnitDao;
import com.oneclouder.pidm.user.model.AssociationUnit;
import com.oneclouder.pidm.user.service.IAssociationUnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 9:52 AM
 */
@Service("associationUnitService")
public class AssociationUnitServiceImpl extends BaseServiceImpl<AssociationUnit> implements IAssociationUnitService {
    private IAssociationUnitDao associationUnitDao;

    @Resource(name = "associationUnitDao")
    public void setDao(IAssociationUnitDao dao) {
        super.setDao(dao);
        this.associationUnitDao = dao;
    }


    @Override
    public Integer findId(Map map) {
        return associationUnitDao.findId(map);
    }
}

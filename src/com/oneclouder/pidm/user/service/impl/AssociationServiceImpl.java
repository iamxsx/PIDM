package com.oneclouder.pidm.user.service.impl;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.user.dao.IAssociationDao;
import com.oneclouder.pidm.user.model.Association;
import com.oneclouder.pidm.user.service.IAssociationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 9:46 AM
 */
@Service("associationService")
public class AssociationServiceImpl extends BaseServiceImpl<Association> implements IAssociationService  {
    private IAssociationDao associationDao;

    @Resource(name = "associationDao")
    public void setDao(IAssociationDao associationDao) {
        super.setDao(associationDao);
        this.associationDao = associationDao;
    }
}

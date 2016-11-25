package com.oneclouder.pidm.user.service;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.user.model.AssociationUnit;

import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 9:52 AM
 */
public interface IAssociationUnitService extends IBaseService<AssociationUnit> {

    public Integer findId(Map map);
}

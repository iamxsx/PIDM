package com.oneclouder.pidm.user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.user.model.AssociationUnit;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 9:54 AM
 */
@Lazy
@MyBatisRepository
@Repository("associationUnitDao")
public interface IAssociationUnitDao extends IBaseDao<AssociationUnit> {
    public Integer findId(Map map);
}

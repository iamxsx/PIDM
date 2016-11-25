package com.oneclouder.pidm.user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.user.model.Association;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 9:51 AM
 */
@Lazy
@MyBatisRepository
@Repository("associationDao")
public interface IAssociationDao extends IBaseDao<Association>{


}

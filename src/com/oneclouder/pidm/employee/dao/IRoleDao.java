package com.oneclouder.pidm.employee.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.employee.model.Role;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Created by PhychoLee on 9/10/16 10:02 AM.
 */
@MyBatisRepository
@Repository
@Lazy
public interface IRoleDao extends IBaseDao<Role> {

}

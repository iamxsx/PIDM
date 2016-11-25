package com.oneclouder.pidm.menu.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.model.Privilege;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/09/16.
 */
@MyBatisRepository
@Repository()
@Lazy
public interface IPrivilegeDao {

    List<Privilege> findByEmployee(@Param("employeeId") Integer employeeId) throws SQLException;
}

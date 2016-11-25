package com.oneclouder.pidm.logs.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.logs.model.ExceptionLogInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-10-8.
 */
@MyBatisRepository
@Repository("logDao")
public interface ILogDao {

    public void recordException(ExceptionLogInfo info);

    List<ExceptionLogInfo> findLogsByPage(Map<String, Object> params);

    int getLogCount();
}

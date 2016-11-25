package com.oneclouder.pidm.logs.service;

import com.oneclouder.pidm.logs.model.ExceptionLogInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-10-8.
 */
public interface ILogService {

    public void recordException(ExceptionLogInfo info);


    List<ExceptionLogInfo> findLogsByPage(Map<String, Object> params);

    int getLogCount();
}

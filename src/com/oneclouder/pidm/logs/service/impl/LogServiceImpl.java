package com.oneclouder.pidm.logs.service.impl;

import com.oneclouder.pidm.logs.dao.ILogDao;
import com.oneclouder.pidm.logs.model.ExceptionLogInfo;
import com.oneclouder.pidm.logs.service.ILogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-10-8.
 */
@Service("logService")
public class LogServiceImpl implements ILogService{

    @Resource
    private ILogDao logDao;

    @Override
    public void recordException(ExceptionLogInfo info) {
        logDao.recordException(info);
    }

    @Override
    public List<ExceptionLogInfo> findLogsByPage(Map<String, Object> params) {
        return logDao.findLogsByPage(params);
    }

    @Override
    public int getLogCount() {
        return logDao.getLogCount();
    }
}

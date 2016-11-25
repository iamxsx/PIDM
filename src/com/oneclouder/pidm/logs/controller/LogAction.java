package com.oneclouder.pidm.logs.controller;

import com.oneclouder.pidm.logs.model.ExceptionLogInfo;
import com.oneclouder.pidm.logs.service.ILogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-10-8.
 */
@Controller
@RequestMapping("log")
public class LogAction {

    @Resource
    private ILogService logService;

    @RequestMapping("")
    public String toPage(){
        return "back/system/log";
    }

    @RequestMapping("findExceptionLogsByPage")
    @ResponseBody
    public Map<String,Object> findExceptionLogsByPage(Integer limit, Integer offset){
        Map<String,Object> params = new HashMap<>();
        params.put("limit",limit);
        params.put("offset",offset);
        List<ExceptionLogInfo> rows = logService.findLogsByPage(params);
        int total = logService.getLogCount();
        params.clear();
        params.put("rows",rows);
        params.put("total",total);
        return params;
    }


}

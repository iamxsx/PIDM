package com.oneclouder.pidm.logs.aop;

import com.oneclouder.pidm.logs.model.ExceptionLogInfo;
import com.oneclouder.pidm.logs.service.ILogService;
import com.oneclouder.pidm.util.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 处理异常，输出日志
 * Created by clouder on 16-10-8.
 */
@Component
@Aspect
public class ExceptionLogAspect {

    @Resource()
    private ILogService logService;


    @AfterThrowing(pointcut = "execution(* com.oneclouder.pidm.*.*.*.*(..))", throwing = "e")
    public void recordException(JoinPoint joinPoint, Throwable e) {
        //获取抛出异常的方法
        String method = joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName();
        //获取方法的参数
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            params += "(";
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += joinPoint.getArgs()[i] + ",";
            }
            params += ")";
        }
        //获取异常类型
        String exceptionName = e.getClass().getName();
        //获取异常的详细信息
        String exceptionDesc = "";
        for (StackTraceElement element:e.getStackTrace()){
            exceptionDesc += element+"\n";
        }
        ExceptionLogInfo info = new ExceptionLogInfo();
        info.setExceptionDesc(exceptionDesc);
        info.setExceptionMethod(method);
        info.setExceptionName(exceptionName);
        info.setMethodArgs(params);
        info.setTime(DateUtil.getCurrentDate());
        logService.recordException(info);
    }


}

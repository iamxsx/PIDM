package com.oneclouder.pidm.logs.model;

/**
 * Created by clouder on 16-10-8.
 */
public class ExceptionLogInfo {
    private Integer id;
    /**
     * 异常名
     */
    private String exceptionName;
    /**
     * 异常描述
     */
    private String exceptionDesc;
    /**
     * 抛出异常的方法
     */
    private String exceptionMethod;
    /**
     * 异常方法的参数
     */
    private String methodArgs;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public String getExceptionMethod() {
        return exceptionMethod;
    }

    public void setExceptionMethod(String exceptionMethod) {
        this.exceptionMethod = exceptionMethod;
    }

    public String getMethodArgs() {
        return methodArgs;
    }

    public void setMethodArgs(String methodArgs) {
        this.methodArgs = methodArgs;
    }
}

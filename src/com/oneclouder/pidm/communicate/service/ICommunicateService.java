package com.oneclouder.pidm.communicate.service;

import com.oneclouder.pidm.communicate.model.Communicate;
import com.oneclouder.pidm.communicate.model.FormBean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by zheng.
 */
public interface ICommunicateService {
    //添加交流到数据库
    public void addCommunicate(Communicate communicate) throws SQLException;
    //查找所有交流项
    public List<FormBean> findCommunicateByPag(Map<String,Object> params) throws SQLException;
    //更改交流项状态为status，0表示为未回复，1表示为问题已回复
    public void changeStatus(Integer communicateId,Integer status) throws  SQLException;

    Communicate findCommunicateById(Integer communicateId);

    Integer getTotal(Map<String,Object> params) throws SQLException;

    Integer getTotal2(Map<String,Object> params) throws SQLException;

    List<FormBean> findComByReplyPage(Map<String, Object> params) throws SQLException;

    List<Communicate> findComByUIdPage(Map<String, Object> params);

    Integer getTotal3(Integer uId);
}

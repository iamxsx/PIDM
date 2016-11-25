package com.oneclouder.pidm.communicate.service.impl;

import com.oneclouder.pidm.communicate.dao.ICommunicateDao;
import com.oneclouder.pidm.communicate.model.Communicate;
import com.oneclouder.pidm.communicate.model.FormBean;
import com.oneclouder.pidm.communicate.service.ICommunicateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by zheng.
 */
@Service("communicateService")
public class CommunicateServiceImpl implements ICommunicateService {

    @Resource(name = "communicateDao")
    ICommunicateDao communicateDao;

    @Override
    //添加交流到数据库
    public void addCommunicate(Communicate communicate) throws SQLException{
        communicateDao.addCommunicate(communicate);
    }

    @Override
    //查找所有交流项
    public List<FormBean> findCommunicateByPag(Map<String,Object> params) throws SQLException{
        return communicateDao.findCommunicateByPag(params);
    }

    @Override
    //更改交流项状态为status，0表示为未回复，1表示为问题已回复
    public void changeStatus(Integer communicateId,Integer status) throws  SQLException {
        communicateDao.changeStatus(communicateId,status);
    }

    @Override
    public Communicate findCommunicateById(Integer communicateId) {
        return communicateDao.findCommunicateById(communicateId);
    }

    @Override
    public Integer getTotal(Map<String,Object> params) throws SQLException {
        return communicateDao.getTotal(params);
    }

    @Override
    public Integer getTotal2(Map<String,Object> params) throws SQLException {
        return communicateDao.getTotal2(params);
    }

    @Override
    public List<FormBean> findComByReplyPage(Map<String, Object> params) throws SQLException {
        return communicateDao.findComByReplyPage(params);
    }

    @Override
    public List<Communicate> findComByUIdPage(Map<String, Object> params) {
        return communicateDao.findComByUIdPage(params);
    }

    @Override
    public Integer getTotal3(Integer uId) {
        return communicateDao.getTotal3(uId);
    }


}

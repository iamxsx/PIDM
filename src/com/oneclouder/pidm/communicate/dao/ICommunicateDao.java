package com.oneclouder.pidm.communicate.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.communicate.model.Communicate;
import com.oneclouder.pidm.communicate.model.FormBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by zheng.
 */
@MyBatisRepository
@Repository("communicateDao")
public interface ICommunicateDao {
    //添加交流到数据库
    public void addCommunicate(Communicate communicate) throws SQLException;
    //查找所有交流项
    public List<FormBean> findCommunicateByPag(Map<String,Object> params) throws SQLException;
    //更改交流项状态为status，0表示为未回复，1表示为问题已回复
    public void changeStatus(@Param("communicateId") Integer communicate_id,@Param("status") Integer status) throws SQLException;

    public Communicate findCommunicateById(@Param("communicateId") Integer communicateId);
    //查找total
    public Integer getTotal(Map<String,Object> params) throws SQLException;
    //查找total
    public Integer getTotal2(Map<String,Object> params) throws SQLException;

    public List<FormBean> findComByReplyPage(Map<String,Object> params) throws SQLException;

    List<Communicate> findComByUIdPage(Map<String, Object> params);

    Integer getTotal3(Integer uId);
}

package com.oneclouder.pidm.communicate.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.communicate.model.Reply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zheng.
 */
@MyBatisRepository
@Repository("replyDao")
public interface IReplyDao {
    //添加
    public void addReply(Reply reply) throws SQLException;

    public List<Reply> findReplyByComId(@Param("communicate_id") Integer communicate_id) throws SQLException;
}

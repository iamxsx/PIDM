package com.oneclouder.pidm.communicate.service;

import com.oneclouder.pidm.communicate.model.Reply;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zheng.
 */
public interface IReplyService {
    //添加回复
    public void addReply(Reply reply) throws SQLException;
    //通过交流表ID 查询 回复
    public List<Reply> findReplyByComId(Integer communicate_id) throws SQLException;
}

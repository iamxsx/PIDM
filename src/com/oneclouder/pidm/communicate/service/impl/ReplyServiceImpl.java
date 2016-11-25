package com.oneclouder.pidm.communicate.service.impl;

import com.oneclouder.pidm.communicate.dao.IReplyDao;
import com.oneclouder.pidm.communicate.model.Reply;
import com.oneclouder.pidm.communicate.service.IReplyService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zheng.
 */
@Service("replyService")
public class ReplyServiceImpl implements IReplyService {

    @Resource(name = "replyDao")
    IReplyDao replyDao;

    @Override
    public void addReply(Reply reply) throws SQLException {
        replyDao.addReply(reply);
    }

    @Override
    public List<Reply> findReplyByComId(Integer communicate_id) throws SQLException {
        return replyDao.findReplyByComId(communicate_id);
    }
}

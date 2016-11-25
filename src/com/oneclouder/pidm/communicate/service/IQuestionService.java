package com.oneclouder.pidm.communicate.service;

import com.oneclouder.pidm.communicate.model.Question;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zheng.
 */
public interface IQuestionService {
    //添加 再提问 到数据库
    public void addQuestion(Question question) throws SQLException;
    //根据交流表ID查 再提问的问题
    public List<Question> findQuestionByComId(Integer communicate_id) throws SQLException;
}

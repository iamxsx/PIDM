package com.oneclouder.pidm.communicate.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.communicate.model.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zheng.
 */
@MyBatisRepository
@Repository("questionDao")
public interface IQuestionDao {
    //添加 再提问 到数据库
    public void addQuestion(Question question) throws SQLException;

    public List<Question> findQuestionByComId(@Param("communicate_id")Integer communicate_id) throws SQLException;
}

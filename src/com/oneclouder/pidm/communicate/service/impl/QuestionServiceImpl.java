package com.oneclouder.pidm.communicate.service.impl;

import com.oneclouder.pidm.communicate.dao.IQuestionDao;
import com.oneclouder.pidm.communicate.model.Question;
import com.oneclouder.pidm.communicate.service.IQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zheng.
 */
@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {
    @Resource(name = "questionDao")
    IQuestionDao questionDao;

    @Override
    public void addQuestion(Question question) throws SQLException {
        questionDao.addQuestion(question);
    }

    @Override
    public List<Question> findQuestionByComId(Integer communicate_id) throws SQLException {
        return questionDao.findQuestionByComId(communicate_id);
    }
}

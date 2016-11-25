package com.oneclouder.pidm.survey.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.survey.model.TTopic;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/7/16.
 */
@Lazy
@MyBatisRepository
@Repository("topicDao")
public interface ITopicDao extends IBaseDao<TTopic> {
    List<TTopic> findTopicBySurveyid(Integer survey_id);

    void insertTopic(TTopic tTopic) throws SQLException;

    void updateTopicContent(Map map);
}

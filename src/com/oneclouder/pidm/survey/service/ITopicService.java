package com.oneclouder.pidm.survey.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.survey.model.TTopic;

import java.sql.SQLException;

/**
 * Created by clouder on 10/7/16.
 */
public interface ITopicService extends IBaseService<TTopic> {
    void insertTopic(TTopic tTopic) throws SQLException;

    void deleteTopicById(Integer id) throws SQLException;

    void updateTopic(String Content,Integer id) throws SQLException;
}

package com.oneclouder.pidm.survey.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.survey.dao.ITopicDao;
import com.oneclouder.pidm.survey.model.TTopic;
import com.oneclouder.pidm.survey.service.ITopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by clouder on 10/7/16.
 */
@Service("topicService")
public class TopicService extends BaseServiceImpl<TTopic> implements ITopicService {

    @Resource(name = "topicDao")
    ITopicDao topicDao;

    @Override
    public void insertTopic(TTopic tTopic) throws SQLException {
        topicDao.insert(tTopic);
    }

    @Override
    public void deleteTopicById(Integer id) throws SQLException {
        topicDao.deleteById(id);
    }

    @Override
    public void updateTopic(String Content, Integer id) throws SQLException {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("topicContent",Content);
        map.put("id",id);
        topicDao.updateTopicContent(map);
    }

}

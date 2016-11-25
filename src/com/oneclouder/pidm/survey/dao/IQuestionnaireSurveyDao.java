package com.oneclouder.pidm.survey.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.survey.model.TQuestionnaireSurvey;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/7/16.
 */
@Lazy
@MyBatisRepository
@Repository("questionnaireSurveyDao")
public interface IQuestionnaireSurveyDao extends IBaseDao<TQuestionnaireSurvey> {

    TQuestionnaireSurvey findSurvey();

    void updateJoinNum(Integer id);

    void updateTitle(Map map);

    void updateDub(TQuestionnaireSurvey questionnaireSurvey);

    Integer findStatus();
    /** 查看历史调查表统计    zcb*/
    List<TQuestionnaireSurvey> historySurveyInfo();
    /** 通过ID查找调查表     zcb*/
    TQuestionnaireSurvey findSurveyInfoById(Integer id);

}

package com.oneclouder.pidm.survey.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.survey.model.TQuestionnaireSurvey;
import com.oneclouder.pidm.survey.webBean.SurveyFormBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouder on 10/7/16.
 */
public interface IQuestionnaireSurveyService extends IBaseService<TQuestionnaireSurvey> {

    void updateJoinNum(Integer id);

    SurveyFormBean SurveyInfo();

    void insertSurvey(TQuestionnaireSurvey questionnaireSurvey)throws SQLException;

    void updateTitle(String title,Integer id);

    void updateDub(TQuestionnaireSurvey questionnaireSurvey);

    Integer findStatus();

    TQuestionnaireSurvey findSurvey();
    /**
     * 查看历史调查表统计    zcb
     */
    List<SurveyFormBean> historySurveyInfo();

    /**
     * 通过ID查找调查表     zcb
     */
    SurveyFormBean findSurveyInfoById(Integer id);

    void overSurvey(Integer id);
}

package com.oneclouder.pidm.survey.test;

import com.oneclouder.pidm.survey.model.TQuestionnaireSurvey;
import com.oneclouder.pidm.survey.service.IQuestionnaireSurveyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by clouder on 10/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class QuestionnaireSurveyServiceTest {

    @Resource(name = "questionnaireSurveyService")
    IQuestionnaireSurveyService questionnaireSurveyService;

    @Test
    public void questionnaireSurveyDao(){
        System.out.println(questionnaireSurveyService.SurveyInfo());
    }

    @Test
    public void testinsert() throws SQLException {
        TQuestionnaireSurvey questionnaireSurvey = new TQuestionnaireSurvey();
        questionnaireSurvey.setTitle("aaa");
        questionnaireSurvey.setTitleNum(1);
        questionnaireSurveyService.insertSurvey(questionnaireSurvey);
        System.out.println(questionnaireSurvey.getId());

    }
}

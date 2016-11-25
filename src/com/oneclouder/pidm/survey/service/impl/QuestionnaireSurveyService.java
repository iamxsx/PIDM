package com.oneclouder.pidm.survey.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.n_user.dao.INCompanyDao;
import com.oneclouder.pidm.survey.dao.IOptionDao;
import com.oneclouder.pidm.survey.dao.IQuestionnaireSurveyDao;
import com.oneclouder.pidm.survey.dao.ITopicDao;
import com.oneclouder.pidm.survey.model.TOption;
import com.oneclouder.pidm.survey.model.TQuestionnaireSurvey;
import com.oneclouder.pidm.survey.model.TTopic;
import com.oneclouder.pidm.survey.service.IQuestionnaireSurveyService;
import com.oneclouder.pidm.survey.webBean.SurveyFormBean;
import com.oneclouder.pidm.survey.webBean.TopicFormBean;
import com.oneclouder.pidm.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

@Service("questionnaireSurveyService")
public class QuestionnaireSurveyService extends BaseServiceImpl<TQuestionnaireSurvey> implements IQuestionnaireSurveyService {

    @Resource(name = "questionnaireSurveyDao")
    IQuestionnaireSurveyDao questionnaireSurveyDao;

    @Resource(name = "topicDao")
    ITopicDao topicDao;

    @Resource(name = "optionDao")
    IOptionDao optionDao;

    @Override
    public void updateJoinNum(Integer id) {
        questionnaireSurveyDao.updateJoinNum(id);
    }

    /**
     * 将调查表的信息封装到SurveyFormBean中
     * @return
     */
    @Override
    public SurveyFormBean SurveyInfo() {
        TQuestionnaireSurvey questionnaireSurvey = questionnaireSurveyDao.findSurvey();
        List<TTopic> topic = topicDao.findTopicBySurveyid(questionnaireSurvey.getId());
        List<TOption> option = optionDao.findOptionBySid(questionnaireSurvey.getId());
        SurveyFormBean surveyFormBean = new SurveyFormBean();

        List<TopicFormBean> topicFormBeanList = new ArrayList<>();

        surveyFormBean.setQuestionnaireSurvey(questionnaireSurvey);
        for (TTopic top:topic) {
            TopicFormBean topicFormBean = new TopicFormBean();
            topicFormBean.setTopicContent(top.getTopicContent());
            topicFormBean.setNature(top.getNature());
            topicFormBean.setTid(top.getId());
            List<TOption> optionContentList = new ArrayList<>();
            for (TOption op: option) {
                if (Objects.equals(top.getId(), op.getTopicId())){
                    optionContentList.add(op);
                }
            }
            topicFormBean.setOption(optionContentList);
            topicFormBeanList.add(topicFormBean);
        }
        surveyFormBean.setTopic(topicFormBeanList);
        return surveyFormBean;
    }

    @Override
    @Transactional
    public void insertSurvey(TQuestionnaireSurvey questionnaireSurvey) throws SQLException {
        String time = DateUtil.getCurrentDate();
        questionnaireSurvey.setStartTime(time);

        TQuestionnaireSurvey questionnaireSurvey1 = questionnaireSurveyDao.findSurvey();
        questionnaireSurvey1.setStatus(2);
        questionnaireSurvey1.setEndTime(time);
        questionnaireSurveyDao.updateDub(questionnaireSurvey1);

        questionnaireSurveyDao.insert(questionnaireSurvey);
    }

    @Override
    public void updateTitle(String title, Integer id) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("title",title);
        map.put("id",id);
        questionnaireSurveyDao.updateTitle(map);
    }

    @Override
    public void updateDub(TQuestionnaireSurvey questionnaireSurvey) {
        questionnaireSurveyDao.updateDub(questionnaireSurvey);
    }

    @Override
    public Integer findStatus() {
        return questionnaireSurveyDao.findStatus();
    }


    public TQuestionnaireSurvey findSurvey(){
        return questionnaireSurveyDao.findSurvey();
    }

    /**
     * 查看历史调查表统计    zcb
     */
    @Override
    public List<SurveyFormBean> historySurveyInfo() {
        List<TQuestionnaireSurvey> q = questionnaireSurveyDao.historySurveyInfo();
        List<SurveyFormBean> surveyFormBeens = new ArrayList<>();
        for(TQuestionnaireSurvey questionnaireSurvey : q){
            List<TTopic> topic = topicDao.findTopicBySurveyid(questionnaireSurvey.getId());
            List<TOption> option = optionDao.findOptionBySid(questionnaireSurvey.getId());
            SurveyFormBean surveyFormBean = new SurveyFormBean();
            surveyFormBean.setQuestionnaireSurvey(questionnaireSurvey);
            List<TopicFormBean> topicFormBeanList = new ArrayList<>();

            for (TTopic top:topic) {
                TopicFormBean topicFormBean = new TopicFormBean();
                topicFormBean.setTopicContent(top.getTopicContent());
                topicFormBean.setNature(top.getNature());
                topicFormBean.setTid(top.getId());
                List<TOption> optionContentList = new ArrayList<>();
                for (TOption op: option) {
                    if (Objects.equals(top.getId(), op.getTopicId())){
                        optionContentList.add(op);
                    }
                }
                topicFormBean.setOption(optionContentList);
                topicFormBeanList.add(topicFormBean);
            }
            surveyFormBean.setTopic(topicFormBeanList);
            surveyFormBeens.add(surveyFormBean);
        }
        return surveyFormBeens;
    }

    /**
     * 通过ID查找调查表     zcb
     */
    @Override
    public SurveyFormBean findSurveyInfoById(Integer id) {
        TQuestionnaireSurvey questionnaireSurvey = questionnaireSurveyDao.findSurveyInfoById(id);
        List<TTopic> topic = topicDao.findTopicBySurveyid(questionnaireSurvey.getId());
        List<TOption> option = optionDao.findOptionBySid(questionnaireSurvey.getId());
        SurveyFormBean surveyFormBean = new SurveyFormBean();

        List<TopicFormBean> topicFormBeanList = new ArrayList<>();

        surveyFormBean.setQuestionnaireSurvey(questionnaireSurvey);
        for (TTopic top:topic) {
            TopicFormBean topicFormBean = new TopicFormBean();
            topicFormBean.setTopicContent(top.getTopicContent());
            topicFormBean.setNature(top.getNature());
            topicFormBean.setTid(top.getId());
            List<TOption> optionContentList = new ArrayList<>();
            for (TOption op: option) {
                if (Objects.equals(top.getId(), op.getTopicId())){
                    optionContentList.add(op);
                }
            }
            topicFormBean.setOption(optionContentList);
            topicFormBeanList.add(topicFormBean);
        }
        surveyFormBean.setTopic(topicFormBeanList);
        return surveyFormBean;
    }

    //结束调查表
    @Override
    public void overSurvey(Integer id) {
        String time = DateUtil.getCurrentDate();
        TQuestionnaireSurvey questionnaireSurvey = questionnaireSurveyDao.findSurvey();
        questionnaireSurvey.setId(id);
        questionnaireSurvey.setStatus(2);
        questionnaireSurvey.setEndTime(time);
        questionnaireSurveyDao.updateDub(questionnaireSurvey);
    }

}

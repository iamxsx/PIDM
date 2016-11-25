package com.oneclouder.pidm.survey.webBean;

import com.oneclouder.pidm.survey.model.TQuestionnaireSurvey;

import java.util.List;

/**
 * Created by clouder on 10/7/16.
 */
public class SurveyFormBean {
    private TQuestionnaireSurvey questionnaireSurvey;

    private List<TopicFormBean> topic;

    private Integer singselection;

    private Integer mulselect;

    private Integer comp;

    public Integer getSingselection() {
        return singselection;
    }

    public void setSingselection(Integer singselection) {
        this.singselection = singselection;
    }

    public Integer getMulselect() {
        return mulselect;
    }

    public void setMulselect(Integer mulselect) {
        this.mulselect = mulselect;
    }

    public Integer getComp() {
        return comp;
    }

    public void setComp(Integer comp) {
        this.comp = comp;
    }


    public TQuestionnaireSurvey getQuestionnaireSurvey() {
        return questionnaireSurvey;
    }

    public void setQuestionnaireSurvey(TQuestionnaireSurvey questionnaireSurvey) {
        this.questionnaireSurvey = questionnaireSurvey;
    }

    public List<TopicFormBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicFormBean> topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "SurveyFormBean{" +
                "questionnaireSurvey=" + questionnaireSurvey +
                ", topic=" + topic +
                '}';
    }
}

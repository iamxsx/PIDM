package com.oneclouder.pidm.survey.model;

public class TTopic {
    private Integer id;

    private Integer surveyId;

    private String topicContent;

    private Integer nature;

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent == null ? null : topicContent.trim();
    }

    @Override
    public String toString() {
        return "TTopic{" +
                "id=" + id +
                ", surveyId=" + surveyId +
                ", topicContent='" + topicContent + '\'' +
                ", nature=" + nature +
                '}';
    }
}
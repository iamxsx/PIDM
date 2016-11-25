package com.oneclouder.pidm.survey.model;

public class TQuestionnaireSurvey {
    private Integer id;

    private String title;

    private Integer titleNum;

    private Integer joinNum;

    private Integer status;

    private String startTime;

    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getTitleNum() {
        return titleNum;
    }

    public void setTitleNum(Integer titleNum) {
        this.titleNum = titleNum;
    }

    @Override
    public String toString() {
        return "TQuestionnaireSurvey{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titleNum=" + titleNum +
                ", joinNum=" + joinNum +
                '}';
    }
}
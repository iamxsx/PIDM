package com.oneclouder.pidm.survey.webBean;

import com.oneclouder.pidm.survey.model.TOption;

import java.util.List;

/**
 * Created by clouder on 10/7/16.
 */
public class TopicFormBean {

    private Integer tid;

    private String topicContent;

    private Integer nature;

    private List<TOption> option;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }


    public List<TOption> getOption() {
        return option;
    }

    public void setOption(List<TOption> option) {
        this.option = option;
    }


    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    @Override
    public String toString() {
        return "TopicFormBean{" +
                "tid=" + tid +
                ", topicContent='" + topicContent + '\'' +
                ", nature=" + nature +
                ", option=" + option +
                '}';
    }
}

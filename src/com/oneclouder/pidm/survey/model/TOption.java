package com.oneclouder.pidm.survey.model;

public class TOption {
    private Integer id;

    private Integer topicId;

    private String optionContent;

    private Integer count;

    private Integer sId;

    private String nature;

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent == null ? null : optionContent.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    @Override
    public String toString() {
        return "TOption{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", optionContent='" + optionContent + '\'' +
                ", count=" + count +
                ", sId=" + sId +
                ", nature='" + nature + '\'' +
                '}';
    }
}
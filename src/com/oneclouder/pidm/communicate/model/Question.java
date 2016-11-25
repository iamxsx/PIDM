package com.oneclouder.pidm.communicate.model;

import java.util.Date;

/**
 * Created by zheng.
 */
public class Question {
    private Integer id;

    private Integer communicateId;

    private String time;

    private String content;

    private Communicate communicate;

    public Communicate getCommunicate() {
        return communicate;
    }

    public void setCommunicate(Communicate communicate) {
        this.communicate = communicate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunicateId() {
        return communicateId;
    }

    public void setCommunicateId(Integer communicateId) {
        this.communicateId = communicateId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}

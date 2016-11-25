package com.oneclouder.pidm.article.model;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.user.model.User;

/**
 * Created by clouder on 16-9-11.
 */
public class Article {

    private Integer id;

    private String header;

    private Menu location;

    private Employee author;

    private String publishTime;

    private Integer isPicNews;

    private String poster;

    private Integer status;

    private Integer type;

    private String content;

    private String posterDesc;

    private String approver;

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getPosterDesc() {
        return posterDesc;
    }

    public void setPosterDesc(String posterDesc) {
        this.posterDesc = posterDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public Menu getLocation() {
        return location;
    }

    public void setLocation(Menu location) {
        this.location = location;
    }

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsPicNews() {
        return isPicNews;
    }

    public void setIsPicNews(Integer isPicNews) {
        this.isPicNews = isPicNews;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster == null ? null : poster.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

}

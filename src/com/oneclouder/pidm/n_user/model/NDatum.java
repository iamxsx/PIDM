package com.oneclouder.pidm.n_user.model;

/**
 * Created By IntelliJ IDEA
 * 注册用户上传资源bean
 * @Author: AngryFeng
 * @Date: 16-10-16
 * @Time: 下午11:49
 */
public class NDatum {
    private Integer id;
    private Integer userId;
    private String fileUrl;
    private String descr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getDescr() {
        return descr;
    }

    public void setDesc(String descr) {
        this.descr = descr;
    }
}

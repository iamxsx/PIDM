package com.oneclouder.model;

public class TAnnex {
    private Integer id;

    private Integer aid;

    private String filesurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getFilesurl() {
        return filesurl;
    }

    public void setFilesurl(String filesurl) {
        this.filesurl = filesurl == null ? null : filesurl.trim();
    }
}
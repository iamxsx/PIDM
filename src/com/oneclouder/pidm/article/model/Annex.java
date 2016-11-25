package com.oneclouder.pidm.article.model;

/**
 * 附件
 */
public class Annex {
    private Integer id;

    private Article article;

    private String filesurl;

    private String annexDesc;

    public String getAnnexDesc() {
        return annexDesc;
    }

    public void setAnnexDesc(String annexDesc) {
        this.annexDesc = annexDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getFilesurl() {
        return filesurl;
    }

    public void setFilesurl(String filesurl) {
        this.filesurl = filesurl == null ? null : filesurl.trim();
    }


}
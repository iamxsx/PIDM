package com.oneclouder.pidm.article.service;

import com.oneclouder.pidm.article.model.Article;

import java.io.IOException;
import java.util.Map;

/**
 * Created by clouder on 16-9-14.
 */
public interface IArticleSearchService {


    /**
     * 根据关键词查询文章
     * @param which 根据什么查询
     * @param keywords 查询的关键字
     * @param limit 查询的条数
     */
    public Map<String,Object> searchArticle(String which, String keywords, int limit);

    /**
     * 更新文章索引
     * @param article
     */
    public void updateArticleIndex(Article article);

    /**
     * 删除文章索引
     * @param aid
     */
    public void deleteArticleIndex(Integer aid);

    /**
     * 增加文章索引
     * @param article
     */
    public void addArticleIndex(Article article);

}

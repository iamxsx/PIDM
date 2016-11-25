package com.oneclouder.pidm.article.service;

import com.oneclouder.pidm.article.model.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-11.
 */
public interface IArticleService {

    public void saveArticle(Article article);

    public Article findArticleById(Integer id);

    public List<Article> findArticlesByPage(Map<String,Object> params);

    public int getTotalArticleCount();

    public void setStatusByArticleId(Map<String,Object> params);

    public List<Article> findPublishedArticleByPage(Map<String,Object> params);

    public void setApprover(Map<String,Object> params);


    public List<Article> findArticleByCondition(Map<String,Object> params);

    public void deleteArticles(List<String> ids);


    public List<Article> findArticlesBySection(Map<String,Object> params);

    public void deletePoster(Integer aid);

    public List<Article> findArticlesByStatus(Map<String, Object> params);


    public void updateArticle(Article article);


    public int getCountByStatus(Map<String, Object> params);

    public List<Article> findArticlesByIds(List<String> ids);

    public List<Article> findArticleByMenuId(Map<String, Object> params);

    public Article findAssociationArticle(String menu, String menuId);

    public int getArticleCountByMenuId(Map<String, Object> params);

    public List<Article> getPhototNews();

    List<Article> findArticlesBySecondSection(String sectionName);

    public int findPublishedArticleCount(Map<String, Object> params);

    public Article findPublishedArticleById(Integer aid);

    List<Article> getCarouselNews();

    List<Article> selectAllPublishedArticle();

    List<Article> findMumbersService(Map<String, Object> params);
}

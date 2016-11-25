package com.oneclouder.pidm.article.dao;

import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-11.
 */
@MyBatisRepository
@Repository("articleDao")
@Lazy
public interface IArticleDao {

    /**
     * 保存新文章
     * @param article
     */
    public void saveArticle(Article article);

    /**
     * 根据ｉｄ查找文章
     * @param aid
     * @return
     */
    public Article findArticleById(Integer aid);

    public Article findPublishedArticleById(Integer aid);

    /**
     * 分页查询文章
     * @param params
     * @return
     */
    public List<Article> findArticlesByPage(Map<String,Object> params);

    /**
     * 获得文章总数
     * @return
     */
    public int getTotalArticleCount();

    /**
     * 更改文章发布状态
     * @param params
     */
    public void setStatusByArticleId(Map<String,Object> params);

    /**
     * 查找已发布的文章
     * @return
     */
    public List<Article> findPublishedArticleByPage(Map<String,Object> params);

    /**
     * 设置审核人
     * @param params
     */
    public void setApprover(Map<String,Object> params);



    /**
     * 根据条件查询文章
     * @param params
     * @return
     */
    public List<Article> findArticleByCondition(Map<String,Object> params);

    /**
     * 根据ｉｄ删除所选文章
     * @param ids
     */
    public void deleteArticles(List<String> ids);


    /**
     * 查询某版块的文章
     * @return
     */
    public List<Article> findArticlesBySection(Map<String,Object> params);


    /**
     * 将图片置空
     * @param aid
     */
    public void deletePoster(Integer aid);


    /**
     * 更新文章
     * @param article
     */
    public void updateArticle(Article article);


    /**
     * 根据文章状态查询数量
     * @param status
     * @return
     */
    public int getCountByStatus(Map<String, Object> params);

    /**
     * 根据状态查找文章
     * @param params
     * @return
     */
    public List<Article> findArticlesByStatus(Map<String, Object> params);

    public List<Article> findArticlesByIds(List<String> ids);

    /**
     * 查找该菜单下的所有子菜单下的文章
     * @param params
     * @return
     */
    public List<Article> findArticleByMenuId(Map<String, Object> params);

    /**
     * 直接查找某个菜单下的文章
     * @param params
     * @return
     */
    public List<Article> findArticleByMenuIdDirectly(Map<String, Object> params);

    public Article findAssociationArticle(Map<String, String> params);

    public int getArticleCountByMenuName(Map<String, Object> params);

    public List<Article> getPhototNews();

    public List<Article> findArticlesBySecondSection(String sectionName);

    public int findPublishedArticleCount(Map<String, Object> params);

    List<Article> getCarouselNews();

    List<Article> selectAllPublishedArticle();

    List<Article> findMumbersService(Map<String, Object> params);
}

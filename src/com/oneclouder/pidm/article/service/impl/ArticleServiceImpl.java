package com.oneclouder.pidm.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.oneclouder.pidm.article.dao.IArticleDao;
import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IArticleService;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.redis.service.IRedisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-11.
 */
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    @Resource(name = "articleDao")
    private IArticleDao articleDao;

    @Resource
    private IMenuService menuService;

    @Resource
    private IRedisService redisService;


    @Override
    public void saveArticle(Article article) {
        articleDao.saveArticle(article);
    }

    private static final String KEY_ARTICLE_ID_ = "KEY_ARTICLE_ID_";

    @Override
    public Article findArticleById(Integer id) {
        String result = redisService.get(KEY_ARTICLE_ID_ + id);
        if (!StringUtils.isEmpty(result)){
            Article article = JSON.parseObject(result,Article.class);
            return article;
        }else {
            Article article = articleDao.findArticleById(id);
            redisService.set(KEY_ARTICLE_ID_ + id, JSON.toJSONString(article));
            return article;
        }
    }

    @Override
    public List<Article> findArticlesByPage(Map<String, Object> params) {
        return articleDao.findArticlesByPage(params);
    }

    @Override
    public int getTotalArticleCount() {
        return articleDao.getTotalArticleCount();
    }

    @Override
    public void setStatusByArticleId(Map<String, Object> params) {
        articleDao.setStatusByArticleId(params);
    }

    @Override
    public List<Article> findPublishedArticleByPage(Map<String, Object> params) {
        return articleDao.findPublishedArticleByPage(params);
    }

    @Override
    public void setApprover(Map<String, Object> params) {
        articleDao.setApprover(params);
    }


    @Override
    public List<Article> findArticleByCondition(Map<String, Object> params) {
        return articleDao.findArticleByCondition(params);
    }

    @Override
    public void deleteArticles(List<String> ids) {
        articleDao.deleteArticles(ids);
        for (String id : ids){
            redisService.delete(KEY_ARTICLE_ID_ + id);
        }
    }

    @Override
    public List<Article> findArticlesBySection(Map<String, Object> params) {
        return articleDao.findArticlesBySection(params);
    }

    @Override
    public void deletePoster(Integer aid) {
        articleDao.deletePoster(aid);
    }

    @Override
    public List<Article> findArticlesByStatus(Map<String, Object> params) {
        return articleDao.findArticlesByStatus(params);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
        redisService.delete(KEY_ARTICLE_ID_ + article.getId());
    }

    @Override
    public int getCountByStatus(Map<String, Object> params) {
        return articleDao.getCountByStatus(params);
    }

    @Override
    public List<Article> findArticlesByIds(List<String> ids) {
        return articleDao.findArticlesByIds(ids);
    }

    @Override
    public List<Article> findArticleByMenuId(Map<String, Object> params) {
        //查出该菜单下的所有子菜单
        Integer menuId = (Integer) params.get("menuId");
        List<Integer> ids = menuService.getSonMenuIds(menuId);
        //如果没有子菜单则说明该菜单已经处于最底层，则查出该菜单下的文章
        params.put("ids", ids);
        return articleDao.findArticleByMenuId(params);
    }

    @Override
    public Article findAssociationArticle(String menu, String menuId) {
        Map<String,String> params = new HashMap<>();
        params.put("menuName",menu);
        params.put("menuId",menuId);
        return articleDao.findAssociationArticle(params);
    }

    @Override
    public int getArticleCountByMenuId(Map<String, Object> params) {
        Integer menuId = (Integer) params.get("menuId");
        List<Integer> ids = menuService.getSonMenuIds(menuId);
        params.put("ids", ids);
        return articleDao.getArticleCountByMenuName(params);
    }

    @Override
    public List<Article> getPhototNews() {
        return articleDao.getPhototNews();
    }

    @Override
    public List<Article> findArticlesBySecondSection(String sectionName) {
        return articleDao.findArticlesBySecondSection(sectionName);
    }

    @Override
    public int findPublishedArticleCount(Map<String, Object> params) {
        return articleDao.findPublishedArticleCount(params);
    }

    @Override
    public Article findPublishedArticleById(Integer aid) {
        return articleDao.findPublishedArticleById(aid);
    }

    @Override
    public List<Article> getCarouselNews() {
        return articleDao.getCarouselNews();
    }
    @Override
    public List<Article> selectAllPublishedArticle(){
        return articleDao.selectAllPublishedArticle();
    }

    /**
     * 获取会员服务新闻
     * @return
     * @param params
     */
    @Override
    public List<Article> findMumbersService(Map<String, Object> params) {
        Integer menuId = (Integer) params.get("menuId");
        List<Integer> ids = menuService.getSonMenuIds(menuId);
        return articleDao.findMumbersService(params);
    }

}

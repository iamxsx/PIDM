package com.oneclouder.pidm.article.controller;

import com.oneclouder.pidm.article.model.Annex;
import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IAnnexService;
import com.oneclouder.pidm.article.service.IArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-10-26.
 */
@Controller
@RequestMapping("article")
public class FrontArticleAction {

    @Resource(name = "articleService")
    private IArticleService articleService;

    @Resource(name = "annexService")
    private IAnnexService annexService;

    /**
     * 查询某个版块的文章
     *
     * @param sectionName
     * @return
     */
    @RequestMapping(value = "findArticleBySection", method = RequestMethod.POST)
    @ResponseBody
    public List<Article> findArticleBySection(String sectionName, @RequestParam(defaultValue = "1") Integer offset,@RequestParam(defaultValue = "10") Integer limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("sectionName", sectionName);
        params.put("offset", offset);
        params.put("limit", limit);
        List<Article> articles = null;
        //todo 这里一定会改
        if (("会员服务").equals(sectionName)) {
            params.put("menuId", 7);
            articles = articleService.findArticleByMenuId(params);
        } else {
            articles = articleService.findArticlesBySection(params);
        }

        return articles;
    }

    @RequestMapping("findArticlesBySecondSection")
    @ResponseBody
    public List<Article> findArticlesBySecondSection(String sectionName) {
        return articleService.findArticlesBySecondSection(sectionName);
    }

    /**
     * 显示文章详情
     *
     * @param modelAndView
     * @param aid
     * @return
     */
    @RequestMapping("detail/{aid}/{menuName}")
    public ModelAndView showArticleDetail(ModelAndView modelAndView, @PathVariable Integer aid, @PathVariable("menuName") String menuName) {
        Article article = articleService.findArticleById(aid);
        modelAndView.setViewName("foreground/article/article-detail");
        modelAndView.addObject("article", article);
        Annex annex = annexService.findAnnexByArticleId(article.getId());
        if (annex != null) {
            modelAndView.addObject("annex", annex);
        }
        return modelAndView;
    }

    /**
     * 获得轮播新闻
     */
    @RequestMapping("getCarouselNews")
    @ResponseBody
    public List<Article> getCarouselNews() {
        List<Article> articles = articleService.getCarouselNews();
        return articles;
    }

    @RequestMapping("getPhototNews")
    @ResponseBody
    public List<Article> getPhototNews() {
        List<Article> articles = articleService.getPhototNews();
        return articles;
    }

}

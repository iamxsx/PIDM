package com.oneclouder.pidm.index.controller;

import com.oneclouder.pidm.article.model.Annex;
import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IAnnexService;
import com.oneclouder.pidm.article.service.IArticleService;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.survey.service.IQuestionnaireSurveyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-8.
 */
@Controller
@RequestMapping("/")
public class IndexAction {

    @Resource(name = "articleService")
    private IArticleService articleService;

    @Resource
    private IAnnexService annexService;

    @Resource
    private IMenuService menuService;

    @Resource(name = "questionnaireSurveyService")
    private IQuestionnaireSurveyService questionnaireSurveyService;

    @RequestMapping("index")
    public String index(Model model){
        List<Article> articles = articleService.getCarouselNews();
        model.addAttribute("carouselNews",articles);
        return "/foreground/index";
    }


    /**
     * 协会概况模块
     * @return
     */
    @RequestMapping("section/to-association-section")
    public ModelAndView toAssociationSection(ModelAndView modelAndView,String menu,String menuId){
        if (menu == null || menu .equals("")){
            menu = "协会简介";
        }
        Article article = articleService.findAssociationArticle(menu,menuId);
        if (article != null){
            Annex annex = annexService.findAnnexByArticleId(article.getId());
            modelAndView.addObject("annex",annex);
        }
        modelAndView.addObject("article",article);
        modelAndView.addObject("menuName",menu);
        modelAndView.setViewName("foreground/section/association");
        return modelAndView;
    }

    /**
     * 协会新闻模块
     * @return
     */
    @RequestMapping("section/to-news-section")
    public String toNewsSection(Model model, String menu,Integer menuId){
        model.addAttribute("menuId",menuId);
        model.addAttribute("menuName",menu);
        return "foreground/section/news";
    }

    /**
     * 根据 menuId 查询文章（会查询出该菜单下的所有文章）
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping("findArticleByMenuName")
    @ResponseBody
    public List<Article> findArticleByMenuName(Integer menuId,Integer offset,Integer limit){
        Map<String,Object> params = new HashMap<>();
        params.put("menuId",menuId);
        params.put("offset",(offset-1)*limit);
        params.put("limit",limit);
        List<Article> articles = articleService.findArticleByMenuId(params);
        return articles;
    }

    /*@RequestMapping("findArticleByMenuId")
    @ResponseBody
    public List<Article> findArticleByMenuId(Integer menuId,Integer offset,Integer limit){
        Map<String,Object> params = new HashMap<>();
        params.put("menuId",menuId);
        params.put("offset",(offset-1)*limit);
        params.put("limit",limit);
        List<Article> articles = articleService.findArticleByMenuId(params);
        return articles;
    }*/

    @RequestMapping("getArticleCountByMenuName")
    @ResponseBody
    public int getArticleCountByMenuName(Integer menuId){
        Map<String,Object> params = new HashMap<>();
        params.put("menuId",menuId);
        int count = articleService.getArticleCountByMenuId(params);
        return count;
    }




    /**
     * 通知公告
     * @return
     */
    @RequestMapping("section/to-notices-section")
    public String toNoticesSection(Model model,String menu,Integer menuId){
        model.addAttribute("menuName",menu);
        model.addAttribute("menuId",menuId);
        return "foreground/section/important-notices";
    }

    /**
     * 理论研究
     * @return
     */
    @RequestMapping("section/to-search-section")
    public String toSearchSection(Model model,String menu,Integer menuId){
        model.addAttribute("menuName",menu);
        model.addAttribute("menuId",menuId);
        return "foreground/section/search";
    }

    /**
     * 专家智库
     * @return
     */
    @RequestMapping("section/to-expert-section")
    public String toExpertSection(Model model,String menu,Integer menuId){
        model.addAttribute("menuName",menu);
        model.addAttribute("menuId",menuId);
        return "foreground/section/expert";
    }

    /**
     * 法律规章
     * @return
     */
    @RequestMapping("section/to-law-section")
    public String toLawSection(Model model,String menu,Integer menuId){
        model.addAttribute("menuName",menu);
        model.addAttribute("menuId",menuId);
        return "foreground/section/law";
    }

    /**
     * 行业动态
     * @return
     */
    @RequestMapping("section/to-dynamic-section")
    public String toDynamicSection(Model model,String menu,Integer menuId){
        model.addAttribute("menuName",menu);
        model.addAttribute("menuId",menuId);
        return "foreground/section/dynamic";
    }

    /**
     * 价格数据
     * @return
     */
    @RequestMapping("section/to-price-section")
    public String toPriceSection(Model model,String menu,Integer menuId){
        model.addAttribute("menuName",menu);
        model.addAttribute("menuId",menuId);
        return "foreground/section/price-data";
    }

    /**
     * 会员服务
     * @return
     */
    @RequestMapping("section/to-service-section")
    public String toServiceSection(Model model,String menu,Integer menuId){
        model.addAttribute("menuName",menu);
        model.addAttribute("menuId",menuId);
        return "foreground/section/service-function";
    }

    @RequestMapping("apply/table")
    public String toRegistertable(){
        return "foreground/register-table";
    }
    /**
     * 下载中心
     * @return
     */
    @RequestMapping("section/to-download-section")
    public String toDownloadSection(){
        return "foreground/section/download-center";
    }

    /**
     * 交流原地
     * @return
     */
    @RequestMapping("section/to-consult-section")
    public String toConsultSection(Model model,String menu){
        if(menu == null || menu.equals("交流园地") ||menu.equals("经验交流")){
            model.addAttribute("menuName","经验交流");
            return "foreground/section/consult";
        }else {
            model.addAttribute("menuName","建议意见");
            return "foreground/section/suggestion";
        }
    }

    /**
     * 意见调查表内容
     * 跳转到前台意见调查表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "section/survey/SurveyInfo",method = RequestMethod.GET)
    public ModelAndView SurveyInfo(ModelAndView model, Integer succSurvey, HttpSession session){
        NUserInfo u = (NUserInfo) session.getAttribute("userInfo");
        if (u != null){
            Integer status = questionnaireSurveyService.findStatus();
            if (status != null&&status == 3){
                if (succSurvey != null && succSurvey == 0) {
                    model.addObject("msg", "填写成功");
                    model.setViewName("/foreground/n_user/msg");
                }else {
                    model.addObject("survey", questionnaireSurveyService.SurveyInfo());
                    //添加用户id
                    model.addObject("userId", u.getId());
                    model.setViewName("/back/survey/Survey");
                }
            }else{
                model.addObject("msg","后台维护中,请稍后操作!");
                model.setViewName("/foreground/n_user/msg");
            }
        }else{
            //用户未登录
            model.addObject("msg","请先登录!");
            model.setViewName("/foreground/n_user/msg");
        }
        return model;
    }



}

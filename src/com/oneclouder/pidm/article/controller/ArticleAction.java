package com.oneclouder.pidm.article.controller;

import com.oneclouder.pidm.article.model.Annex;
import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IAnnexService;
import com.oneclouder.pidm.article.service.IArticleSearchService;
import com.oneclouder.pidm.article.service.IArticleService;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.menu.service.IMenuService;
import com.oneclouder.pidm.redis.service.IRedisService;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.util.DateUtil;
import com.oneclouder.pidm.util.FtpUtil;
import com.oneclouder.pidm.util.PropertiesUtil;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import com.sun.deploy.net.HttpResponse;
import org.activiti.engine.impl.util.json.HTTP;
import org.activiti.engine.repository.Model;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by clouder on 11/09/16.
 */
@Controller
@RequestMapping("back/article")
public class ArticleAction {

    @Resource(name = "menuService")
    private IMenuService menuService;

    @Resource(name = "articleService")
    private IArticleService articleService;

    @Resource(name = "annexService")
    private IAnnexService annexService;

    @Resource(name = "searchService")
    private IArticleSearchService searchService;

    @Resource(name = "workFlowService")
    private IWorkFlowService workFlowService;


    @RequestMapping("to-publish")
    public String toPublish() {
        return "back/article/publish-article";
    }


    /**
     * 跳转到文章管理首页
     *
     * @return
     */
    @RequestMapping("")
    public String article() {
        return "back/article/article-list";
    }

    /**
     * 查询一级版块
     *
     * @return
     */
    @RequestMapping("queryLevel1Menu")
    @ResponseBody
    public List<Menu> queryLevel1Menu() {
        return menuService.queryLevel1Menu();
    }

    /**
     * 查询二，三级版块
     *
     * @param parentId
     * @return
     */
    @RequestMapping("querySecondMenu")
    @ResponseBody
    public List<Menu> querySecondMenu(Integer parentId) {
        return menuService.querySecondMenu(parentId);
    }


    /**
     * 分页查询文章
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping("findArticlesByPage")
    @ResponseBody
    public Map<String, Object> findArticlesByPage(int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("start", offset);
        List<Article> rows = articleService.findArticlesByPage(params);
        int total = articleService.getTotalArticleCount();
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    /**
     * 列出文章
     *
     * @return
     */
    @RequestMapping("article-list")
    public String listArticles() {
        return "back/article/article-list";
    }

    /**
     * 文章预览
     *
     * @param model
     * @param aid
     * @return
     */
    @RequestMapping("article-preview")
    public ModelAndView preview(ModelAndView model, Integer aid) {
        Article article = articleService.findArticleById(aid);
        model.addObject("article", article);
        Annex annex = annexService.findAnnexByArticleId(article.getId());
        if (annex != null) {
            model.addObject("annex", annex);
        }
        model.setViewName("back/article/article-preview");
        return model;
    }

    /**
     * 显示图片
     *
     * @param response
     * @param path     　图片路径
     * @throws IOException
     */
    @RequestMapping("showImage")
    public void showImage(HttpServletResponse response, String path) throws IOException {
        FtpUtil.showImages(response, path);
    }

    @RequestMapping("article-published-list")
    public String toPublishedList() {
        return "back/article/article-published-list";
    }

    @RequestMapping("findPublishedArticlesByPage")
    @ResponseBody
    public Map<String, Object> findPublishedArticlesByPage(int offset, int limit, String column, String text) {
        Map<String, Object> params = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        params.put("author", employee.getId());
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("column", column);
        params.put("text", text);
        List<Article> rows = articleService.findPublishedArticleByPage(params);
        int total = articleService.findPublishedArticleCount(params);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ResponseBody
    @RequestMapping("findArticleByCondition")
    public List<Article> findArticleByCondition(String column, String text) {
        Map<String, Object> map = new HashMap<>();
        map.put("column", column);
        map.put("text", text);
        return articleService.findArticleByCondition(map);
    }

    @RequestMapping("deleteArticles")
    @ResponseBody
    public void deleteArticles(String ids) {
        String[] _ids = ids.split(",");
        articleService.deleteArticles(Arrays.asList(_ids));
    }

    /**
     * 跳转到编辑页面
     *
     * @param model
     * @param aid
     * @return
     */
    @RequestMapping("edit")
    public ModelAndView edit(ModelAndView model, Integer aid) {
        Article article = articleService.findArticleById(aid);
        model.addObject("article", article);
        Annex annex = annexService.findAnnexByArticleId(aid);
        if (annex != null) {
            model.addObject(annex);
        }
        model.setViewName("back/article/article-edit");
        return model;
    }

    /**
     * 查询未发布的文章
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping("findNotpublishedArticle")
    @ResponseBody
    public Map<String, Object> findNotpublishedArticle(int offset, int limit) {
        return findArticles(limit, offset, 0);
    }

    /**
     * 跳转页面
     *
     * @return
     */
    @RequestMapping("article-not-published-list")
    public String toNotPublishedArticle() {
        return "back/article/article-not-published";
    }

    @RequestMapping("article-verify-list")
    public String toVerifyArticle() {
        return "back/article/article-verify-list";
    }

    @RequestMapping("findVerifiedArticle")
    @ResponseBody
    public Map<String, Object> findVerifiedArticle(int limit, int offset) {
        return findArticles(limit, offset, 1);
    }

    /**
     * 根据状态查询文章
     *
     * @param limit
     * @param offset
     * @param status
     * @return
     */
    private Map<String, Object> findArticles(int limit, int offset, int status) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("status", status);
        params.put("author", employee.getId());
        List<Article> rows = articleService.findArticlesByStatus(params);
        int total = articleService.getCountByStatus(params);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @RequestMapping("article-reject-list")

    public String toRejectedArticle() {
        return "back/article/article-rejected-list";
    }

    @RequestMapping("findRejectedArticle")
    @ResponseBody
    public Map<String, Object> findRejectedArticle(int limit, int offset) {
        Map<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = employee.getRealName();
        List<Map<String, Object>> list = workFlowService.personal(username, offset, limit);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (Map<String, Object> map : list) {
            String taskId = (String) map.get("taskId");
            String entityId = (String) map.get("entityId");
            sb.append(entityId + ",");
            sb2.append(taskId + ",");
        }
        List<String> taskIds = Arrays.asList(sb2.toString().split(","));
        List<String> ids = Arrays.asList(sb.toString().split(","));

        List<Article> articles = articleService.findArticlesByIds(ids);
        for (int i = 0; i < articles.size(); i++) {
            articles.get(i).setTaskId(taskIds.get(i));
        }
        result.put("total", workFlowService.findTaskList(username).size());
        result.put("rows", articles);
        return result;
    }

    /**
     * 保存文章
     *
     * @param request
     * @param response
     * @param session
     * @param header     　标题
     * @param location   　发布位置
     * @param location2  　具体发布在哪个版块
     * @param location3  　具体发布在哪个版块
     * @param isTop      　是否置顶
     * @param content    　文章内容
     * @param posterDesc 　图片描述
     * @param annexDesc  　附件描述
     * @throws Exception
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveArticle(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                              String header, String location, Integer location2, Integer location3, Integer location4, Integer isTop, String content,
                              String posterDesc, String annexDesc
    ) throws Exception {
        int status = 0;
        saveOrUpdateArticle(request, response, header, location, location2, location3, location4, isTop, content, posterDesc, annexDesc, status);
        return "back/article/save-success";
    }

    /**
     * 新发布文章，只是存入数据库，未发布
     *
     * @param request
     * @param response
     * @param header
     * @param location
     * @param location2
     * @param location3
     * @param isPicNews
     * @param content
     * @param posterDesc
     * @param annexDesc
     * @throws Exception
     */
    private Article saveOrUpdateArticle(HttpServletRequest request, HttpServletResponse response, String header, String location, Integer location2, Integer location3, Integer location4, Integer isPicNews, String content, String posterDesc, String annexDesc, int status) throws Exception {
        //发布板块的id
        Integer where = null;
        if (location4 != null) {
            where = location4;
        }else if (location3 == null) {
            where = location2;
        } else {
            where = location3;
        }
        Article article = new Article();
        //绑定菜单项
        Menu menu = new Menu();
        menu.setId(where);
        article.setLocation(menu);

        //将发布位置转化为文件存储位置
        location = locate(location);
        //处理海报图和附件
        Map<String, String> map = uploadFile(request, response, location);
        //绑定海报url
        String poster = map.get("poster");
        if (!StringUtils.isEmpty(poster)) {
            article.setPoster(poster);
        }
        //绑定用户
        //Employee employee = (Employee) session.getAttribute("currentEmployee");
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        article.setAuthor(employee);
        //设置标题
        article.setHeader(header);
        //是否置顶
        article.setIsPicNews(isPicNews);
        //发布状态
        article.setStatus(status);
        //发布内容
        article.setContent(content);
        //发布时间
        article.setPublishTime(DateUtil.getCurrentDate());
        //图片描述
        article.setPosterDesc(posterDesc);
        articleService.saveArticle(article);
        //绑定附件
        String annexUrl = map.get("annex");
        if (!StringUtils.isEmpty(annexUrl)) {
            Annex annex = new Annex();

            annex.setArticle(article);
            //设置附件路径
            annex.setFilesurl(annexUrl);
            //设置附件描述
            annex.setAnnexDesc(annexDesc);
            //保存附件
            annexService.saveAnnex(annex);
        }
        //更新索引
        searchService.addArticleIndex(article);
        return article;
    }

    /**
     * 发布文章
     *
     * @param request
     * @param response
     * @param session
     * @param key        　根据ｋｅｙ值判断是新文章还是已保存过的文章
     * @param header
     * @param location
     * @param location2
     * @param location3
     * @param isPicNews
     * @param content
     * @param posterDesc
     * @param annexDesc
     * @return
     */
    @RequestMapping(value = "publish",method = RequestMethod.POST)
    public String publishArticle(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session,
                                 String key, String header,
                                 String location, Integer location2,
                                 Integer location3, Integer location4, Integer isPicNews,
                                 String content, String posterDesc,
                                 String annexDesc, Integer aid, String taskId
    ) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = employee.getRealName();
        Article article = null;
        //新发布
        if (key.equals("new")) {
            article = saveOrUpdateArticle(request, response, header, location, location2, location3, location4, isPicNews, content, posterDesc, annexDesc, 1);
            // Todo: 启动工作流
            workFlowService.start(article.getId(), username, "Article", "Insert");
        }
        //保存草稿
        else if (key.equals("save")) {
            article = saveOrUpdateArticle(request, response, header, location, location2, location3, location4, isPicNews, content, posterDesc, annexDesc, 0);
            return "back/article/save-success";
        }
        //提交草稿
        else if (key.equals("update")) {
            article = updateArticle(request, response, header, location, location2, location3, location4, isPicNews, content, posterDesc, annexDesc, aid);
            // Todo: 启动工作流
            workFlowService.start(article.getId(), username, "Article", "Insert");
        }
        //提交被驳回
        else if (key.equals("reapply")) {
            updateArticle(request, response, header, location, location2, location3, location4, isPicNews, content, posterDesc, annexDesc, aid);
            // Todo: 启动工作流
            workFlowService.reapply(taskId, username);
        }

        return "back/article/publish-success";
    }

    /**
     * 更新已存在文章
     */
    private Article updateArticle(HttpServletRequest request, HttpServletResponse response, String header,
                                  String location, Integer location2, Integer location3, Integer location4, Integer isPicNews, String content, String posterDesc, String annexDesc, Integer aid) throws Exception {
        //发布板块的id
        Integer where = null;
        if (location4 != null) {
            where = location4;
        }else if (location3 == null) {
            where = location2;
        } else {
            where = location3;
        }
//        if (location4 != null) {
//            where = location4;
//        }
//        where = location3 == null ? location2 : location3;
        Article article = articleService.findArticleById(aid);
        //绑定菜单项
        Menu menu = new Menu();
        menu.setId(where);
        article.setLocation(menu);
        location = locate(location);
        //处理海报图和附件
        Map<String, String> map = uploadFile(request, response, location);
        //绑定海报url
        String poster = map.get("poster");
        if (!StringUtils.isEmpty(poster)) {
            //有重新上传海报图，先去删除之前的图片
            if (article.getPoster() != null) {
                if (FtpUtil.deleteFile(article.getPoster())) {
                    System.out.println("删除成功");
                }
            }
            article.setPoster(poster);
        }
        //绑定附件
        String annexUrl = map.get("annex");
        if (!StringUtils.isEmpty(annexUrl)) {
            Annex annex = annexService.findAnnexByArticleId(aid);
            //有重新上传附件，删除之前的附件
            if (annex != null) {
                annexService.deleteAnnex(annex.getId());
            }
            Annex newAnnex = new Annex();
            //设置附件路径
            newAnnex.setFilesurl(annexUrl);
            //设置附件描述
            newAnnex.setAnnexDesc(annexDesc);
            //保存附件
            annexService.saveAnnex(annex);
            return article;
        }
        //设置标题
        article.setHeader(header);
        //是否置顶
        article.setIsPicNews(isPicNews);
        //发布内容
        article.setContent(content);
        //发布时间
        article.setPublishTime(DateUtil.getCurrentDate());
        //图片描述
        article.setPosterDesc(posterDesc);
        articleService.updateArticle(article);
        //更新索引
        searchService.addArticleIndex(article);
        return article;
    }

    /*
    * 处理被驳回的文章
    * */
    @RequestMapping("parseArticle")
    public ModelAndView parseArticle(ModelAndView modelAndView, Integer aid, String taskId) {
        Article article = articleService.findArticleById(aid);
        List<Map<String, Object>> comments = workFlowService.findHistoricComment(taskId);
        article.setTaskId(taskId);
        for (Map<String, Object> comment : comments) {
            comment.put("startTime", DateUtil.formate((Date) comment.get("startTime")));
            comment.put("endTime", DateUtil.formate((Date) comment.get("endTime")));
        }
        modelAndView.addObject("article", article);
        modelAndView.addObject("comments", comments);
        Annex annex = annexService.findAnnexByArticleId(article.getId());
        if (annex != null) {
            modelAndView.addObject("annex", annex);
        }
        modelAndView.setViewName("back/article/article-rejected");
        return modelAndView;
    }


    /**
     * 删除海报图
     *
     * @param aid
     * @param response
     * @throws IOException
     */
    @RequestMapping("deletePoster")
    @ResponseBody
    public void deletePoster(int aid, HttpServletResponse response) throws IOException {
        Article article = articleService.findArticleById(aid);
        String poster = article.getPoster();
        if (!StringUtils.isEmpty(poster)) {
            if (FtpUtil.deleteFile(poster)) {
                articleService.deletePoster(aid);
                response.getWriter().write("删除成功");
            } else {
                response.getWriter().write("删除失败");
            }
        }
    }

    /**
     * 删除附件
     *
     * @param aid
     * @param response
     * @throws IOException
     */
    @RequestMapping("deleteAnnex")
    @ResponseBody
    public void deleteAnnex(int aid, HttpServletResponse response) throws IOException {
        Annex annex = annexService.findAnnexByArticleId(aid);
        String filesurls = annex.getFilesurl();
        if (!StringUtils.isEmpty(filesurls)) {
            if (FtpUtil.deleteFile(filesurls)) {
                annexService.deleteAnnex(annex.getId());
                response.getWriter().write("删除成功");
            } else {
                response.getWriter().write("删除失败");
            }
        }
        annexService.deleteAnnex(aid);
    }

    @RequestMapping("download-annex")
    @ResponseBody
    public void downloadAnnex(HttpServletResponse response, String filePath) throws IOException {
        String fileName = filePath.substring(filePath.lastIndexOf("/"));
        FtpUtil.downloadFile(response, filePath, fileName);
    }


    /**
     * 文章搜索
     *
     * @param modelAndView
     * @param which
     * @param keywords
     * @param limit
     * @return
     */
    @RequestMapping("article-search")
    public ModelAndView toSearch(ModelAndView modelAndView, String which, String keywords, int limit) {
        Map<String, Object> result = searchService.searchArticle(which, keywords, limit);
        modelAndView.addObject("result", result);
        modelAndView.setViewName("foreground/article/article-search");
        return modelAndView;
    }

    /**
     * 搜索文章
     *
     * @param which    　根据什么搜索
     * @param keywords 　搜索的关键字
     * @param limit    　搜索条数
     * @return
     */
    @RequestMapping("searchArticle")
    @ResponseBody
    public Map<String, Object> searchArticle(String which, String keywords, int limit) {
        Map<String, Object> result = searchService.searchArticle(which, keywords, limit);
        return result;
    }


    /**
     * 上传文件
     *
     * @param request
     * @param response
     * @param location 文件的存放路径
     * @return
     * @throws Exception
     */
    public Map<String, String> uploadFile(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
        //key为上传文件的name属性，value为上传的路径
        Map<String, String> map = new HashMap<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                System.out.println(file.getName());
                if (file != null) {
                    String filename = file.getOriginalFilename();
                    if (filename.trim() != "") {
                        filename = generateFileName(filename);
                        FtpUtil.upload(file.getInputStream(), filename, location);
                        map.put(file.getName(), location + filename);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 根据发布位置决定文件的存放路径
     *
     * @param location
     * @return
     */
    public String locate(String location) {
        String locate = null;
        switch (location) {
            case "协会概况":
                locate = "ASSOCIATION_UPLOAD_PATH";
                break;
            case "协会新闻":
                locate = "NOTICE_UPLOAD_PATH";
                break;
            case "通知公告":
                locate = "LAWS_UPLOAD_PAHT";
                break;
            case "法律规章":
                locate = "LAWS_UPLOAD_PAHT";
                break;
            case "行业动态":
                locate = "NEWS_UPLOAD_PATH";
                break;
            case "价格数据":
                locate = "PRICE_UPLOAD_PATH";
                break;
            case "会员服务":
                locate = "MUMBER_UPLOAD_PATH";
                break;
            case "下载中心":
                locate = "CENTER_UPLOAD_PATH";
                break;
            default:
                locate = "UPLOAD_PATH";
        }
        return PropertiesUtil.getProperty(locate);
    }

    /**
     * 生成上传文件的文件名
     *
     * @param fileName
     * @return
     */
    public static String generateFileName(String fileName) {
        return DateUtil.getCurrentDateDesc() + fileName.substring(fileName.lastIndexOf("."));
    }


}

package com.oneclouder.pidm.article.test;

import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IArticleService;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.menu.model.Menu;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.util.DateUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ArticleTest {


    @Test
    public void testSaveArticle(){
        Article article = new Article();
        article.setContent("ninininin");
        article.setStatus(0);
        article.setIsPicNews(0);
        article.setPublishTime("2016-09-11");
        article.setHeader("xixix");
        Employee employee = new Employee();
        employee.setId(2);
        article.setAuthor(employee);
        Menu menu = new Menu();
        menu.setId(19);
        article.setLocation(menu);
        article.setPoster("upload/xxx");
        articleService.saveArticle(article);
    }

    @Test
    public void testFindArticlesByPage(){
        Map<String,Object> params = new HashMap<>();
        params.put("limit",10);
        params.put("start",0);
        List<Article> articles = articleService.findArticlesByPage(params);
        System.out.println();
    }

    @Test
    public void testGenerateFileName(){
        String fileName = "sdad.jpg";
        fileName = DateUtil.getCurrentDateDesc() + fileName.substring(fileName.lastIndexOf("."));
        System.out.println(fileName);
    }

    @Test
    public void testFindArticlesBySection(){
//        List<Article> articles = articleService.findArticlesBySection(//"协会动态");
//        for (Article article : articles){
//            System.out.println(article.getHeader());
//        }
    }

    @Resource(name = "luceneAnalyzer")
    private Analyzer analyzer;

    @Resource(name = "indexWriter")
    private IndexWriter indexWriter;

    @Resource(name = "indexSearcher")
    private IndexSearcher searcher;

    @Resource(name = "articleService")
    private IArticleService articleService;

    @Resource(name = "directoryReader")
    private DirectoryReader directoryReader;


    @Test
    public void addArticleIndex() throws IOException {
        List<Article> articles = articleService.selectAllPublishedArticle();
        System.out.println("haha");
        /*for (Article article:articles){
            Document document = new Document();
            document.add(new Field("content",article.getContent(), TextField.TYPE_STORED));
            document.add(new Field("header",article.getHeader(), TextField.TYPE_STORED));
            document.add(new Field("id",article.getId() + "", StringField.TYPE_STORED));
            indexWriter.addDocument(document);
        }
        indexWriter.commit();
*/
    }

}

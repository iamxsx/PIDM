package com.oneclouder.pidm.article.test;

import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IArticleService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by clouder on 16-9-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class LuceneTest {

    @Resource(name = "luceneAnalyzer")
    private Analyzer analyzer;

    @Resource(name = "indexWriter")
    private IndexWriter indexWriter;

    @Resource(name = "indexSearcher")
    private IndexSearcher searcher;

    @Resource(name = "articleService")
    private IArticleService service;


    @Test
    public void test() throws IOException {
        Article article = service.findArticleById(128);
        Document document = new Document();
        document.add(new Field("content",article.getContent(), TextField.TYPE_STORED));
        document.add(new Field("header",article.getHeader(),TextField.TYPE_STORED));
        document.add(new Field("id",article.getId() + "", StringField.TYPE_STORED));
        indexWriter.addDocument(document);
        System.out.println("建立索引成功");
    }

    @Test
    public void test2() throws ParseException, IOException {

        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("价格");
        TopDocs topDocs = searcher.search(query,10);
        System.out.println("符合条件的记录有："+topDocs.scoreDocs.length);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs){
            Document document = searcher.doc(scoreDoc.doc);
            System.out.println("id = " + document.get("id"));
            System.out.println("content = " + document.get("content"));
        }
    }


}

package com.oneclouder.pidm.article.service.impl;

import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IArticleSearchService;
import com.oneclouder.pidm.article.service.IArticleService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-14.
 */
@Service("searchService")
public class ArticleSearchServiceImpl implements IArticleSearchService{

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


    @Override
    public Map<String,Object> searchArticle(String which,String keywords,int limit) {

        List<Article> articles = new ArrayList<>();
        Map<String,Object> result = new HashMap<>();
        try {
            DirectoryReader ireader = DirectoryReader.openIfChanged(directoryReader);
            IndexSearcher isearcher;
            if (ireader == null){
                isearcher = new IndexSearcher(directoryReader);
            }else {
                isearcher = new IndexSearcher(ireader);
            }
            QueryParser parser = new QueryParser(which,analyzer);
            Query query = parser.parse(keywords);
            TopDocs topDocs = isearcher.search(query,1000);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs){
                Document document = isearcher.doc(scoreDoc.doc);
                Integer aid = Integer.parseInt(document.get("id"));
                Article article = articleService.findPublishedArticleById(aid);
                if (article !=null){
                    article.setContent(article.getContent().substring(0,200));
                    articles.add(article);
                }
            }
            result.put("articles",articles);
            result.put("total",articles.size());
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateArticleIndex(Article article) {
        Document document = new Document();
        document.add(new Field("content",article.getContent(), TextField.TYPE_STORED));
        document.add(new Field("header",article.getHeader(), TextField.TYPE_STORED));
        document.add(new Field("id",article.getId() + "", StringField.TYPE_STORED));
        try {
            indexWriter.updateDocument(new Term("id",article.getId() + ""),document);
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticleIndex(Integer aid) {
        try {
            indexWriter.deleteDocuments(new Term("id",aid+""));
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addArticleIndex(Article article) {
        Document document = new Document();
        document.add(new Field("content",article.getContent(), TextField.TYPE_STORED));
        document.add(new Field("header",article.getHeader(), TextField.TYPE_STORED));
        document.add(new Field("id",article.getId() + "", StringField.TYPE_STORED));
        try {
            indexWriter.addDocument(document);
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

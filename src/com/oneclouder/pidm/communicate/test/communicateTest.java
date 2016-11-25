package com.oneclouder.pidm.communicate.test;

import com.oneclouder.pidm.communicate.model.Communicate;
import com.oneclouder.pidm.communicate.model.FormBean;
import com.oneclouder.pidm.communicate.model.Question;
import com.oneclouder.pidm.communicate.model.Reply;
import com.oneclouder.pidm.communicate.service.ICommunicateService;
import com.oneclouder.pidm.communicate.service.IQuestionService;
import com.oneclouder.pidm.communicate.service.IReplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zheng.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class communicateTest {

    @Resource(name = "communicateService")
    ICommunicateService communicateService;
    @Resource(name = "replyService")
    IReplyService replyService;
    @Resource(name = "questionService")
    IQuestionService questionService;

    //定义时间
    Date date = new Date();
    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time=format.format(date);

    //提交
    @Test
    public void submitTest() throws SQLException {
        Communicate communicate = new Communicate();
        communicate.setContent("知道我不完美，能给的我都给！");
        communicate.setTitle("一万毫升眼泪");
        communicate.setStatus(1);
        communicate.setTime(time);
        communicate.setUserId(1);
//        System.out.print("****-*-*--**--**--**--*---****"+time+"0..0..0..0..0..0..0..0");
        communicateService.addCommunicate(communicate);
    }

    //回复 提交
    @Test
    public void submitReplyTest() throws SQLException{
        Reply reply = new Reply();

        reply.setCommunicateId(2);
        reply.setEmployeeId(1);
        reply.setContent("谢谢你的咨询！");
        reply.setTime(time);

        replyService.addReply(reply);
        System.out.println("回复 已录入数据库");
        communicateService.changeStatus(3,0);
    }

    //再提问 提交
    @Test
    public void submitQuestionTest() throws SQLException{
        Question question = new Question();
        question.setContent("只是歌词而已，客气客气！");
        question.setTime(time);
        question.setCommunicateId(2);

        questionService.addQuestion(question);
        //设置交流表status为1，1表示为已回复
        Integer status = 0;
        communicateService.changeStatus(3,status);
        System.out.println("成功");
    }

    //查看回复和再提问
    @Test
    public void findReplyByComId() throws SQLException {
//        /****/
//        List<Reply> replys = replyService.findReplyByComId(2);
//        Reply reply = replyService.findReplyByComId(2).get(0);
//        System.out.println(reply.getEmployee().getUserName());
//        for(Reply reply : replys){
//            System.out.println("管理员回复的："+reply.getEmployee().getName());
//        }

        /****/
        List<Question> questions = questionService.findQuestionByComId(2);
        System.out.println(questions);
        for(Question question : questions){
            System.out.println("再提问的："+question.getCommunicate().getUser().getRealName());
        }
    }


    //查找交流表项
    @Test
    public void findUserAndCompanyName() throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 0);
        params.put("start", 2);
        params.put("column", null);
        params.put("text", null);
        List<FormBean> formBeans = communicateService.findCommunicateByPag(params);
        for (FormBean formBean : formBeans){
            System.out.println("ID："+ formBean.getId() );
            System.out.println("title："+ formBean.getTitle());
            System.out.println("提问人："+ formBean.getAskName());
//            System.out.println("回答人："+ formBean.getAnswerName());
//            System.out.println("回答时间："+ formBean.getAnswerTime());
        }
    }

    @Test
    public void findCommunicate() throws SQLException{
        Communicate communicate = communicateService.findCommunicateById(2);
        System.out.println(communicate.getContent());
    }

    //前台用户 查看交流表
    @Test
    public void findCommunicateByUId() throws SQLException{
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("limit",5);
        params.put("start",0);
        params.put("uId",1);
        List<Communicate> communicates = communicateService.findComByUIdPage(params);

        for (Communicate communicate : communicates){
            System.out.println("标题："+communicate.getTitle());
        }

        System.out.println("总数"+communicateService.getTotal3(1));

    }

    @Test
    //测试系统时间
    public void testTime() throws InterruptedException {

        int i;
        for(i=0;i<10;i++){

            //定义时间
            Date date = new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=format.format(date);

            Thread.sleep(2000);
            System.out.println(time);
        }

    }



}

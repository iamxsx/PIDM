package com.oneclouder.pidm.communicate.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.oneclouder.pidm.communicate.dao.IQuestionDao;
import com.oneclouder.pidm.communicate.model.Communicate;
import com.oneclouder.pidm.communicate.model.FormBean;
import com.oneclouder.pidm.communicate.model.Question;
import com.oneclouder.pidm.communicate.model.Reply;
import com.oneclouder.pidm.communicate.service.ICommunicateService;
import com.oneclouder.pidm.communicate.service.IQuestionService;
import com.oneclouder.pidm.communicate.service.IReplyService;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.webBean.UserInfo;
import com.sun.deploy.net.HttpResponse;
import org.apache.ibatis.jdbc.SQL;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zheng.
 */
@Controller
public class CommunicateAction {

    @Resource(name = "communicateService")
    ICommunicateService communicateService;
    @Resource(name = "replyService")
    IReplyService replyService;
    @Resource(name = "questionService")
    IQuestionService questionService;

    /**
     * 连接后台查看 交流表 详情
     */
    @RequestMapping(value = "back/communicate/detail", method = RequestMethod.GET)
    public ModelAndView Detail(ModelAndView model,Integer communicateId){
        model.addObject("communicateId",communicateId);
        model.setViewName("/back/communicate/detail");
        return model;
    }

    /**
     * 前台用户查看 交流表详情
     */
    @RequestMapping(value = "communicate/userDetail", method = RequestMethod.GET)
    public ModelAndView userDetail(ModelAndView model,Integer communicateId){
        model.addObject("communicateId",communicateId);
        model.setViewName("/foreground/communicate/detail");
        return model;
    }

    /**
     * 后台 连接查看交流表
     */
    @RequestMapping(value = "back/communicate/showList", method = RequestMethod.GET)
    public String ShowList(){
        return "/back/communicate/showList";
    }

    /**
     * 提交
     * @param title
     * @param content
     * @param type
     */
    @RequestMapping(value = "communicate/submit-communicate",method = RequestMethod.GET)
    public void submitCommunicate(@RequestParam String title, @RequestParam String content, @RequestParam Integer type, HttpSession session, HttpServletResponse response) throws SQLException {
        //获得用户
        NUserInfo user = (NUserInfo) session.getAttribute("userInfo");        // TODO: 16-9-12 用户是否为空  用户什么的session
        if(user != null){
            System.out.print("当前用户ID："+ user.getId());
            //获得系统时间 定义时间格式
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);

            //封装 咨询
            Communicate communicate = new Communicate();
            communicate.setUserId(user.getId());
            communicate.setTitle(title);
            communicate.setContent(content);
            communicate.setTime(time);
            communicate.setType(type);
            communicate.setStatus(0);
            //存入 咨询 到数据库
            communicateService.addCommunicate(communicate);
        }
    }

    /**
     * 其他人的问题
     */
    @RequestMapping(value = "communicate/othersCommunicate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> othersCommunicate(int offset, int limit ) throws SQLException{
        Map<String , Object> json = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("start", offset);
        List<FormBean> formBeans = communicateService.findComByReplyPage(params);
        List<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();
        for(FormBean formBean:formBeans){
            Map<String,Object> row = new HashMap<String,Object>();
            row.put("communicateId",formBean.getId());
            row.put("title",formBean.getTitle());
            row.put("content",formBean.getContent());
            row.put("askName",formBean.getAskName());
            row.put("companyName",formBean.getCompanyName());
            row.put("askTime",formBean.getAskTime());
            row.put("status",formBean.getStatus());
            row.put("type",formBean.getType());
            row.put("replyTime",formBean.getReplyTime());
            row.put("employeeName",formBean.getReplyName());
            row.put("replyContent",formBean.getReplyContent());

            rows.add(row);
        }
        json.put("rows",rows);  //存放数据
        return json;
    }


    /**
     * 后台 查看 交流表项
     */
    @RequestMapping(value = "back/communicate/show-communicate-list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> showCommunicateList(int offset, int limit ,String column, String text) throws SQLException {
        if(column.equals("type")){
            if(text.indexOf("经验")>-1 || text.indexOf("交流")>-1){text="0";}
            if(text.indexOf("建议")>-1 || text.indexOf("意见")>-1){text="1";}
        }
        if(column.equals("status")){
            if(text.indexOf("已")>-1 || text.indexOf("有")>-1){text="1";}
            if(text.indexOf("未")>-1 || text.indexOf("没")>-1){text="0";}
        }

        Map<String,Object> json = new HashMap<String,Object>();

        //情况一、查找所有有回复的
        if(column.equals("replyName") || column.equals("replyTime")){
            Map<String, Object> params = new HashMap<>();
            params.put("limit", limit);
            params.put("start", offset);
            params.put("column", column);
            params.put("text", text);
            List<FormBean> formBeans = communicateService.findComByReplyPage(params);
            List<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();
            for(FormBean formBean:formBeans){
                Map<String,Object> row = new HashMap<String,Object>();
                row.put("communicateId",formBean.getId());
                row.put("title",formBean.getTitle());
                row.put("content",formBean.getContent());
                row.put("askName",formBean.getAskName());
                row.put("companyName",formBean.getCompanyName());
                row.put("askTime",formBean.getAskTime());
                row.put("status",formBean.getStatus());
                row.put("type",formBean.getType());
                row.put("replyTime",formBean.getReplyTime());
                row.put("employeeName",formBean.getReplyName());

                rows.add(row);
            }
            Integer total = communicateService.getTotal2(params);
            json.put("total",total);    //存放数据的条数
            json.put("rows",rows);  //存放数据
        }else
            //情况二、查找交流表 包括已回复的和未回复的
            {
            Map<String, Object> params = new HashMap<>();
            params.put("limit", limit);
            params.put("start", offset);
            params.put("column", column);
            params.put("text", text);
            //rows存放交流表内容
            List<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();
            List<FormBean> formBeans = communicateService.findCommunicateByPag(params);
            for(FormBean formBean : formBeans){
                //参数communicate_id,title,type,company_name,username,ask_time,status
                // employee_name,answer_time
                // TODO: 16-9-12 可以前台遍历 也可以后台遍历
                Map<String,Object> row = new HashMap<String,Object>();
                row.put("communicateId",formBean.getId());
                row.put("title",formBean.getTitle());
                row.put("content",formBean.getContent());
                row.put("askName",formBean.getAskName());
                row.put("companyName",formBean.getCompanyName());
                row.put("askTime",formBean.getAskTime());
                row.put("status",formBean.getStatus());
                row.put("type",formBean.getType());
                //获得最新的回复人和回复时间
                List<Reply> replys = replyService.findReplyByComId(formBean.getId());
                if(replys.size()>0){
                    Reply reply = replys.get(0);
                    String replyTime = reply.getTime();
                    String employeeName = reply.getEmployee().getRealName();
                    String replyContent = reply.getContent();
                    row.put("replyTime",replyTime);
                    row.put("employeeName",employeeName);
                    row.put("replyContent",replyContent);

                }else {
                    row.put("replyTime","");
                    row.put("employeeName","");
                    row.put("replyContent","");
                }
                rows.add(row);
            }
            Integer total = communicateService.getTotal(params);
            json.put("total",total);    //存放数据的条数
            json.put("rows",rows);  //存放数据
        }
        //json回显 回前端
        return json;
    }

    /**
     * 管理员回复
     * @param communicate_id
     * @param content
     */
    @RequestMapping(value = "back/communicate/submit-reply",method = RequestMethod.GET)
    public void submitReply(@RequestParam Integer communicate_id, @RequestParam String content, HttpSession session, HttpServletResponse response) throws SQLException {
        // TODO: 16-9-14 管理员ID
        //从shiro的session中获取登陆信息
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        Integer employeeId = employee.getId();

        //获得系统时间 定义时间格式
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        Reply reply = new Reply();
        reply.setCommunicateId(communicate_id);
        reply.setEmployeeId(employeeId);
        reply.setContent(content);
        reply.setTime(time);
        //管理员回复录入数据库
        replyService.addReply(reply);
        //设置交流表status为1，1表示为已回复
        Integer status = 1;
        communicateService.changeStatus(communicate_id,status);
    }


    /**
     * 前台用户 再接着 咨询
     * @param communicate_id
     * @param content
     */
    @RequestMapping(value = "communicate/submit-question",method = RequestMethod.GET)
    public void submitQestion(@RequestParam Integer communicate_id, @RequestParam String content, HttpSession session, HttpServletResponse response) throws SQLException {

        //获得系统时间 定义时间格式
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        Question question = new Question();
        question.setContent(content);
        question.setTime(time);
        question.setCommunicateId(communicate_id);

        questionService.addQuestion(question);

        //设置交流表status为1，1表示为已回复
        Integer status = 0;
        communicateService.changeStatus(communicate_id,status);
    }


    /**
     * 前台 后台 共用
     * 查看详细交流和回复内容
     * @param communicateId
     */
    @RequestMapping(value = "communicate/show-detailed-communicate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> foreground(@RequestParam Integer communicateId,HttpSession session) throws SQLException{
        return showDetailedCommunicate(communicateId,session);
    }
    @RequestMapping(value = "back/communicate/show-detailed-communicate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> back(@RequestParam Integer communicateId,HttpSession session) throws SQLException{
        return showDetailedCommunicate(communicateId,session);
    }


    public Map<String,Object> showDetailedCommunicate(@RequestParam Integer communicateId,HttpSession session) throws SQLException{
        //回复的内容
        Map<String,Object> json = new HashMap<String,Object>();
        List<Reply> replys = replyService.findReplyByComId(communicateId);
        List<Map<String,Object>> rows_reply_question = new LinkedList<Map<String,Object>>();
        if(replys.size()>0) {
            for (Reply reply : replys) {
                Map<String, Object> row = new HashMap<String, Object>();
                row.put("content", reply.getContent());
                row.put("time", reply.getTime());
                row.put("name", reply.getEmployee().getRealName()+"  回复于  ");

                rows_reply_question.add(row);
            }
        }
        //再提问的内容
        List<Question> questions = questionService.findQuestionByComId(communicateId);
        List<Map<String,Object>> rows_question = new LinkedList<Map<String, Object>>();
        if(questions.size()>0) {
            for (Question question : questions) {
                Map<String, Object> row = new HashMap<String, Object>();
                row.put("content", question.getContent());
                row.put("time", question.getTime());
                row.put("name",question.getCommunicate().getUser().getRealName()+"  提问于  ");

                rows_reply_question.add(row);
            }
        }
        //查看主题
        Communicate communicate = communicateService.findCommunicateById(communicateId);
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("id",communicate.getId());
        map.put("title",communicate.getTitle());
        map.put("type",communicate.getType());
        map.put("status",communicate.getStatus());
        map.put("name",communicate.getUser().getRealName());
        map.put("time",communicate.getTime());
        map.put("content",communicate.getContent());


        json.put("title_info",map);
        json.put("rows_reply_question",rows_reply_question);


        return json;
    }


    /**
     * 前台 个人中心信息  交流查看
     */
    @RequestMapping(value = "communicate/myCommunicate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> myCommunicate(HttpSession session,int offset, int limit){
        NUserInfo user = (NUserInfo) session.getAttribute("userInfo");
        Integer uId = user.getId();

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("limit",limit);
        params.put("start",offset);
        params.put("uId",uId);

        Map<String ,Object> json = new HashMap<String, Object>();
        List<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();
        //用户Id待定
        List<Communicate> communicates = communicateService.findComByUIdPage(params);
        for (Communicate communicate : communicates){
            Map<String,Object> map = new HashMap<String ,Object>();
            map.put("id",communicate.getId());
            map.put("title",communicate.getTitle());
            map.put("type",communicate.getType());
            map.put("status",communicate.getStatus());
            map.put("name",communicate.getUser().getRealName());
            map.put("time",communicate.getTime());
            map.put("content",communicate.getContent());

            rows.add(map);
        }
        Integer total = communicateService.getTotal3(uId);
        json.put("total",total);
        json.put("rows",rows);
        return json;
    }

}

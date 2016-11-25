package com.oneclouder.pidm.workFlow.controller;

import com.oneclouder.pidm.article.model.Annex;
import com.oneclouder.pidm.article.model.Article;
import com.oneclouder.pidm.article.service.IAnnexService;
import com.oneclouder.pidm.article.service.IArticleService;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INCompanyEmployeeService;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.n_user.web_bean.RegisterFormBean;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by xucb on 16-9-11.
 */
@Controller
//@RequestMapping("/back/work")
public class WorkFlowAction {
    @Resource(name = "workFlowService")
    private IWorkFlowService workFlowServiceImpl;

    @Resource(name = "articleService")
    private IArticleService articleService;

    @Resource(name = "annexService")
    private IAnnexService annexService;

    @Resource(name = "nUserService")
    INUserService nUserService;

    @Resource(name = "nCompanyEmployeeService")
    INCompanyEmployeeService nCompanyEmployeeService;

    /**
     * 文章预览
     */
    @RequestMapping("/back/work/article-preview")
    @ResponseBody
    public List<Object> preview(Integer aid) {
        List<Object> list = new ArrayList<>();
        Article article = articleService.findArticleById(aid);
        list.add(article);
        Annex annex = annexService.findAnnexByArticleId(aid);
        if (annex != null) {
            list.add(annex);
        }
        return list;
    }

    //前台用户信息展示
    @RequestMapping("/work/showInfo")
    @ResponseBody
    public RegisterFormBean showInfoFront(Integer entityId) throws SQLException {
        return this.showInfo(entityId);
    }

    //后台用户信息展示
    @RequestMapping("/back/work/showInfo")
    @ResponseBody
    public RegisterFormBean showInfoBack(Integer entityId) throws SQLException{
        return this.showInfo(entityId);
    }

    /**
     * 用户信息展示
     */
    public RegisterFormBean showInfo(Integer entityId) throws SQLException {
        RegisterFormBean registerFormBean = new RegisterFormBean();
        NUser user = nUserService.findTempUserById(entityId);
        String tempUrl = nUserService.findTempUrl(user.getId());
        if(tempUrl != null && !"".equals(tempUrl)){
            registerFormBean.setFilePath(tempUrl);
        }
        NCompany company = user.getCompany();
        List<NCompanyEmployee> employees = nCompanyEmployeeService.getTCompanyEmployeeByCId(company.getId());
        List<NCompanyEmployee> designatedContact = new ArrayList<>();
        List<NCompanyEmployee> introduceds = new ArrayList<>();
        NCompanyEmployee legalRep = new NCompanyEmployee();
        for(NCompanyEmployee nCompanyEmployee : employees){
            if(nCompanyEmployee.getNature() == 0){
                designatedContact.add(nCompanyEmployee);
            }
            if(nCompanyEmployee.getNature() == 1){
                introduceds.add(nCompanyEmployee);
            }
            if(nCompanyEmployee.getNature() == 2){
                legalRep = nCompanyEmployee;
            }
        }
        registerFormBean.setCompany(company);
        registerFormBean.setDesignatedContacts(designatedContact);
        registerFormBean.setIntroduceds(introduceds);
        registerFormBean.setLegalRep(legalRep);
        registerFormBean.setUser(user);
        return registerFormBean;
    }

    /**
     * 用户信息展示页面
     */
    @RequestMapping("to-userView")
    public ModelAndView userView(ModelAndView modelAndView){
        modelAndView.setViewName("back/staging/userMessage");
        return modelAndView;
    }
    /**
     * 跳转到待办事项页面
     */
    @RequestMapping("/back/work/to-do-list")
    public String toDoList(){
        return "back/staging/schedule";
    }

    /**
     * 跳转到待办事项的流程图页面
     */
    @RequestMapping("/back/work/to-flowsheet")
    public ModelAndView toFlowSheetBack(ModelAndView model,String taskId) {
        return this.toFlowSheet(model,taskId);
    }

    @RequestMapping("/work/to-flowsheet")
    public ModelAndView toFlowSheetFront(ModelAndView model,String taskId) {
        return this.toFlowSheet(model,taskId);
    }

    public ModelAndView toFlowSheet(ModelAndView model,String taskId){
        model.addObject("taskId",taskId);
        model.setViewName("back/staging/flowsheet");
        Map<String, Object> coordinate = coordinate(taskId);
        int x = (int) coordinate.get("x");
        int y = (int) coordinate.get("y");
        int width = (int) coordinate.get("width");
        int height = (int) coordinate.get("height");
        model.addObject("x",x);
        model.addObject("y",y);
        model.addObject("width",width);
        model.addObject("height",height);
        return model;
    }

    /**
     * 跳转到已办事项列表
     */
    @RequestMapping("/back/work/pending")
    public String pending(){
        return "back/staging/pending";
    }
    /**
     * 跳转到已办事项的流程图
     */
    @RequestMapping("to-hiflowsheet")
    public ModelAndView toHiflowsheet(ModelAndView model,String processInstanceId){
        model.addObject("processInstanceId",processInstanceId);
        model.setViewName("back/staging/pending-flowsheet");
        return model;
    }
    /**
     * 组任务跳转到待办事项的详情展示
     */
    @RequestMapping("/back/work/to-sc-datails")
    public ModelAndView scheduleDetails(ModelAndView model,String taskId){
        //认领任务
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = employee.getRealName();
        workFlowServiceImpl.claim(taskId,username);
        model.setViewName("/back/staging/schedule-details");
        return model;
    }
    /**
     * 待办事项中的个人任务跳转到详情展示
     */
    @RequestMapping("/back/work/to-datails")
    public ModelAndView scheduleDetail(ModelAndView model,String taskId){
        model.setViewName("/back/staging/schedule-details");
        return model;
    }

    /**
     * 跳转到已办事项的详情展示
     */
    @RequestMapping("/back/work/to-pe-datails")
    public ModelAndView pendingDetails(ModelAndView model){
        model.setViewName("/back/staging/pending-details");
        return model;
    }
    /**
     * 跳转到个人中心
     */
    @RequestMapping("/work/to-personal")
    public ModelAndView personal(ModelAndView modelAndView){
        modelAndView.setViewName("/back/staging/personal");
        return modelAndView;
    }
    /**
     * 个人中心跳转到已处理详情
     */
    @RequestMapping("/work/to-perhis-details")
    public ModelAndView perhisDetails(ModelAndView modelAndView){
        modelAndView.setViewName("/back/staging/per-his-details");
        return modelAndView;
    }
    /**
     * 个人中心跳转到未处理详情
     */
    @RequestMapping("/work/to-per-details")
    public ModelAndView perDetails(ModelAndView modelAndView){
        modelAndView.setViewName("/back/staging/per-details");
        return modelAndView;
    }
    /**
     * 部署流程定义
     */
    @RequestMapping("/back/work/deployment")
    @ResponseBody
    public String deployment(){
        workFlowServiceImpl.saveNewDeploye("diagrams/User.bpmn","diagrams/User.png","User");
        workFlowServiceImpl.saveNewDeploye("diagrams/Article.bpmn","diagrams/Article.png","Article");
        return "deployment-success";
    }

    /**
     * 查看待办事项
     */
    @RequestMapping("/back/work/schedule")
    @ResponseBody
    public Map<String,Object> schedule(@RequestBody Map<String,Object> map,HttpServletRequest request){
        String condition = (String) map.get("condition");
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = employee.getRealName();

        List<Task> taskL = workFlowServiceImpl.findTaskList(username);
        for (Task task:taskL){
            String taskId = task.getId();
            workFlowServiceImpl.rollBack(taskId);
        }

        List<Task> taskList;
        if(condition!=null && !"".equals(condition)){
            taskList = workFlowServiceImpl.findGoupTaskByCondition(username,condition);
        }
        else{
            taskList = workFlowServiceImpl.findGroupTask(username);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        List<Task> limiList;
        if(condition!=null && !"".equals(condition)){
            limiList = workFlowServiceImpl.findGoupTaskByConditionLimit(username,condition,(Integer)map.get("offset"),(Integer)map.get("limit"));
        }
        else{
            limiList = workFlowServiceImpl.findGoupTaskByLimit(username,(Integer)map.get("offset"),(Integer)map.get("limit"));
        }
        for(Task task:limiList){
            String taskId = task.getId();
            String key = workFlowServiceImpl.findKeyByTaskId(taskId);
            String type = workFlowServiceImpl.findTypeByTaskId(taskId);
            String businessKey = workFlowServiceImpl.findBusinessKeyByTaskId(taskId);
            String entity = workFlowServiceImpl.findIdByBusinessKey(businessKey);
            String workType = key + type;
            List<HistoricTaskInstance> htasklist = workFlowServiceImpl.findActiviti(taskId);
            String actName = htasklist.get(0).getName();
            Date startTime = htasklist.get(0).getStartTime();
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("taskId",taskId);
            m.put("workType",workType);
            m.put("actName",actName);
            m.put("startTime",startTime);
            m.put("entityId",entity);
            list.add(m);
        }
        Map<String,Object> json = new HashMap<>();
        json.put("rows",list);
        json.put("total",taskList.size());
        return json;
    }
    /**
     * 开启流程实例 测试
     */
    @RequestMapping("/start")
    public void testStart(){
        int entityId = 1;
        String username = "文章";
        String key = "Article";
        String type = "Insert" ;
        workFlowServiceImpl.start(entityId,username,key,type);
    }

    /**
     * 个人中心查看个人任务
     */
    @RequestMapping("/work/personal")
    @ResponseBody
    public Map<String,Object> Personal(@RequestBody Map<String,Object> map,HttpServletRequest request,HttpSession session){
        //前台用户
        NUserInfo user = (NUserInfo) session.getAttribute("userInfo");
        String username = user.getAccount();
//        String username = "撰稿人";
        List<Task> taskList = workFlowServiceImpl.findTaskList(username);
        List<Task> list = workFlowServiceImpl.findTaskListByName(username,(Integer)map.get("offset"),(Integer)map.get("limit"));
        List<Map<String,Object>> listmap = new ArrayList<>();
        for (Task task:list){
            String taskId = task.getId();
            String key = workFlowServiceImpl.findKeyByTaskId(taskId);
            String type = workFlowServiceImpl.findTypeByTaskId(taskId);
            String workType =key+type;
            String businesskey = workFlowServiceImpl.findBusinessKeyByTaskId(taskId);
            String entityId = workFlowServiceImpl.findIdByBusinessKey(businesskey);
            Date startTime = task.getCreateTime();
            Map<String,Object> m = new HashMap<>();
            m.put("actName",task.getName());
            m.put("taskId",taskId);
            m.put("entityId",entityId);
            m.put("workType",workType);
            m.put("startTime",startTime);
            listmap.add(m);
        }
        Map<String,Object> json = new HashMap<>();
        json.put("rows",listmap);
        json.put("total",taskList.size());
        return json;
    }

    /**
     * 认领任务
     */
    @RequestMapping("/back/work/claim")
    @ResponseBody
    public String claim(String taskId,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = employee.getRealName();
        workFlowServiceImpl.claim(taskId,username);
        return "success";
        //// TODO: 16-9-12 return 显示的详情
    }
    /**
     * 取消认领的任务
     */
    @RequestMapping("/back/work/rollBack")
    @ResponseBody
    public String rollBack(String taskId){
        workFlowServiceImpl.rollBack(taskId);
        return "rollBack-success";
    }

    /**
     * 完成认领的组任务
     */
    @RequestMapping("/back/work/complete")
    @ResponseBody
    public String complete(String taskId,String comment,String valiable,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = employee.getRealName();
        workFlowServiceImpl.claim(taskId,username);
        workFlowServiceImpl.completeTask(taskId,username,comment,valiable);
        return "complete-success";
    }

    @RequestMapping("/back/work/completePersonal")
    @ResponseBody
    public String completePersonal(String taskId,String comment,String valiable,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = employee.getRealName();
        workFlowServiceImpl.completeTask(taskId,username,comment,valiable);
        return "complete-success";
    }
    /**
     * 前台用户完成任务
     */
    @RequestMapping("/work/reapply")
    @ResponseBody
    public String frontComplete(String taskId,String username,HttpServletRequest request){
        workFlowServiceImpl.reapply(taskId,username);
        return "frontComplete-success";
    }

    /**
     * 待办事项中流程轨迹
     */
    @RequestMapping("/back/work/comment")
    @ResponseBody
    public List<Map<String,Object>> commentBack(String taskId){
        return this.comment(taskId);
    }

    @RequestMapping("/work/comment")
    @ResponseBody
    public List<Map<String,Object>> commentBFront(String taskId){
        return this.comment(taskId);
    }

    public List<Map<String,Object>> comment(String taskId){
        List<Map<String,Object>> comment = workFlowServiceImpl.findHistoricComment(taskId);
        return comment;
    }

    /**
     * 待办事项中流程图
     */
    @RequestMapping("/back/work/flowsheet")
    public void flowsheetBack(String taskId, HttpServletResponse response) {
        this.flowsheet(taskId,response);
    }

    @RequestMapping("/work/flowsheet")
    public void flowsheetFront(String taskId, HttpServletResponse response){
        this.flowsheet(taskId,response);
    }

    public void flowsheet(String taskId, HttpServletResponse response) {
    InputStream inputStream = workFlowServiceImpl.viewImageByTaskId(taskId);
    try {
        int size = inputStream.available();
        byte data[] = new byte[size];
        inputStream.read(data);
        response.setContentType("image/png"); // 设置返回的文件类型
        OutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();
        os.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    /**
     * 待办事项 流程图的坐标信息
     */
    @RequestMapping("/back/work/coordinate")
    @ResponseBody
    public Map<String,Object> coordinateBack(String taskId) {
        return this.coordinate(taskId);
    }

    @RequestMapping("/work/coordinate")
    @ResponseBody
    public Map<String,Object> coordinateFront(String taskId) {
        return this.coordinate(taskId);
    }

    public Map<String,Object> coordinate(String taskId){
        Map<String, Object> coordinate = workFlowServiceImpl.coordinate(taskId);
        return coordinate;
    }

    /**
     * 查看已办事项 列表
     */
    @RequestMapping("/work/completeActivity")
    @ResponseBody
    public Map<String,Object> completeActivityFront(@RequestBody Map<String,Object> map,HttpServletRequest request,HttpSession session){
        return this.completeActivity(map,request,session);
    }

    @RequestMapping("/back/work/completeActivity")
    @ResponseBody
    public Map<String,Object> completeActivityBack(@RequestBody Map<String,Object> map,HttpServletRequest request,HttpSession session) {
        return this.completeActivity(map,request,session);
    }
    public Map<String,Object> completeActivity(@RequestBody Map<String,Object> map,HttpServletRequest request,HttpSession session){
        //获取查询列表的查询条件
        String condition = (String) map.get("condition");
        String front = (String) map.get("front");
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String username = null;
        if ("front".equals(front)){
            NUserInfo user = (NUserInfo) session.getAttribute("userInfo");
            username = user.getAccount();
        }
        else {
            username = employee.getRealName();
        }
        Map<String,Object> m = new HashMap<>();
        List<Object> list = new ArrayList<Object>();
        if(condition!=null && !"".equals(condition)){
            list = workFlowServiceImpl.completeActivityByCondition(username,condition);
        }else {
            list = workFlowServiceImpl.completeActivity(username);
        }
        m.put("total",list.size());
        List<Object> listmap = new ArrayList<Object>();
        if(condition!=null && !"".equals(condition)){
            listmap = workFlowServiceImpl.completeActivityByConditionLimit(username,condition,(Integer)map.get("offset"),(Integer)map.get("limit"));
        }else {
            listmap = workFlowServiceImpl.completeActivityLimit(username,condition,(Integer)map.get("offset"),(Integer)map.get("limit"));
        }
        m.put("rows",listmap);
        return m;
    }

    /**
     * 查看待办事项的详情
     */
    @RequestMapping("/back/work/nowView")
    @ResponseBody
    public Map<String,Object> nowView(String taskId){
        String business_key = workFlowServiceImpl.findBusinessKeyByTaskId(taskId);
        String key = workFlowServiceImpl.findKeyByBusinessKey(business_key);
        String id = workFlowServiceImpl.findIdByBusinessKey(business_key);
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        map.put("id",id);
        return map;
    }
    /**
     * 查看已办事项的详情
     */
    @RequestMapping("/back/work/historicView")
    @ResponseBody
    public Map<String,Object> historicView(String processInstanceId){
        String business_key = workFlowServiceImpl.findBusinessKeyByProcessInstanceId(processInstanceId);
        String key = workFlowServiceImpl.findKeyByBusinessKey(business_key);
        String id = workFlowServiceImpl.findIdByBusinessKey(business_key);
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        map.put("id",id);
        return map;
    }

    /**
     * 查看已办事项 流程轨迹
     */
    @RequestMapping("/back/work/historicComment")
    @ResponseBody
    public List<Map<String,Object>> findHistoricCommentBackByProcessInstanceId(String processInstanceId) {
        return this.findHistoricCommentByProcessInstanceId(processInstanceId);
    }

    @RequestMapping("/work/historicComment")
    @ResponseBody
    public List<Map<String,Object>> findHistoricCommentFrontByProcessInstanceId(String processInstanceId) {
        return this.findHistoricCommentByProcessInstanceId(processInstanceId);
    }

    public List<Map<String,Object>> findHistoricCommentByProcessInstanceId(String processInstanceId){
        List<Map<String, Object>> list = workFlowServiceImpl.findHistoricCommentByProcessInstanceId(processInstanceId);
        return list;
    }

    /**
     * 查看已办事项 流程图
     */
    @RequestMapping("/back/work/historicViewImage")
    public void historicViewImgeBack(String processInstanceId,HttpServletResponse response) {
        this.historicViewImge(processInstanceId,response);
    }

    @RequestMapping("/work/historicViewImage")
    public void historicViewImgeFront(String processInstanceId,HttpServletResponse response) {
        this.historicViewImge(processInstanceId,response);
    }

    public void historicViewImge(String processInstanceId,HttpServletResponse response){
        InputStream inputStream = workFlowServiceImpl.historicViewImage(processInstanceId);
        try {
            int size = inputStream.available();
            byte data[] = new byte[size];
            inputStream.read(data);
            response.setContentType("image/png"); // 设置返回的文件类型
            OutputStream os = response.getOutputStream();
            os.write(data);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

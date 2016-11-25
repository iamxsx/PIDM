package com.oneclouder.pidm.regManage.controller;

import com.oneclouder.pidm.regManage.model.RegManage;
import com.oneclouder.pidm.regManage.model.Usermessage;
import com.oneclouder.pidm.regManage.service.IRegManage;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.util.*;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * Created by xucb on 16-9-19.
 */
@Controller
@RequestMapping("/back/regmanage")
public class RegManageAction {

    @Resource(name = "regService")
    private IRegManage regService;

    @Resource(name = "historyService")
    private HistoryService historyService;

    @Resource(name = "workFlowService")
    private IWorkFlowService workFlowServiceImpl;

    /**
     * 跳转到注册信息管理页面
     */
    @RequestMapping("to-regmanage")
    public ModelAndView regmanage(ModelAndView model){
        model.setViewName("back/regmanage/regmanage");
        return model;
    }

    /**
     * 跳转到客户申请审批
     */
    @RequestMapping("to-regActivity")
    public ModelAndView toFindActiviti(ModelAndView model){
        model.setViewName("back/regmanage/regActivity");
        return model;
    }


    /**
     * 查询注册信息管理
     * @param map
     * @return
     */
    @RequestMapping("/findRegMessage")
    @ResponseBody
    public Map<String,Object> findRegMessage(@RequestBody Map<String,Object> map){
        RegManage regManage = new RegManage();
        if (map.get("type")!=null && !"".equals((String)map.get("type"))) {
            String t = (String) map.get("type");
            Integer type = Integer.parseInt(t);
            regManage.setType(t);
            if (type == 1) {                    //公司编号
                regManage.setIdentifier((String) map.get("condition"));
            } else if (type == 2) {             //公司名称
                regManage.setCompanyName((String) map.get("condition"));
            } else if (type == 3) {             //协会名称
                regManage.setAssociationName((String) map.get("condition"));
            } else if (type == 4) {             //分会名称
                regManage.setBranchName((String) map.get("condition"));
            }
        }
        Integer offset = (Integer) map.get("offset");
        Integer limit = (Integer) map.get("limit");
        regManage.setOffset(offset);
        regManage.setLimit(limit);
        List<RegManage> regManages = regService.selectRegMessage(regManage);
        int count = regService.selectRegMessageCount(regManage);
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("total",count);
        m.put("rows",regManages);
        return m;
    }

    /**
     * 客户申请审批
     * @return
     */
    /**
     *List<HistoricProcessInstance> allHisProcessInstance = workFlowServiceImpl.findAllHisProcessInstance(id.get(i).getId());
     if(allHisProcessInstance.size()>0){
     for(int j= 0;j<allHisProcessInstance.size();j++){
     Map<String,Object> m = new HashMap<>();
     Integer entityId = id.get(i).getId();
     //String approver = id.get(i).getApprover();
     String realName = id.get(i).getRealName();
     Integer status = id.get(i).getStatus();
     String businessKey = allHisProcessInstance.get(j).getBusinessKey();
     HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
     .processInstanceBusinessKey(businessKey).singleResult();
     String pId = historicProcessInstance.getId();
     String keyByBusinessKey = workFlowServiceImpl.findKeyByBusinessKey(businessKey);
     String typeByBusinessKey = workFlowServiceImpl.findTypeByBusinessKey(businessKey);
     Date startTime = allHisProcessInstance.get(j).getStartTime();
     Date endTime = allHisProcessInstance.get(j).getEndTime();
     m.put("pId",pId);
     // m.put("approver",approver);
     m.put("realName",realName);
     m.put("startTime",startTime);
     m.put("workType",keyByBusinessKey+typeByBusinessKey);
     m.put("status",status);
     m.put("endTime",endTime);
     m.put("entityId",entityId);
     listJson.add(m);
     }
     }
     */
    @RequestMapping("/findRegActiviti")
    @ResponseBody
    public List<Map<String,Object>> findActiviti(Integer companyId) {
       /* RegManage regManage = new RegManage();
        //根据正式表的公司id查询到临时表中公司的id
        int i = regService.selectCompName(companyId);
        regManage.setCompanyId(i);
        List<Usermessage> id = regService.findId(regManage);
        //查询出来只有一个帐号，现在的逻辑是一个公司就只有一个帐号
        //在临时表中一个帐号有多条信息*/

       //通过公司的id来找到用户的account
        String account = regService.selectAccountByCompId(companyId);
        List<Map<String,Object>> listmap = new ArrayList<>();
        //if(id.size()>0){
            //String realName = id.get(0).getAccount();//获取当前帐号的帐号姓名
            //根据当前用户来查询出待办事项和已办事项
        String realName = account;
            List<Task> taskList = workFlowServiceImpl.findTaskList(realName);//查看个人的待办任务
            for (Task task:taskList){
                Map<String,Object> m = new HashMap<>();
                String taskId = task.getId();
                String key = workFlowServiceImpl.findKeyByTaskId(taskId);
                String type = workFlowServiceImpl.findTypeByTaskId(taskId);
                String workType =key+type;
                String businesskey = workFlowServiceImpl.findBusinessKeyByTaskId(taskId);
                String entityId = workFlowServiceImpl.findIdByBusinessKey(businesskey);
                Date startTime = task.getCreateTime();
                m.put("status",regService.findStatus(Integer.parseInt(entityId)));
                m.put("pId",taskId);
                m.put("entityId",entityId);
                m.put("workType",workType);
                m.put("startTime",startTime);
                m.put("realName",realName);
                m.put("endTime",null);
                listmap.add(m);
            }
            List<Object> list = workFlowServiceImpl.completeActivity(realName);//查看个人的已办事项
            for(Object obj:list){
                Map<String,Object> m = new HashMap<>();
                Map map = (Map) obj;
                String enid = (String)map.get("entityId");
                int entityId = Integer.parseInt(enid);
                int status = regService.findStatus(entityId);
                m.put("status",status);
                m.put("pId",map.get("processInstanceId"));
                m.put("workType",map.get("workType"));
                m.put("startTime",map.get("startTime"));
                m.put("endTime",map.get("endTime"));
                m.put("entityId",entityId);
                m.put("realName",realName);
                listmap.add(m);
            }

      // }
        return listmap;
    }

    /**
     * 客户帐号管理
     */
    @RequestMapping("/findAllCount")
    @ResponseBody
    public Map<String,Object> findAllCount(@RequestBody Map<String,Object> map) {
        String t = (String) map.get("companyId");
        Integer i = Integer.parseInt(t);
        Integer offset = (Integer) map.get("offset");
        Integer limt = (Integer) map.get("limit");
        List<Usermessage> allCount = regService.findAllCount(i,offset,limt);
        Map<String,Object> m = new HashMap<>();
        m.put("rows",allCount);
        int all = regService.findAll(i,offset, limt);
        m.put("total",all);
        return m;
    }

    @RequestMapping("show-account-info")
    @ResponseBody
    public Map<String, Object> showAccountInfo(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Usermessage usermessage = regService.findById(id);
        map.put("usermessage", usermessage);
        return map;
    }

    @RequestMapping(value = "change-account-info",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeAccountInfo(@RequestBody Usermessage usermessage) {
        Map<String, Object> map = new HashMap<>();
        regService.changeAccountInfo(usermessage);
        map.put("msg","success");
        return map;
    }

    @RequestMapping("deleteId")
    @ResponseBody
    public String deleteId(int id){
        regService.deleteId(id);
        return "success";
    }

}

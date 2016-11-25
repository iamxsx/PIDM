package com.oneclouder.pidm.workFlow.service.Impl;

import com.oneclouder.pidm.workFlow.dao.IWorkFlowDao;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

/**
 * Created by xucb on 16-9-10.
 */
@Service("workFlowService")
public class WorkFlowServiceImpl implements IWorkFlowService {
    @Resource(name = "processEngine")
    private ProcessEngine processEngine;

    @Resource(name = "repositoryService")
    private RepositoryService repositoryService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "historyService")
    private HistoryService historyService;

    @Resource(name = "workFlowDao")
    private IWorkFlowDao workFlowDao;

    @Override
    public void saveNewDeploye(String url1, String url2, String fileName) {
        Deployment deployment;
        deployment = repositoryService.createDeployment()
                                                .name(fileName)
                                                .addClasspathResource(url1)
                                                .addClasspathResource(url2)
                                                .deploy();
    }

    @Override
    public String findBusinessKeyByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        String business_key = processInstance.getBusinessKey();
        return business_key;
    }

    @Override
    public String findIdByBusinessKey(String business_key) {
        String id = "";
        if (StringUtils.isNotBlank(business_key)){
            id = business_key.split("\\.")[1];
        }
        return id;
    }

    @Override
    public String findKeyByBusinessKey(String business_key) {
        String key = "";
        if (StringUtils.isNotBlank(business_key)){
            key = business_key.split("\\.")[0];
        }
        return key;
    }

    @Override
    public String findTypeByBusinessKey(String business_key) {
        String type = "";
        if(StringUtils.isNotBlank(business_key)){
            type = business_key.split("\\.")[2];
        }
        return type;
    }

    @Override
    public String findTaskIdByBusiness(String business_key) {
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(business_key)
                .singleResult();
        String taskId = task.getId();
        return taskId;
    }

    @Override
    public String findKeyByTaskId(String taskId) {
        String businessKey = findBusinessKeyByTaskId(taskId);
        String key = findKeyByBusinessKey(businessKey);
        return key;
    }

    @Override
    public String findTypeByTaskId(String taskId) {
        String businessKey = findBusinessKeyByTaskId(taskId);
        String type = findTypeByBusinessKey(businessKey);
        return type;
    }

    @Override
    public String findTaskIdById(String simpleName,String id,String type) {
        String business_key = simpleName+"."+id+"."+type;
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(business_key).singleResult();
        String taskId = task.getId();
        return taskId;
    }

    @Override
    public InputStream viewImageByTaskId(String taskId) {
        String key = findKeyByTaskId(taskId);
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .latestVersion()
                .singleResult();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),diagramResourceName);
        return inputStream;
    }

    @Override
    public String findProcessInstanceByBusinessKey(String BussinessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(BussinessKey)
                .singleResult();
        String processInstanceId = processInstance.getId();
        return processInstanceId;
    }

    @Override
    public String findTaskIdByProcessInstance(String ProcessInstanceId) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(ProcessInstanceId)
                .singleResult();
        String taskId = task.getId();
        return taskId;
    }

    @Override
    public InputStream viewImageByProcessInstanceId(String processInstanceId) {
        String taskId = findTaskIdByProcessInstance(processInstanceId);
        return viewImageByTaskId(taskId);
    }

    @Override
    public String startProcess(Integer entity, String username, String key, String type) {
        Map<String,Object> variables = new HashMap<String,Object>();
        variables.put("user",username);
        String objId = key+"."+entity+"."+type;
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(key,objId,variables);
        return processInstance.getId();
    }

    @Override
    public List<Task> findTaskListByName(String username,Integer offset, Integer limit) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(username)
                .orderByTaskCreateTime()
                .desc()
                .listPage(offset,limit);
        return list;
    }

    @Override
    public List<Task> findTaskList(String username) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(username)
                .orderByTaskCreateTime()
                .desc()
                .list();
        return list;
    }

    @Override
    //valiable 是选择分支的字面文字
    public void completeTask(String taskId, String username, String comment, String valiable) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        String processInstanceId = task.getProcessInstanceId();
        Authentication.setAuthenticatedUserId(username);
        taskService.addComment(taskId,processInstanceId,comment);
        if(valiable != null){
            Map<String,Object> valiables = new HashMap<String,Object>();
            valiables.put("message",valiable);
            taskService.complete(taskId,valiables);
        }else {
            taskService.complete(taskId);
        }
    }

    @Override
    public List<Task> findGroupTask(String username) {
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser(username)
                .orderByTaskCreateTime()
                .asc()
                .list();
        return list;
    }

    @Override
    public List<Task> findGoupTaskByCondition(String username, String type) {
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser(username)
                .processDefinitionName(type)
                .orderByTaskCreateTime()
                .asc()
                .list();
        return list;
    }

    @Override
    public List<Task> findGoupTaskByLimit(String username, Integer offset, Integer limit) {
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser(username)
                .orderByTaskCreateTime()
                .asc()
                .listPage(offset,limit);
        return list;
    }

    @Override
    public List<Task> findGoupTaskByConditionLimit(String username, String type, Integer offset, Integer limit) {
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser(username)
                .processDefinitionName(type)
                .orderByTaskCreateTime()
                .asc()
                .listPage(offset,limit);
        return list;
    }

    @Override
    public void claim(String taskId, String username) {
        taskService.claim(taskId,username);
    }

    @Override
    public void rollBack(String taskId) {
        taskService.setAssignee(taskId,null);
    }

    @Override
    public List<Map<String, Object>> findHistoricComment(String taskId) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        String processInstanceId = task.getProcessInstanceId();
        List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
        List<Map<String,Object>> listmap = new ArrayList<>();
        for (Comment comment:list){
            String username = comment.getUserId();
//            String message = comment.getFullMessage();
            String message = workFlowDao.findMessage(comment.getTaskId());
            Map<String,Object> map = new HashMap<>();
            map.put("username",username);
            map.put("message",message);
            String commentTaskId = comment.getTaskId();
            HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                    .taskId(commentTaskId)
                    .singleResult();
            map.put("startTime",historicTaskInstance.getStartTime());
            map.put("endTime",historicTaskInstance.getEndTime());
            map.put("taskName",historicTaskInstance.getName());
            listmap.add(map);
        }
        return listmap;
    }

    @Override
    public Map<String, Object> coordinate(String taskId) {
        // TODO: 16-9-11
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(pi.getActivityId());
        int x = activityImpl.getX();
        int y = activityImpl.getY();
        int width = activityImpl.getWidth();
        int height = activityImpl.getHeight();
        Map<String,Object> map = new HashMap<>();
        map.put("x",x);
        map.put("y",y);
        map.put("width",width);
        map.put("height",height);
        return map;
    }

    @Override
    public List<Object> complete(List<HistoricTaskInstance> list) {
        List<Object> messageList = new ArrayList<>();
        for(HistoricTaskInstance historicTaskInstance:list){
            if(historicTaskInstance.getEndTime()!=null){
                Map<String,Object> map = new HashMap<>();
                String processInstanceId = historicTaskInstance.getProcessInstanceId();
                HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .singleResult();
                String business_key = historicProcessInstance.getBusinessKey();
                //String type = findBusinessKeyByProcessInstanceId(processInstanceId);
                String key = findKeyByBusinessKey(business_key);
                String type = findTypeByBusinessKey(business_key);
                String entityId = findIdByBusinessKey(business_key);
                String workType = key+type;
                Date startTime = historicTaskInstance.getStartTime();
                Date endTime = historicTaskInstance.getEndTime();
                String taskname = historicTaskInstance.getName();
                String user = historicTaskInstance.getAssignee();
                map.put("processInstanceId",processInstanceId);
                map.put("workType",workType);
                map.put("startTime",startTime);
                map.put("endTime",endTime);
                map.put("taskname",taskname);
                map.put("user",user);
                map.put("entityId",entityId);
                messageList.add(map);
            }
        }
        return messageList;
    }

    @Override
    public List<Object> completeActivity(String username) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .list();
        return complete(list);

    }

    @Override
    public List<Object> completeActivityByCondition(String username, String candition) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .processDefinitionName(candition)
                .list();
        return complete(list);
    }

    @Override
    public List<Object> completeActivityLimit(String username, String candition, Integer offset, Integer limit) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .list();
        //        .listPage(offset,limit);
        List<Object> complete = complete(list);
        int sum = offset+limit;
        if(sum > complete.size()){
            sum = complete.size();
        }
        return complete.subList(offset,sum);
    }

    @Override
    public List<Object> completeActivityByConditionLimit(String username, String candition, Integer offset, Integer limit) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .processDefinitionName(candition)
                .list();
         //       .listPage(offset,limit);
        List<Object> complete = complete(list);
        int sum = offset+limit;
        if(sum > complete.size()){
            sum = complete.size();
        }
        return complete.subList(offset,sum);
    }

    @Override
    public void reapply(String taskId,String username) {
        completeTask(taskId,username,"重新提交信息审核",null);
    }

    @Override
    public String findProcessInstanceIdByBusinessKey(String businessKey){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if(processInstance == null){
            return null;
        }
        return processInstance.getId();
    }

    @Override
    public String findTaskIdByBusinessKey(String businessKey) {
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if(task == null){
            return null;
        }
        return task.getId();
    }

    @Override
    public String findBusinessKeyByProcessInstanceId(String processInstanceId) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        System.out.println(historicProcessInstance);
        return historicProcessInstance.getBusinessKey();
    }

    @Override
    public List<Map<String,Object>> findHistoricCommentByProcessInstanceId(String processInstanceId) {
        List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
        List<Map<String,Object>> listmap = new ArrayList<>();
        for (Comment comment:list){
            String username = comment.getUserId();
            String message = workFlowDao.findMessage(comment.getTaskId());
//            String message = comment.getFullMessage();
            Map<String,Object> map = new HashMap<>();
            map.put("username",username);
            map.put("message",message);
            String commentTaskId = comment.getTaskId();
            map.put("taskId",commentTaskId);
            HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                    .taskId(commentTaskId)
                    .singleResult();
            map.put("startTime",historicTaskInstance.getStartTime());
            map.put("endTime",historicTaskInstance.getEndTime());
            map.put("taskName",historicTaskInstance.getName());
            listmap.add(map);
        }
        return listmap;
    }

    @Override
    public InputStream historicViewImage(String processInstanceId) {
        String business_key = findBusinessKeyByProcessInstanceId(processInstanceId);
        String type = findKeyByBusinessKey(business_key);
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(type)
                .singleResult();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream in = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),diagramResourceName);
        return in;
    }

    @Override
    public List<HistoricTaskInstance> findActiviti(String taskId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskId(taskId)
                .list();
        return list;
    }


    @Override
    public List<Map<String,Object>> personal(String username,Integer offset,Integer limit) {
        List<Task> taskListByName = findTaskListByName(username, offset, limit);
        List<Map<String,Object>> listmap = new ArrayList<>();
        for (Task task:taskListByName){
            String taskId = task.getId();
            String businesskey = findBusinessKeyByTaskId(taskId);
            String entityId = findIdByBusinessKey(businesskey);
            Map<String,Object> m = new HashMap<>();
            m.put("taskId",taskId);
            m.put("entityId",entityId);
            listmap.add(m);
        }
        return listmap;
    }

    @Override
    public void start(Integer entityId, String username, String key,String type) {
        // TODO: 16-9-11 调试获得的taskid顺序是否正确
        String processInstance = startProcess(entityId,username,key,type);
        List<Task> list = taskService.createTaskQuery()
                .processInstanceId(processInstance)
                .list();
        String id = list.get(0).getId();
        String str ="";
        if("User".equals(key)){
            str = "注册信息审核";
        }
        else if("Article".equals(key)){
            str = "发布信息审核";
        }
        completeTask(id,username,str,null);
    }

    @Override
    public void setStatusByUserId(Map<String, Object> params) {
        workFlowDao.setStatusByUserId(params);
    }

    @Override
    public void setApprover(Map<String, Object> params) {
        workFlowDao.setApprover(params);
    }

    @Override
    public List<HistoricProcessInstance> findAllHisProcessInstance(Integer entityId) {
        List<HistoricProcessInstance> list = new ArrayList<>();
        String id = String.valueOf(entityId);
        String businessKeyInsert = "User"+"."+id+"."+"Insert";
        String businessKeyUpdate = "User"+"."+id+"."+"Update";
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKeyInsert).singleResult();
        HistoricProcessInstance historicProcessInstance1 = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKeyUpdate).singleResult();
        if (historicProcessInstance!=null){
            list.add(historicProcessInstance);
        }
        if (historicProcessInstance1!=null){
            list.add(historicProcessInstance1);
        }
        return list;
    }

    @Override
    public void userUpdate(Integer entityId, String username, String key, String type) {
//        String businessKey1 = "User"+"."+entityId+"."+"Insert";
//        String businessKey2 = "User"+"."+entityId+"."+"Update";
//        String taskIdByBusinessKey = findTaskIdByBusinessKey(businessKey1);
//        String taskIdByBusinessKey1 = findTaskIdByBusinessKey(businessKey2);
//        if(taskIdByBusinessKey!=null && !"".equals(taskIdByBusinessKey)){
//            reapply(taskIdByBusinessKey,username);
////        }else if(taskIdByBusinessKey1!=null && !"".equals(taskIdByBusinessKey1)){
//            reapply(taskIdByBusinessKey1,username);
//        }else {
            if ("Update".equals(type)){
                String businessKey = "User"+"."+entityId+"."+"Update";
                String taskIdByBusinessKey = findTaskIdByBusinessKey(businessKey);
                if (taskIdByBusinessKey != null){
                    reapply(taskIdByBusinessKey,username);
                }else {
                    start(entityId, username, key, type);
                }
            }
            else if ("Insert".equals(type)){
                String businessKey = "User"+"."+entityId+"."+"Update";
                String taskIdByBusinessKey = findTaskIdByBusinessKey(businessKey);
                if (taskIdByBusinessKey != null){
                    reapply(taskIdByBusinessKey,username);
                }else {
                    String businessKey1 = "User"+"."+entityId+"."+"Insert";
                    String taskIdByBusinessKey1 = findTaskIdByBusinessKey(businessKey1);
                    if (taskIdByBusinessKey1 !=null && !"".equals(taskIdByBusinessKey1)){
                        reapply(taskIdByBusinessKey1,username);
                    }
                }
            }
//        }
    }

    /**
     * 修改前台临时用户的状态
     *
     * @param params
     */
    @Override
    public void setStatusByUserTEMId(Map<String, Object> params) {
        workFlowDao.setStatusByUserTEMId(params);
    }

    /**
     * 修改前台临时用户的审核人
     *
     * @param params
     */
    @Override
    public void setApproverTME(Map<String, Object> params) {
        workFlowDao.setApproverTME(params);
    }

    @Override
    public void setRegisterstatus(int id) {
        workFlowDao.setRegisterstatus(id);
    }

    @Override
    public String findMessage(String taskid) {
        return workFlowDao.findMessage(taskid);
    }

    @Override
    public void setMessageByUserId(Map<String, Object> params) {
        workFlowDao.setMessageByUserId(params);
    }

    @Override
    public void setMessageByTEMPUserId(Map<String, Object> params) {
        workFlowDao.setMessageByTEMPUserId(params);
    }

    @Override
    public void setUserRole(Map<String, Object> params) {
        workFlowDao.setUserRole(params);
    }

    @Override
    public int selectRoleId(String roleName) {
        return workFlowDao.selectRoleId(roleName);
    }

    @Override
    public int selectUserIdByAccount(Integer userId) {
        String userAccount = workFlowDao.selectTUserAccount(userId);
        int id = workFlowDao.selectUserId(userAccount);
        return id;
    }

    @Override
    public String selectTUserAccount(Integer userId) {
        return workFlowDao.selectTUserAccount(userId);
    }

    @Override
    public int selectCompID(int uid) {
        return workFlowDao.selectCompID(uid);
    }

    @Override
    public int selectTEMPCompID(int uid) {
        return workFlowDao.selectTEMPCompID(uid);
    }

}

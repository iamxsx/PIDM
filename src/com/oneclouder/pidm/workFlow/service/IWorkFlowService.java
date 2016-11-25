package com.oneclouder.pidm.workFlow.service;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-10.
 */
public interface IWorkFlowService {
    //部署流程
    public void saveNewDeploye(String url1, String url2, String fileName);
    //根据taskId查询business_key
    public String findBusinessKeyByTaskId(String taskId);
    //根据BusinessKey查询业务Id
    public String findIdByBusinessKey(String business_key);
    //根据BusinessKey查询业务key
    public String findKeyByBusinessKey(String business_key);
    //根据businessKey查询业务Type
    public String findTypeByBusinessKey(String business_key);
    //根据businessKey查询taskId
    public String findTaskIdByBusiness(String business_key);
    //根据taskid查询业务key
    public String findKeyByTaskId(String taskId);
    //根据taskId查询业务type
    public String findTypeByTaskId(String taskId);
    //根据业务Id查询taskId
    public String findTaskIdById(String simpleName,String id,String type);
    //根据taskId查询流程图
    public InputStream viewImageByTaskId(String taskId);
    //根据businessKey来查询流程实例
    public String findProcessInstanceByBusinessKey(String BussinessKey);
    //根据流程实例查询任务Id
    public String findTaskIdByProcessInstance(String ProcessInstanceId);
    //根据流程实例查询流程图
    public InputStream viewImageByProcessInstanceId(String processInstanceId);
    //启动流程实例
    public String startProcess(Integer entity, String username, String key, String type);
    //查看个人任务
    public List<Task> findTaskListByName(String username,Integer offset, Integer limit);
    //查看个人任务没有分页
    public List<Task> findTaskList(String username);
    //完成任务
    public void completeTask(String taskId, String username, String comment, String valiable);
    //查询组任务
    public List<Task> findGroupTask(String username);
    //有查询条件的查看组任务
    public List<Task> findGoupTaskByCondition(String username, String type);
    //查询组任务分页
    public List<Task> findGoupTaskByLimit(String username,Integer offset, Integer limit);
    //查询有查询条件组任务分页
    public List<Task> findGoupTaskByConditionLimit(String username, String type, Integer offset, Integer limit);
    //认领组任务
    public void claim(String taskId, String username);
    //退回组任务
    public void rollBack(String taskId);
    //获取待办任务历史批注
    public List<Map<String,Object>> findHistoricComment(String taskId);
    //获取待办任务流程图坐标
    public Map<String,Object> coordinate(String taskId);
    //抽取已办事项
    public List<Object> complete(List<HistoricTaskInstance> list);
    //查询已办事项
    public List<Object> completeActivity(String username);
    //有查询条件的已办事项
    public List<Object> completeActivityByCondition(String username, String candition);
    //查询已办事项分页
    public List<Object> completeActivityLimit(String username, String candition, Integer offset, Integer limit);
    //查询有查询条件的已办事项分页
    public List<Object> completeActivityByConditionLimit(String username,String candition,Integer offset,Integer limit);
    //重新申请
    public void reapply(String taskId,String username);
    //通过businesskey找到流程实例
    public String findProcessInstanceIdByBusinessKey(String businessKey);
    //通过businesskey找到任务id
    public String findTaskIdByBusinessKey(String businessKey);
    //通过流程实例找到businessKey
    public String findBusinessKeyByProcessInstanceId(String processInstanceId);
    //已办事项流程轨迹
    public List<Map<String,Object>> findHistoricCommentByProcessInstanceId(String processInstanceId);
    //已办事项流程图
    public InputStream historicViewImage(String processInstanceId);

    public List<HistoricTaskInstance> findActiviti(String taskId);
    //提供给业务查询被驳回的工作流
    public List<Map<String,Object>> personal(String username,Integer offset,Integer limit);
    //提供给业务 启动工作流 的接口
    public void start(Integer entityId,String username,String key,String type);
    //修改前台用户的状态
    public void setStatusByUserId(Map<String,Object> params);
    //修改前台用户的审核人
    public void setApprover(Map<String,Object> params);
    //根据用户的id集合查询出所有的流程实例
    public List<HistoricProcessInstance> findAllHisProcessInstance(Integer entityId);
    //用户修改后重新审批
    public void userUpdate(Integer entityId,String username,String key,String type);
    /**
     * 修改前台临时用户的状态
     */
    public void setStatusByUserTEMId(Map<String,Object> params);

    /**
     * 修改前台临时用户的审核人
     */
    public void setApproverTME(Map<String,Object> params);

    public void setRegisterstatus(int id);

    public String findMessage(String taskid);

    public void setMessageByUserId(Map<String,Object> params);

    public void setMessageByTEMPUserId(Map<String,Object> params);

    public void setUserRole(Map<String,Object> params);

    public int selectRoleId(String roleName);

    public int selectUserIdByAccount(Integer userId);

    public String selectTUserAccount(Integer userId);

    public int selectCompID(int uid);

    public int selectTEMPCompID(int uid);

}

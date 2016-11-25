package com.oneclouder.pidm.workFlow.test;

import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by xucb on 16-9-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class test {
    @Resource(name = "workFlowService")
    private IWorkFlowService workFlowService;

    @Autowired
    protected ApplicationContext ctx;
    /**
     * 发布流程定义
     */
    /**
     * 启动流程实例 提供给业务模块调用
     */
    @Test
    public void testStart(){
        System.out.println(ctx);
        int entityId = 1;
        String username = "aaa";
        String key = "User";
        String type="Insert";
        workFlowService.start(entityId,username,key,type);
    }
    /**
     * 测试 获取意见
     */
    @Test
    public void testComment(){
        String message = workFlowService.findMessage("11005");
        System.out.println(message);
    }
    /**
     * 前台用户 个人中心 查看个人任务
     */
    @Test
    public void testPersonal(){
        String username = "aaa";
        Integer offset = 0;
        Integer limit = 10;
        List<Task> list = workFlowService.findTaskListByName(username,offset,limit);
        List<Map<String,Object>> listmap = new ArrayList<>();
        for (Task task:list){
            String taskId = task.getId();
            String workType = workFlowService.findKeyByTaskId(taskId);
            Date startTime = task.getCreateTime();
            Map<String,Object> m = new HashMap<>();
            m.put("taskId",taskId);
            m.put("workType",workType);
            m.put("startTime",startTime);
            listmap.add(m);
        }
        Map<String,Object> json = new HashMap<>();
        json.put("rows",listmap);
        json.put("total",list.size());
        System.out.println(json);
    }
    /**
     * 查看组任务
     */
    @Test
    public void schedule(){
        //查询条件 condition
        String condition = null;
        //获取用户名
        String username = "aaa";
        //分页参数
        Integer limit = 10;
        Integer offset = 0;
        List<Task> taskList;
        if(condition!=null && !"".equals(condition)){
            taskList = workFlowService.findGoupTaskByCondition(username,condition);
        }
        else{
            taskList = workFlowService.findGroupTask(username);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        List<Task> limiList;
        if(condition!=null && !"".equals(condition)){
            limiList = workFlowService.findGoupTaskByConditionLimit(username,condition,offset,limit);
        }
        else{
            limiList = workFlowService.findGoupTaskByLimit(username,offset,limit);
        }
        for(Task task:limiList){
            String taskId = task.getId();
            String workType = workFlowService.findKeyByTaskId(taskId);
            List<HistoricTaskInstance> htasklist = workFlowService.findActiviti(taskId);
            String actName = htasklist.get(0).getName();
            Date startTime = htasklist.get(0).getStartTime();
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("taskId",taskId);
            m.put("workType",workType);
            m.put("actName",actName);
            m.put("startTime",startTime);
            list.add(m);
        }
        Map<String,Object> json = new HashMap<>();
        json.put("rows",list);
        json.put("total",taskList.size());
        System.out.println(json);
    }

    /**
     * 测试sublist
     */
    @Test
    public void testSublist(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        List<Integer> l = list.subList(9,list.size());
        System.out.println(l);

    }

    @Test
    public void testSelectRole(){
        int roleId = workFlowService.selectRoleId("副秘书长");
        System.out.println(roleId);
    }

    @Test
    public void testSelectUserIdByAccount(){
        int roleId = workFlowService.selectUserIdByAccount(18);
        System.out.println(roleId);
    }

    @Test
    public void testSelectTUserAccount(){
        String account = workFlowService.selectTUserAccount(18);
        System.out.println(account);
    }
}

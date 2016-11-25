package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.workFlow.model.GetApplicationContext;
import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Created by xucb on 16-9-29.
 */
public class PublishTakeThrListenerImpl implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("PublishTakeThrListenerImpl!");
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        // TODO: 16-9-11 使用业务的dao来修改状态
        // TODO: 16-9-13 设置变量
        String processInstanceId = delegateExecution.getProcessInstanceId();
        HistoryService historyService = applicationContext.getBean("historyService", HistoryService.class);
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .taskDefinitionKey("task3")
                .orderByTaskCreateTime()
                .desc()
                .list();
        List<HistoricTaskInstance> listzb = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .taskDefinitionKey("task4")
                .orderByTaskCreateTime()
                .desc()
                .list();
        System.out.println(list.get(list.size()-1));
        String assignee = list.get(list.size()-1).getAssignee();
        String zhubian = listzb.get(listzb.size()-1).getAssignee();
        System.out.println(assignee);
        String username = assignee;
        delegateExecution.setVariable("approver1",username);
        delegateExecution.setVariable("zhubian",zhubian);
    }
}

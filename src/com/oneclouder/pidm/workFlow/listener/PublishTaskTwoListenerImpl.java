package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.employee.service.impl.EmployeeServiceImpl;
import com.oneclouder.pidm.workFlow.model.GetApplicationContext;
import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by xucb on 16-9-12.
 */
public class PublishTaskTwoListenerImpl implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("PublishTaskTwoListenerImpl!");
        // TODO: 16-9-13 获取参数 重点测试
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        String processInstanceId = delegateTask.getProcessInstanceId();
        HistoryService historyService = applicationContext.getBean("historyService", HistoryService.class);
        Object approver = delegateTask.getExecution().getVariable("approver1");
        IEmployeeService employeeService = applicationContext.getBean("employeeService",IEmployeeService.class);
        if(approver == null){
            //设置参加审核的人员
            List<Employee> boss = null;
            try {
                boss = employeeService.findByRoleName("执行主编");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(boss !=null){
                for (int i = 0 ;i<boss.size();i++){
                    delegateTask.addCandidateUser(boss.get(i).getRealName());
                }
            }
        }else {
            delegateTask.addCandidateUser(approver.toString());
        }
    }
}

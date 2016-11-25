package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.workFlow.model.GetApplicationContext;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouder on 16-9-10.
 */
public class TaskListenerImpl implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("TaskListenerImpl!");
        //设置执行审核功能的人员
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        IEmployeeService employeeService = applicationContext.getBean("employeeService",IEmployeeService.class);
        Object approver = delegateTask.getExecution().getVariable("assignee");
        if(approver != null){
            delegateTask.addCandidateUser(approver.toString());
        }else{
            List<Employee> boss = null;
            try {
                boss = employeeService.findByRoleName("副秘书长");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(boss !=null){
                for (int i = 0 ;i<boss.size();i++){
                    delegateTask.addCandidateUser(boss.get(i).getRealName());
                }
            }
        }
    }
}

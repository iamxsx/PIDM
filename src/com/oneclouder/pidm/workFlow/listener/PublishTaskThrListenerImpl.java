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
 * Created by xucb on 16-9-29.
 */
public class PublishTaskThrListenerImpl implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("PublishTaskThrListenerImpl!");
        //设置参加审核的人员
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        IEmployeeService employeeService = applicationContext.getBean("employeeService",IEmployeeService.class);
        Object approver = delegateTask.getExecution().getVariable("zhubian");
        if(approver == null){
            List<Employee> boss = null;
            try {
                boss = employeeService.findByRoleName("主编");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(boss !=null){
                for (int i = 0 ;i<boss.size();i++){
                    delegateTask.addCandidateUser(boss.get(i).getRealName());
                }
            }
        }else{
            delegateTask.addCandidateUser(approver.toString());
        }
    }
}

package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.article.service.IArticleService;
import com.oneclouder.pidm.article.service.impl.ArticleServiceImpl;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.workFlow.model.GetApplicationContext;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import com.oneclouder.pidm.workFlow.service.Impl.WorkFlowServiceImpl;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xucb on 16-9-11.
 */
public class PublishEndListenerImpl implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("PublishEndListenerImpl!");
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        Map<String,Object> params = new HashMap<String,Object>();
        String businessKey = delegateExecution.getBusinessKey();
        IArticleService articleService = applicationContext.getBean("articleService", ArticleServiceImpl.class);
        IWorkFlowService workFlowServiceImpl = applicationContext.getBean("workFlowService", WorkFlowServiceImpl.class);
        String aid = workFlowServiceImpl.findIdByBusinessKey(businessKey);
        String status = "3";
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String approver = employee.getRealName();
        params.put("aid",aid);
        params.put("status",status);
        params.put("approver",approver);
        articleService.setStatusByArticleId(params);
        articleService.setApprover(params);
    }
}

package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.article.service.IArticleService;
import com.oneclouder.pidm.article.service.impl.ArticleServiceImpl;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.workFlow.model.GetApplicationContext;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import com.oneclouder.pidm.workFlow.service.Impl.WorkFlowServiceImpl;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xucb on 16-9-11.
 */
public class PublishStartListenerImpl implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("PublishStartListenerImpl!");
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        Map<String,Object> params = new HashMap<String,Object>();
        String businessKey = delegateTask.getExecution().getBusinessKey();
        IArticleService articleService = applicationContext.getBean("articleService", ArticleServiceImpl.class);
        IWorkFlowService workFlowServiceImpl = applicationContext.getBean("workFlowService", WorkFlowServiceImpl.class);
        String aid = workFlowServiceImpl.findIdByBusinessKey(businessKey);
        String status = "1";
        String approver = articleService.findArticleById(Integer.valueOf(aid)).getAuthor().getRealName();
        params.put("aid",aid);
        params.put("status",status);
        params.put("approver",approver);
        articleService.setApprover(params);
        articleService.setStatusByArticleId(params);
    }
}

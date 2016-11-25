package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.user.service.IUserTemporaryService;
import com.oneclouder.pidm.user.service.impl.UserTemporaryImpl;
import com.oneclouder.pidm.workFlow.model.GetApplicationContext;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import com.oneclouder.pidm.workFlow.service.Impl.WorkFlowServiceImpl;
import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xucb on 16-9-10.
 */
public class EndListenerImpl implements ExecutionListener {

    private HttpSession session;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("EndListenerImpl!");
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        //获取工作流的businesskey
        String businessKey = delegateExecution.getBusinessKey();
        //获取流程实例id
        String processInstanceId = delegateExecution.getProcessInstanceId();
        IWorkFlowService workFlowServiceImpl = applicationContext.getBean("workFlowService", WorkFlowServiceImpl.class);
        INUserService nUserService = applicationContext.getBean("nUserService",INUserService.class);
        HistoryService historyService = applicationContext.getBean("historyService", HistoryService.class);
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .taskDefinitionKey("usertask2")
                .orderByTaskCreateTime()
                .desc()
                .list();
        //从工作流的businesskey中获取业务id
        String uid = workFlowServiceImpl.findIdByBusinessKey(businessKey);
        //从工作流的businesskey中获取业务类型type
        String type = workFlowServiceImpl.findTypeByBusinessKey(businessKey);
        //查询出角色“会员”的role id  会员
        int roleId = workFlowServiceImpl.selectRoleId("会员");
        System.out.println(roleId);
        //审核状态 3为审核通过
        String status = "3";
        //根据临时表找到正式表的用户Id
        int i = workFlowServiceImpl.selectUserIdByAccount(Integer.parseInt(uid));
        Map<String,Object> params = new HashMap<String,Object>();
        Map<String,Object> params2 = new HashMap<String,Object>();
        //协会意见 传入taskid
        String message = workFlowServiceImpl.findMessage(list.get(0).getId());
        int compID = workFlowServiceImpl.selectCompID(i);
        int u = Integer.parseInt(uid);
        int tempCompID = workFlowServiceImpl.selectTEMPCompID(u);
        //修改临时表的审核状态
        params2.put("uid",uid);
        params2.put("status",status);
        params2.put("message",message);
        params2.put("companyId",tempCompID);

        //修改正式表的审核状态
        params.put("uid",i);
        params.put("status",status);
        params.put("message",message);
        params.put("companyId",compID);
        params.put("userRole",roleId);//角色id

        if("Insert".equals(type)){
            //修改临时表的审核状态
            workFlowServiceImpl.setStatusByUserTEMId(params2);
            workFlowServiceImpl.setMessageByTEMPUserId(params2);
            //修改正式表的审核状态
            workFlowServiceImpl.setStatusByUserId(params);
            workFlowServiceImpl.setMessageByUserId(params);
            //如果是用户注册，给予用户赋予用户角色为正式会员
            workFlowServiceImpl.setUserRole(params);
        }else if("Update".equals(type)){
            //修改临时表的审核状态
            workFlowServiceImpl.setStatusByUserTEMId(params2);
            workFlowServiceImpl.setMessageByTEMPUserId(params2);
        }
        //如果审核通过的话，把临时表中的信息复制到正式表中
        nUserService.saveUserAllInfoFromTemp(Integer.parseInt(uid));
    }
}

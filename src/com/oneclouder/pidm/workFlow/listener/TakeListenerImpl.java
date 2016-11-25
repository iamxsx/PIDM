package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.user.model.UserTemporary;
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
 * Created by clouder on 16-9-10.
 */
public class TakeListenerImpl implements ExecutionListener {

    private HttpSession session;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("TakeListenerImpl!");
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        //获取工作流的businessKey
        String businessKey = delegateExecution.getBusinessKey();
        //获取流程实例id
        String processInstanceId = delegateExecution.getProcessInstanceId();
        IWorkFlowService workFlowServiceImpl = applicationContext.getBean("workFlowService", WorkFlowServiceImpl.class);
        IUserTemporaryService userTemporaryService = applicationContext.getBean("userTemporaryService",IUserTemporaryService.class);
        HistoryService historyService = applicationContext.getBean("historyService", HistoryService.class);
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .taskDefinitionKey("usertask2")
                .orderByTaskCreateTime()
                .desc()
                .list();
        //获取副秘书长角色的办理人
        String assignee = list.get(list.size()-1).getAssignee();
        //设置工作流变量
        delegateExecution.setVariable("assignee",assignee);
        //根据businessKey来获取业务id
        String uid = workFlowServiceImpl.findIdByBusinessKey(businessKey);
        //根据businesskey来获取业务类型type
        String type = workFlowServiceImpl.findTypeByBusinessKey(businessKey);
        //查询出角色“会员”的role id 非会员
        int roleId = workFlowServiceImpl.selectRoleId("非会员");
        System.out.println(roleId);
        //审核状态 2为审核不通过
        String status = "2";
        //根据临时表中信息来获取正式表中用户的id
        int i = workFlowServiceImpl.selectUserIdByAccount(Integer.parseInt(uid));
        Map<String,Object> params = new HashMap<String,Object>();
        Map<String,Object> params2 = new HashMap<String,Object>();
        //协会意见 传入taskid
        String message = workFlowServiceImpl.findMessage(list.get(0).getId());
        int compID = workFlowServiceImpl.selectCompID(i);
        int u = Integer.parseInt(uid);
        int tempCompID = workFlowServiceImpl.selectTEMPCompID(u);
        //修改临时表中的审核状态
        params2.put("uid",uid);
        params2.put("status",status);
        params2.put("message",message);
        params2.put("companyId",tempCompID);
        //修改正式表中的审核状态
        params.put("uid",i);
        params.put("status",status);
        params.put("message",message);
        params.put("companyId",compID);
        params.put("userRole",roleId);//这里放的是roleId

        //如果业务类型是insert，则修改正式表和临时表;如果业务类型是Update，则修改临时表
        if("Insert".equals(type)){
            //修改正式表中的审核状态
            workFlowServiceImpl.setStatusByUserId(params);
            workFlowServiceImpl.setMessageByUserId(params);
            //修改临时表中的审核状态
            workFlowServiceImpl.setStatusByUserTEMId(params2);
            workFlowServiceImpl.setMessageByTEMPUserId(params2);
            //如果是用户注册，修改正式表中用户的用户角色为非正式会员
            workFlowServiceImpl.setUserRole(params);
        }else if("Update".equals(type)){
            //修改临时表中的审核状态
            workFlowServiceImpl.setStatusByUserTEMId(params2);
            workFlowServiceImpl.setMessageByTEMPUserId(params2);
        }

        //从临时表中找到当前的用户名，把其设置成工作流中的变量
        String account = workFlowServiceImpl.selectTUserAccount(Integer.parseInt(uid));
        delegateExecution.setVariable("user",account);
    }
}

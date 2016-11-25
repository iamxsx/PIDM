package com.oneclouder.pidm.workFlow.listener;

import com.oneclouder.pidm.user.service.IUserTemporaryService;
import com.oneclouder.pidm.workFlow.model.GetApplicationContext;
import com.oneclouder.pidm.workFlow.service.IWorkFlowService;
import com.oneclouder.pidm.workFlow.service.Impl.WorkFlowServiceImpl;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class StartListenerImpl implements TaskListener {

    //private HttpSession session;

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("StartListenerImpl!");
        GetApplicationContext getApplicationContext = new GetApplicationContext();
        WebApplicationContext applicationContext = getApplicationContext.getApplicationContext();
        System.out.println(delegateTask);
        DelegateExecution e = delegateTask.getExecution();
        if(e==null){
            System.out.println(e);
        }
        //获取工作流的businessKey
        String businessKey = e.getProcessBusinessKey();
        IWorkFlowService workFlowServiceImpl = applicationContext.getBean("workFlowService", WorkFlowServiceImpl.class);
        IUserTemporaryService userTemporaryService = applicationContext.getBean("userTemporaryService",IUserTemporaryService.class);
        //根据工作流的businessKey获取业务id
        String uid = workFlowServiceImpl.findIdByBusinessKey(businessKey);
        //根据工作流的businesskey获取业务类型type
        String type = workFlowServiceImpl.findTypeByBusinessKey(businessKey);
        //审核状态 1为审核中
        String status = "1";
        //根据临时表中的id找到正式表相应的id
        int i = workFlowServiceImpl.selectUserIdByAccount(Integer.parseInt(uid));
        Map<String,Object> params = new HashMap<String,Object>();
        Map<String,Object> params2 = new HashMap<String,Object>();
        //修改临时表中的状态
        params2.put("uid",uid);
        params2.put("status",status);
        //修改正式表中的状态
        params.put("uid",i);
        params.put("status",status);

        //如果业务类型是insert，则修改正式表和临时表;如果业务类型是Update，则修改临时表
        if("Insert".equals(type)){
            //修改正式表的的状态
            workFlowServiceImpl.setStatusByUserId(params);
            //修改临时表中的状态
            workFlowServiceImpl.setStatusByUserTEMId(params2);
        }
        else if("Update".equals(type)){
            //修改临时表中的状态
            workFlowServiceImpl.setStatusByUserTEMId(params2);
        }

    }
}

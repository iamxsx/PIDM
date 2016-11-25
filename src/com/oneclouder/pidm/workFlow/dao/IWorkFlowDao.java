package com.oneclouder.pidm.workFlow.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by xucb on 16-9-13.
 */
@MyBatisRepository
@Repository("workFlowDao")
@Lazy
public interface IWorkFlowDao {
    /**
     * 修改前台用户的状态
     */
    public void setStatusByUserId(Map<String,Object> params);

    /**
     * 修改前台用户的审核人
     */
    public void setApprover(Map<String,Object> params);

    /**
     * 修改前台临时用户的状态
     */
    public void setStatusByUserTEMId(Map<String,Object> params);

    /**
     * 修改前台临时用户的审核人
     */
    public void setApproverTME(Map<String,Object> params);

    public void setRegisterstatus(int id);

    public String findMessage(String taskid);

    public void setMessageByUserId(Map<String,Object> params);

    public void setMessageByTEMPUserId(Map<String,Object> params);

    public void setUserRole(Map<String,Object> params);

    public int selectRoleId(String roleName);

    public String selectTUserAccount(Integer userId);

    public int selectUserId(String account);

    public int selectCompID(int uid);

    public int selectTEMPCompID(int uid);

}

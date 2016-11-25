package com.oneclouder.pidm.user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.user.action.ChangeUserInfo;
import com.oneclouder.pidm.user.model.Description;
import com.oneclouder.pidm.user.model.UserTemporary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zheng.
 */
public interface IUserTemporaryService extends IBaseService<UserTemporary>{

    public void updateTemporaryToUser (Integer userTemporaryId);

    public Integer ifcompanyBeHad (Integer userTemporaryId);

    public int userIdByUserTemporaryId(Integer userTemporaryId);

    UserTemporary findUserTemporaryById(Integer userTemporaryId);
    //更新描述表
    public void updateDescription(Description description);
}

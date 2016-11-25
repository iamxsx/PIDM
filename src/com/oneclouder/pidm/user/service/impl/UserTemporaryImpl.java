package com.oneclouder.pidm.user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.user.dao.IUserTemporaryDao;
import com.oneclouder.pidm.user.model.Description;
import com.oneclouder.pidm.user.model.UserTemporary;
import com.oneclouder.pidm.user.service.IUserTemporaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zheng.
 */
@Service("userTemporaryService")
public class UserTemporaryImpl extends BaseServiceImpl<UserTemporary> implements IUserTemporaryService{

    @Resource(name = "userTemporaryDao")
    IUserTemporaryDao userTemporaryDao;

    //前台用户修改个人信息 工作流通过 后调用 更改用户信息
    @Override
    public void updateTemporaryToUser(Integer userTemporaryId) {
        UserTemporary userTemporary = userTemporaryDao.findUserTemporaryById(userTemporaryId);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("realName",userTemporary.getRealName());
        params.put("phoneNum",userTemporary.getPhoneNum());
        params.put("IDcard",userTemporary.getIDcard());
        params.put("email",userTemporary.getEmail());
        params.put("designatedContact",userTemporary.getDesignatedContact().getId());
        //通过临时表的帐号 获得用户ID
        int uId = userTemporaryDao.findUIdByTemporaryAccount(userTemporary.getAccount());

        params.put("uId",uId);
        //user修改信息更改 ，在userTemporaryDao 里面执行  避免冲突
        userTemporaryDao.updateTemporaryToUser(params);
    }

    @Override
    public int userIdByUserTemporaryId(Integer userTemporaryId) {
        UserTemporary userTemporary = userTemporaryDao.findUserTemporaryById(userTemporaryId);
        int uId = userTemporaryDao.findUIdByTemporaryAccount(userTemporary.getAccount());
        return uId;
    }

    @Override
    public UserTemporary findUserTemporaryById(Integer userTemporaryId) {
        return userTemporaryDao.findUserTemporaryById(userTemporaryId);
    }
    //更新描述表
    @Override
    public void updateDescription(Description description) {
        userTemporaryDao.updateDescription(description);
    }

    //判断用户注册企业是不是已有企业 通过companyId查找用户条数 来判断
    @Override
    public Integer ifcompanyBeHad(Integer userTemporaryId) {
        UserTemporary userTemporary = userTemporaryDao.findUserTemporaryById(userTemporaryId);
        Integer companyId = userTemporary.getCompany().getId();
        Integer count =  userTemporaryDao.findCountOfCompany(companyId);
        if(count<2){
            return 0;  //0表示新注册企业
        }else {
            return 1;  //1表示已有企业
        }
    }
}

package com.oneclouder.pidm.user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.user.model.Description;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.model.UserTemporary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by zheng.
 */
@MyBatisRepository
@Repository("userTemporaryDao")
public interface IUserTemporaryDao extends IBaseDao<UserTemporary>{
    public Integer insertById(User user);
    //通过ID 查找临时表里面的用户
    UserTemporary findUserTemporaryById(@Param("userTemporaryId") Integer userTemporaryId);
    //获得有这个公司ID的用户有多少个来判断 是不是新注册到公司
    int findCountOfCompany (@Param("companyId") Integer companyId);
    //更新临时表的信息到 用户表里
    void updateTemporaryToUser(Map<String, Object> params);

    int findUIdByTemporaryAccount(@Param("account") String account);
    //更新描述表
    void updateDescription(Description description);
}

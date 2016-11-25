package com.oneclouder.pidm.user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.webBean.UserSimpleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 10:05 AM
 */
@MyBatisRepository
@Repository("userDao")
public interface IUserDao extends IBaseDao<User> {

    public List<UserSimpleInfo> getUserSimpleInfo(UserSimpleInfo userSimpleInfo);

    public List<UserSimpleInfo> getUserSimpleInfoLimit(UserSimpleInfo userSimpleInfo);

    public User getUserInfoEle(Integer id);

    public void changeUserInfo(Map<String, Object> map);

    public void deleteUserInfo(Integer uid);


    public void getUserInfoById(@Param("user_id") Integer user_id);


    public Integer userInfoNum(UserSimpleInfo userSimpleInfo);

    public Integer judgeBelong(Integer id);

    public void changeUser(Map map);

    Integer isWriteSurvey(Integer id);

    void updataUserSurveyId(Map map);

    void updataSurveyId();

    /**
     * 批量插入推荐表信息
     * @param map
     */
    void insertIntroduceds(Map<String, Object> map) throws SQLException;

    /**
     * 匹配验证码
     * @param code
     * @return
     */
    Integer verifyCode(String code) throws SQLException;

    /**
     * 根据id激活用户
     * @param userId
     */
    void active(Integer userId) throws SQLException;

    /**
     * 根据账户得到盐
     * @param account
     * @return
     * @throws SQLException
     */
    String findSaltByAccount(String account) throws SQLException;

    /**
     * 根据账户和密码得到用户
     * @param map
     * @return
     */
    User findByAccountAndPassword(Map<String, String> map) throws SQLException;

    /**
     * 根据帐号 插入verifycode
     * @param map
     * @throws SQLException
     */
    void insertVerifycode(Map<String, String> map) throws SQLException;

    /**
     * 查询帐号数目
     * @param account
     * @return
     */
    Integer findAccountNum(String account) throws SQLException;

    Integer updatePasswordByCondition(User user) throws SQLException;

    void insertUser(User user);
}

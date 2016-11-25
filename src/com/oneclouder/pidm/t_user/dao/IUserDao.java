package com.oneclouder.pidm.t_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.t_user.model.User_t;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@MyBatisRepository
@Repository("userDao_t")
public interface IUserDao extends IBaseDao<User_t> {


    public User_t getUserInfoEle(Integer id);

    public void changeUserInfo(Map<String, Object> map);

    public void deleteUserInfo(Integer uid);


    public void getUserInfoById(@Param("user_id") Integer user_id);


    public Integer userInfoNum();

    public Integer judgeBelong(Integer id);

    public void changeUser(Map map);

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
    User_t findByAccountAndPassword(Map<String, String> map) throws SQLException;

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

}

package com.oneclouder.pidm.user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.user.model.AssociationUnit;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.webBean.RegisterFormBean;
import com.oneclouder.pidm.user.webBean.UserSimpleInfo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/11/16
 * Time: 10:03 AM
 */
public interface IUserService extends IBaseService<User> {
//    public void getUserSimpleInfo(UserSimpleInfo userSimpleInfo, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    public List<UserSimpleInfo> getUserSimpleInfoLimit(UserSimpleInfo userSimpleInfo);

    public User getUserInfoEle(Integer id);

    public Integer changeUserInfo(String[] InfoList);

    public void deleteUserInfo(Integer uid);

    public Integer userInfoNum(UserSimpleInfo userSimpleInfo);

    public Integer isWriteSurvey(Integer id);

    public void updataUserSurveyId(Integer surveyId,Integer id);

    public void updataSurveyId();

    /**
     * 注册新用户
     * @param user
     * @param map
     * @return
     * @throws SQLException
     */
    Integer registerUser(User user, Map<String, Object> map, HttpSession session) throws SQLException;

    /**
     * 验证邮箱验证码是否一致,得到用户id
     * @param code
     * @return
     */
    Integer verifyCode(String code) throws SQLException;

    /**
     * 检测用户是否重复激活
     * @return true 激活 flase 未激活
     */
    boolean isActive(Integer userId) throws SQLException;

    boolean isExist(String account) throws SQLException;

    /**
     * 激活用户邮箱
     * @param userId
     */
    void active(Integer userId) throws SQLException;

    /**
     * 帐号/密码 登录
     * @param account
     * @param password
     * @return
     * @throws SQLException
     */
    User login(String account, String password) throws SQLException;

    /**
     * 设置帐号验证码
     * @param account
     * @param verifycode
     * @throws SQLException
     */
    void addVerifycode(String account, String verifycode) throws SQLException;

    /**
     * 重置密码
     * @param user
     * @return
     */
    Integer resetPassword(User user) throws SQLException;

    Integer register(RegisterFormBean registerFormBean, HttpSession session) throws SQLException;

    public void exportPDF(List<Object> Info, HttpServletResponse response);
}

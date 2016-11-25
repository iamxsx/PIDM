package com.oneclouder.pidm.n_user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.web_bean.RegisterFormBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:49
 */
public interface INUserService extends IBaseService<NUser> {
    //--------------------------------成员变量----------------------------------


    //--------------------------------hxj----------------------------------
    List<Map> getUserSimpleInfoLimit(Map map);

    public void getUserSimpleInfo(Integer searchCondition, String keyWord, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    void deleteUserInfo(Integer uid);

    List<Map> selectClientAll(Integer uid);

    void exportPDF(HttpServletResponse response, Integer uid);

    void updateUserInfo(NUser nUser);

    void addClient(RegisterFormBean formBean) throws SQLException;

    void changeClientInfo(RegisterFormBean changeInfo,Integer desNum,Integer introNum) throws SQLException;

    List<Map> selectTUserAll(Integer uid);

    void changeUserCheck(RegisterFormBean registerFormBean,HttpServletRequest request, String location, Integer userId) throws Exception;

    void updataUserSurveyId(Integer surveyId,Integer id);

    void updataSurveyId();

    Integer isWriteSurvey(Integer id);


    //--------------------------------zcb----------------------------------

    /**
     * 插用户信息到 临时表
     **/
    public Integer insertToNTempUser(NUser nUser);

    /**
     * 插用户所有信息 到临时表
     *
     * @param formBean
     * @return
     */
    public Integer saveNUserAllInfoToTemp(RegisterFormBean formBean,HttpServletRequest request) throws Exception;

    /**
     * 工作流审核通过 信息从临时表 copy 到正式表
     */
    public void saveUserAllInfoFromTemp(Integer nUserId) throws SQLException;
    //--------------------------------xucb---------------------------------

    /**
     * 根据临时表用户Id查找出用户信息
     *
     * @param uid
     * @return
     */
    public NUser findTempUserById(Integer uid) throws SQLException;

    /**
     * 根据临时表用户Id查询临时资源表中文件路径
     * @param uid
     * @return
     * @throws SQLException
     */
    public String findTempUrl(Integer uid) throws SQLException;

    //--------------------------------zjf----------------------------------

    /**
     * Created By IntelliJ IDEA
     * 查询账号是否存在
     *
     * @param account 账号
     * @return boolean true存在 false不存在
     * @Author: AngryFeng
     * @Date: 16-10-16 下午12:22
     */
    boolean isExist(String account);

    /**
     * Created By IntelliJ IDEA
     * 注册
     *
     * @param formBean 封装注册数据的bean
     * @param request
     * @return userId 插入的用户id
     * @throws SQLException
     * @Author: AngryFeng
     * @Date: 16-10-16 下午12:23
     */
    Integer register(RegisterFormBean formBean, HttpServletRequest request) throws Exception;

    /**
     * Created By IntelliJ IDEA
     * 封装user的companyId 插入数据库(临时表和正式表)
     *
     * @param cpmpanyId  单位id
     * @param tCpmpanyId 临时表单位id
     * @param user       用户
     * @return userIds key:userId/tUserId value：正式表和临时表的id
     * @throws SQLException
     * @Author: AngryFeng
     * @Date: 16-10-16 下午4:27
     */
    Map<String, Integer> creatUser(Integer cpmpanyId, Integer tCpmpanyId, NUser user) throws Exception;

    /**
     * Created By IntelliJ IDEA
     * 根据验证码查询出user
     *
     * @param code 邮箱验证码
     * @return user
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 上午8:28
     */
    NUser verifyCode(String code);

    /**
     * Created By IntelliJ IDEA
     * 判断邮箱是否已验证
     *
     * @param userId 用户id
     * @return boolean
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 上午8:29
     */
    boolean isActive(Integer userId) throws SQLException;

    /**
     * Created By IntelliJ IDEA
     * 激活账号
     *
     * @param userId 用户id
     * @return row 影响行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 上午8:46
     */
    Integer active(Integer userId);

    /**
     * Created By IntelliJ IDEA
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return user 用户
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 上午10:28
     */
    NUser login(String account, String password);

    /**
     * Created By IntelliJ IDEA
     * 将验证码添加到user中
     *
     * @param account    账号
     * @param verifycode 验证码
     * @return row 影响的行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 下午11:23
     */
    Integer addVerifycode(String account, String verifycode);


    /**
     * Created By IntelliJ IDEA
     * 根据账号和邮箱查找是否user存在
     *
     * @param account
     * @param email
     * @return boolean
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 下午11:28
     */
    boolean isExist(String account, String email);

    /**
     * Created By IntelliJ IDEA
     * 根据账号查询user
     *
     * @param account 账号
     * @return NUser 用户
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午8:15
     */
    NUser findByAccount(String account);

    /**
     * Created By IntelliJ IDEA
     * 判断验证码是否匹配
     *
     * @param account    账号
     * @param email      邮箱
     * @param verifycode 验证码
     * @return pass true：验证通过 false：失败
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午8:55
     */
    boolean isVerifyPass(String account, String email, String verifycode);

    /**
     * Created By IntelliJ IDEA
     * 重置密码
     *
     * @param account  账号
     * @param password 密码
     * @return row 影响行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午10:01
     */
    Integer resetPassword(String account, String password);

    /**
     * Created By IntelliJ IDEA
     * 激活账号 开启工作流
     *
     * @param user
     * @return row 影响行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-19 下午2:55
     */
    Integer activeAccount(NUser user);
}

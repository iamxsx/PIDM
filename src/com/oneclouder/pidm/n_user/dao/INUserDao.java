package com.oneclouder.pidm.n_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;
import com.oneclouder.pidm.n_user.model.NUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:48
 */
@MyBatisRepository
@Repository("nUserDao")
public interface INUserDao extends IBaseDao<NUser> {
    //--------------------------------成员变量----------------------------------


    //--------------------------------hxj----------------------------------
    List<Map> getUserSimpleInfoLimit(Map map);

    void deleteUserInfo(Integer uid);

    List<Map> selectClientAll(Integer uid);

    void updateUserInfo(NUser nUser);

    void insertUser(NUser nUser);

    void updataUserSurveyId(Map map);

    void updataSurveyId();

    Integer isWriteSurvey(Integer id);

    //--------------------------------zcb----------------------------------

    /**
     * 插用户信息到 临时表
     **/
    Integer insertToNTempUser(NUser nUser);

    //--------------------------------zjf----------------------------------

    /**
     * Created By IntelliJ IDEA
     * 统计账户数量
     *
     * @param account 账号
     * @return Integer
     * @Author: AngryFeng
     * @Date: 16-10-16 下午5:41
     */
    Integer findAccountNum(String account);

    /**
     * Created By IntelliJ IDEA
     * 根据邮箱验证码查找user
     *
     * @param code 验证码
     * @return userId 用户id
     * @Author: AngryFeng
     * @Date: 16-10-17 上午9:12
     */
    NUser findByCode(String code);

    /**
     * Created By IntelliJ IDEA
     * 根据id 修改user的status 激活账号
     *
     * @param userId 用户id
     * @return row 影响行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 上午9:24
     */
    Integer active(Integer userId);

    /**
     * Created By IntelliJ IDEA
     * 根据账号查找user密码的salt
     *
     * @param account 账号
     * @return salt 密码的盐
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 上午10:34
     */
    String findSaltByAccount(String account);

    /**
     * Created By IntelliJ IDEA
     * 根据账号和密码 查找user
     *
     * @param map 存放账号和密码 key:account/password value:账号/密码
     * @return
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 上午10:37
     */
    NUser findByAccountAndPassword(Map<String, String> map);

    /**
     * Created By IntelliJ IDEA
     * 根据账号和邮箱查询user的记录数
     *
     * @param params key:account/email value:账号/邮箱
     * @return count 记录数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 下午11:32
     */
    Integer findByAccountAndEmail(Map<String, Object> params);


    /**
     * Created By IntelliJ IDEA
     * 根据账号,插入邮箱验证码
     *
     * @param params key:account/verifycode value:账号/邮箱验证码
     * @return row 影响行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-17 下午11:38
     */
    Integer updateVerifycode(Map<String, Object> params);

    /**
     * Created By IntelliJ IDEA
     * 根据账号查询user
     *
     * @param account 账号
     * @return NUser 用户
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午8:17
     */
    NUser findByAccount(String account);

    /**
     * Created By IntelliJ IDEA
     * 根据账号+邮箱+验证码统计user记录数
     *
     * @param params key:account/email/verifycode value:账号/邮箱/邮箱验证码
     * @return count 记录数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午9:01
     */
    Integer findNumByAccoAndEmailAndVcode(Map<String, Object> params);

    /**
     * Created By IntelliJ IDEA
     * 根据账号更改密码
     *
     * @param params key:account/password/salt value:账号/密码/盐
     * @return row 影响行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 上午10:33
     */
    Integer updatePassword(Map<String, Object> params);

    /**
     * Created By IntelliJ IDEA
     * 验证邮箱验证码，并置空验证码
     * @param params key:account/email/verifycode value:账号/邮箱/邮箱验证码
     * @return row 影响行数
     * @throws
     * @Author: AngryFeng
     * @Date: 16-10-18 下午2:41
     */
    Integer updateCodeByAccoAndEmailAndVcode(Map<String, Object> params);
}

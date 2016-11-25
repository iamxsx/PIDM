package com.oneclouder.pidm.n_user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.n_user.model.NCompany;

import java.sql.SQLException;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:49
 */
public interface INCompanyService extends IBaseService<NCompany> {
    //--------------------------------成员变量----------------------------------


    //--------------------------------hxj----------------------------------
    void updateCompanyInfo(NCompany ncompany);


    //--------------------------------zcb----------------------------------

    /**
     * 插公司信息到 临时表
     **/
    Integer insertToNTempCompany(NCompany nCompany);


    //--------------------------------zjf----------------------------------

    /**
     * Created By IntelliJ IDEA
     * 检测单位是否注册
     *
     * @param companyName 单位名称
     * @return boolean true:存在 false：不存在
     * @Author: AngryFeng
     * @Date: 16-10-16 上午11:50
     */
    boolean isExist(String companyName);

    /**
     * Created By IntelliJ IDEA
     * 封装companyId 添加company
     *
     * @param company 单位对象
     * @return companyId 插入的单位id
     * @throws SQLException
     * @Author: AngryFeng
     * @Date: 16-10-16 上午11:50
     */
    Integer creatCompany(NCompany company) throws SQLException;
}

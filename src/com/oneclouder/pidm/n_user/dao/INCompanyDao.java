package com.oneclouder.pidm.n_user.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.n_user.model.NCompany;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:49
 */
@MyBatisRepository
@Lazy
@Repository("nCompanyDao")
public interface INCompanyDao extends IBaseDao<NCompany> {
    //--------------------------------成员变量----------------------------------





    //--------------------------------hxj----------------------------------
    void updateCompanyInfo(NCompany ncompany);


    //--------------------------------zcb----------------------------------
    /**插公司信息到 临时表**/
    Integer insertToNTempCompany(NCompany nCompany);


    //--------------------------------zjf----------------------------------
    /**
     * 根据单位名称统计数目
     * @param companyName
     * @return
     */
    Integer findComNumByName(String companyName);
}

package com.oneclouder.pidm.n_user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.n_user.dao.INCompanyDao;
import com.oneclouder.pidm.n_user.dao.INTCompanyDao;
import com.oneclouder.pidm.n_user.dao.INUserDao;
import com.oneclouder.pidm.n_user.model.NCompany;
import com.oneclouder.pidm.n_user.model.NUser;
import com.oneclouder.pidm.n_user.service.INCompanyService;
import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.util.PinYinUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午9:00
 */
@Service("nCompanyService")
public class NCompanyServiceImpl extends BaseServiceImpl<NCompany> implements INCompanyService {
    //--------------------------------成员变量----------------------------------
    private INCompanyDao nCompanyDao;

    @Resource(name = "nCompanyDao")
    public void setDao(INCompanyDao nCompanyDao) {
        super.setDao(nCompanyDao);
        this.nCompanyDao = nCompanyDao;
    }

    @Resource(name = "nTCompanyDao")
    private INTCompanyDao nTCompanyDao;


    //--------------------------------hxj----------------------------------


    @Override
    @Transactional
    public void updateCompanyInfo(NCompany ncompany) {
        nCompanyDao.updateCompanyInfo(ncompany);
    }

    //--------------------------------zcb----------------------------------
    @Override
    /**插公司信息到 临时表**/
    public Integer insertToNTempCompany(NCompany nCompany) {
        return nCompanyDao.insertToNTempCompany(nCompany);
    }


    //--------------------------------zjf----------------------------------
    @Override
    public boolean isExist(String companyName) {
        Integer count = nCompanyDao.findComNumByName(companyName);
        //查询的数目大于0 说明已存在
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Integer creatCompany(NCompany company) throws SQLException {
        String identifiter = genCompanyIdentifiter(company.getName());
        String registerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        company.setIdentifier(identifiter);
        company.setRegisterTime(registerTime);
        nCompanyDao.insert(company);
        return company.getId();
    }

    /**
     * Created By IntelliJ IDEA
     * 根据单位名称生成单位编号
     * @Author: AngryFeng
     * @Date: 16-10-16 下午5:19
     * @param
     * @throw
     * @return String "名称首字母-日期-四位随机数"
     */
    public static String genCompanyIdentifiter(String companyName) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        int day = calendar.get(Calendar.DATE);//获取日
        int hour = calendar.get(Calendar.HOUR);//小时
        String midfix = new String("" + month + day + hour);
        Random rand = new Random();
        int suffix = rand.nextInt(999)+1000;
        String pinyin = PinYinUtil.converterToFirstSpell(companyName);
        return (pinyin.toUpperCase() + "-" + midfix + "-" + suffix);
    }
}

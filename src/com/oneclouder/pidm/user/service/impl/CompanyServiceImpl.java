package com.oneclouder.pidm.user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.user.dao.ICompanyDao;
import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.service.ICompanyService;
import com.oneclouder.pidm.util.PinYinUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/10/16
 * Time: 4:30 PM
 */
@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements ICompanyService{
    private ICompanyDao companyDao;

    @Resource(name = "companyDao")
    public void setDao(ICompanyDao companyDao) {
        super.setDao(companyDao);
        this.companyDao = companyDao;
    }

    @Override
    public boolean companyIsExist(String companyName) {
        int count = 0;
        count = companyDao.accountCompanyByName(companyName);
        if(count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer createCompanny(Company company) throws SQLException {
        //公司编号：  利用工具类将公司名字首拼音抽取出来作为编号
        String identifiter = genCompanyIdentifiter(company.getName());
        company.setIdentifier(identifiter.toUpperCase());
        companyDao.insert(company);
        return company.getId();
    }

    @Override
    public Company findCompanyByName(String companyName) throws SQLException {
        return companyDao.findCompanyByName(companyName);
    }

    @Override
    public List<Company> findAllCompanies() throws SQLException {
        return companyDao.findAllCompanies();
    }

    /**
     * 根据用户名信息生成公司编号
     * @param companyName
     * @return
     */
    public static String genCompanyIdentifiter(String companyName) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        int day = calendar.get(Calendar.DATE);//获取日
        int hour = calendar.get(Calendar.HOUR);//小时
        String midfix = new String("" + month + day + hour);
        int suffix = Math.abs(midfix.hashCode());
        String pinyin = PinYinUtil.converterToFirstSpell(companyName);
        return (pinyin.toUpperCase() + "-" + midfix + "-" + suffix);
    }
}

package com.oneclouder.pidm.regManage.service.Impl;

import com.oneclouder.pidm.regManage.dao.IRegManageDao;
import com.oneclouder.pidm.regManage.model.RegManage;
import com.oneclouder.pidm.regManage.model.Usermessage;
import com.oneclouder.pidm.regManage.service.IRegManage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xucb on 16-9-19.
 */
@Service("regService")
public class RegManageImpl implements IRegManage {

    @Resource(name = "regManageDao")
    private IRegManageDao regManageDao;

    @Override
    public List<RegManage> selectRegMessage(RegManage regManage) {
        List<RegManage> regManages = null;
        try {
            regManages = regManageDao.selectRegMessage(regManage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regManages;
    }

    @Override
    public int selectRegMessageCount(RegManage regManage) {
        int count = 0;
        try {
            count = regManageDao.selectRegMessageCount(regManage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Usermessage> findId(RegManage regManage) {
        List<Usermessage> list = new ArrayList<>();
        try {
            list = regManageDao.findId(regManage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Usermessage> findAllCount(Integer i,Integer offset,Integer limit){
        List<Usermessage> allCount = null;
        Usermessage usermessage = new Usermessage();
        usermessage.setLimit(limit);
        usermessage.setOffset(offset);
        usermessage.setId(i);
        try {
            allCount = regManageDao.findAllCount(usermessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCount;
    }

    @Override
    public int findAll(Integer i,Integer offset,Integer limit) {
        Usermessage usermessage = new Usermessage();
        usermessage.setLimit(limit);
        usermessage.setOffset(offset);
        usermessage.setId(i);
        int allCount = 0;
        try {
            allCount = regManageDao.findAll(usermessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCount;
    }

    /**
     * 根据id查询RegManage
     *
     * @param id
     * @return
     */
    @Override
    public Usermessage findById(Integer id) {
        return regManageDao.findById(id);
    }

    @Override
    public void changeAccountInfo(Usermessage usermessage) {
        regManageDao.updateUser(usermessage);
    }

    @Override
    public void deleteId(int id) {
        regManageDao.deleteId(id);
    }

    @Override
    public int findStatus(int id) {
        return regManageDao.findStatus(id);
    }

    @Override
    public int selectCompName(int id) {
        return regManageDao.selectCompName(id);
    }

    @Override
    public String selectAccountByCompId(int id) {
        return regManageDao.selectAccountByCompId(id);
    }
}

package com.oneclouder.pidm.regManage.service;

import com.oneclouder.pidm.regManage.model.RegManage;
import com.oneclouder.pidm.regManage.model.Usermessage;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by xucb on 16-9-19.
 */
public interface IRegManage {
    public List<RegManage> selectRegMessage(RegManage regManage);
    public int selectRegMessageCount(RegManage regManage);
    public List<Usermessage> findId(RegManage regManage);
    public List<Usermessage> findAllCount(Integer i,Integer offset,Integer limit);
    public int findAll(Integer i,Integer offset,Integer limit);

    /**
     * 根据id查询RegManage
     * @param id
     * @return
     */
    Usermessage findById(Integer id);

    void changeAccountInfo(Usermessage usermessage);
    void deleteId(int id);
    public int findStatus(int id);
    public int selectCompName(int id);
    public String selectAccountByCompId(int id);

}

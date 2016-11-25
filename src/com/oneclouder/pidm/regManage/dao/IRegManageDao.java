package com.oneclouder.pidm.regManage.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.regManage.model.RegManage;
import com.oneclouder.pidm.regManage.model.Usermessage;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by xucb on 16-9-19.
 */
@MyBatisRepository
@Repository("regManageDao")
public interface IRegManageDao {
    //根据查询条件查询出注册信息
    public List<RegManage> selectRegMessage(RegManage regManage)throws SQLException;
    public int selectRegMessageCount(RegManage regManage)throws SQLException;
    public List<Usermessage> findId(RegManage regManage)throws SQLException;
    public List<Usermessage> findAllCount(Usermessage usermessage)throws SQLException;
    public int findAll(Usermessage usermessage)throws SQLException;

    /**
     * 根据id查询Usermessage
     * @param id
     * @return
     */
    public Usermessage findById(Integer id);

    /**
     * 修改user信息
     * @param usermessage
     */
    public void updateUser(Usermessage usermessage);

    public void deleteId(int id);

    public int findStatus(int id);

    public int selectCompName(int id);

    public String selectAccountByCompId(int id);
}

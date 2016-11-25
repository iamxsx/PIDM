package com.oneclouder.pidm.t_user.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.t_user.dao.IInformationDao;
import com.oneclouder.pidm.t_user.model.*;
import com.oneclouder.pidm.t_user.service.IInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 18/09/16.
 */
@Service("informationService")
public class InformationServiceImpl extends BaseServiceImpl implements IInformationService {

    @Autowired
    private IInformationDao informationDao;


    @Override
    public int findrepById(Map<String, Object> params) throws SQLException {
        return informationDao.findrepById(params);
    }

    @Override
    public Description_t findDecById(Map<String, Object> params) throws SQLException {
        return informationDao.findDecById(params);
    }

    @Override
    public List<Integer> findIntroducedById(int id) throws SQLException {
        return informationDao.findIntroducedById(id);
    }

    @Override
    public User_t findtUserpById(int id) throws SQLException {
        return informationDao.findtUserpById(id);
    }

    @Override
    public int findUserIdBytAccount(String t_account) throws SQLException {
        return informationDao.findUserIdBytAccount(t_account);
    }

    @Override
    public Association_t findAssoById(int id) throws SQLException {
        return informationDao.findAssoById(id);
    }

    @Override
    public CompanyEmployee_t findempById(int id) throws SQLException {
        return informationDao.findempById(id);
    }

    @Override
    public Company_t findComById(int id) throws SQLException {
        return informationDao.findComById(id);
    }

//    @Override
//    public String findUnitIdById(int id) throws SQLException {
//        return informationDao.findUnitIdById(id);
//    }

    @Override
    public AssociationUnit_t findUnitById(int id) throws SQLException {
        return informationDao.findUnitById(id);
    }

    @Override
    public CompanyEmployee_t findemplegById(int id) throws SQLException {
        return informationDao.findemplegById(id);
    }

}

package com.oneclouder.pidm.t_user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.t_user.model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 18/09/16.
 */
public interface IInformationService extends IBaseService{
    int findrepById(Map<String, Object> params) throws SQLException;
    Description_t findDecById(Map<String, Object> params) throws SQLException;
    List<Integer> findIntroducedById(int id) throws SQLException;
    User_t findtUserpById(int id) throws SQLException;
    int findUserIdBytAccount(String t_account) throws SQLException;
    Association_t findAssoById(int id) throws  SQLException;
    CompanyEmployee_t findempById(int id) throws SQLException;
    Company_t findComById(int id) throws SQLException;
//    String findUnitIdById(int id)throws SQLException;
    AssociationUnit_t findUnitById(int id) throws SQLException;
    CompanyEmployee_t findemplegById(int id) throws SQLException;

}

package com.oneclouder.pidm.base.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/10/16 5:47 PM.
 */
public interface IBaseDao<T> {

    T findById(Integer id) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    void insert(T entity) throws SQLException;

    void update(T entity) throws SQLException;


}

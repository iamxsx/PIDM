package com.oneclouder.pidm.base.service;

import java.sql.SQLException;

/**
 * Created by PhychoLee on 9/10/16 6:34 PM.
 */
public interface IBaseService<T> {
    T findById(Integer id) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    void insert(T entity) throws SQLException;

    void update(T entity) throws SQLException;
}

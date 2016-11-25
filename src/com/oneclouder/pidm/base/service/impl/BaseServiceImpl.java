package com.oneclouder.pidm.base.service.impl;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by PhychoLee on 9/10/16 6:35 PM.
 */
public class BaseServiceImpl<T> implements IBaseService<T> {

    private IBaseDao<T> dao;
    public IBaseDao<T> getDao() {
        return dao;
    }
    /**
     * 注入Dao
     * @param dao
     */
    public void setDao(IBaseDao<T> dao) {
        this.dao = dao;
    }


    @Override
    public T findById(Integer id) throws SQLException {
        return dao.findById(id);
    }

    @Transactional
    public void deleteById(Integer id) throws SQLException {
        dao.deleteById(id);
    }

    @Transactional
    public void insert(T entity) throws SQLException {
        dao.insert(entity);
    }

    @Transactional
    public void update(T entity) throws SQLException {
        dao.update(entity);
    }
}

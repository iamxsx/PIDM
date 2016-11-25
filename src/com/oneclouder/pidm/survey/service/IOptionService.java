package com.oneclouder.pidm.survey.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.survey.model.TOption;

import java.sql.SQLException;

/**
 * Created by clouder on 10/7/16.
 */
public interface IOptionService extends IBaseService<TOption> {
    void updateCount(Integer id);

    public void insertOption(TOption option) throws SQLException;

    void updateOption(String Content,Integer id) throws SQLException;

    void countChangeZero(Integer sid);
}

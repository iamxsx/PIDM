package com.oneclouder.pidm.survey.dao;

import com.oneclouder.pidm.base.dao.IBaseDao;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.survey.model.TOption;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 10/7/16.
 */
@Lazy
@MyBatisRepository
@Repository("optionDao")
public interface IOptionDao extends IBaseDao<TOption> {
    List<TOption> findOptionBySid(Integer s_id);

    void updateCount(Integer id);

    void updateOptionContent(Map map);

    void countChangeZero(Integer sid);
}

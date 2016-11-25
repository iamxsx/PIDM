package com.oneclouder.pidm.survey.service.impl;

import com.oneclouder.pidm.base.service.impl.BaseServiceImpl;
import com.oneclouder.pidm.survey.dao.IOptionDao;
import com.oneclouder.pidm.survey.model.TOption;
import com.oneclouder.pidm.survey.service.IOptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by clouder on 10/7/16.
 */
@Service("optionService")
public class OptionService extends BaseServiceImpl<TOption> implements IOptionService {

    @Resource(name = "optionDao")
    IOptionDao optionDao;

    public void updateCount(Integer id){
        optionDao.updateCount(id);
    }

    public void insertOption(TOption option) throws SQLException {
        optionDao.insert(option);
    }

    @Override
    public void updateOption(String Content,Integer id) throws SQLException {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("optionContent",Content);
        map.put("id",id);
        optionDao.updateOptionContent(map);
    }

    @Override
    public void countChangeZero(Integer sid) {
        optionDao.countChangeZero(sid);
    }
}

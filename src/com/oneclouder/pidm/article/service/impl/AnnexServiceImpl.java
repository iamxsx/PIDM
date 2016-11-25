package com.oneclouder.pidm.article.service.impl;

import com.oneclouder.pidm.article.dao.IAnnexDao;
import com.oneclouder.pidm.article.model.Annex;
import com.oneclouder.pidm.article.service.IAnnexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by clouder on 16-9-12.
 */
@Service("annexService")
public class AnnexServiceImpl implements IAnnexService{

    @Resource(name = "annexDao")
    private IAnnexDao annexDao;

    @Override
    public void saveAnnex(Annex annex) {
        annexDao.saveAnnex(annex);
    }

    @Override
    public Annex findAnnexByArticleId(Integer aid) {
        return annexDao.findAnnexByArticleId(aid);
    }

    @Override
    public void deleteAnnex(Integer annex) {
        annexDao.deleteAnnex(annex);
    }
}

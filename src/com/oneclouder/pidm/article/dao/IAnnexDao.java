package com.oneclouder.pidm.article.dao;

import com.oneclouder.pidm.article.model.Annex;
import com.oneclouder.pidm.base.dao.MyBatisRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by clouder on 16-9-11.
 */
@MyBatisRepository
@Repository("annexDao")
public interface IAnnexDao {

    public void saveAnnex(Annex annex);


    public Annex findAnnexByArticleId(Integer aid);


    public void deleteAnnex(Integer annex);
}

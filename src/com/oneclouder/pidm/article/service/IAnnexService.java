package com.oneclouder.pidm.article.service;

import com.oneclouder.pidm.article.model.Annex;

/**
 * Created by clouder on 16-9-12.
 */
public interface IAnnexService {

    public void saveAnnex(Annex annex);


    public Annex findAnnexByArticleId(Integer aid);

    void deleteAnnex(Integer annex);
}

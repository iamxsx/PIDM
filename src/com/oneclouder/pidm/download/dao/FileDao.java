package com.oneclouder.pidm.download.dao;

import com.oneclouder.pidm.base.dao.MyBatisRepository;
import com.oneclouder.pidm.download.model.FileModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-27.
 */
@MyBatisRepository
@Repository("fileDao")
public interface FileDao {


    public void saveFile(FileModel model);

    List<FileModel> getFileList(Map<String, Object> params);

    int getFileCount();

    void deleteFile(Integer id);
}

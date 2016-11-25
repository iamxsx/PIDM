package com.oneclouder.pidm.download.service;

import com.oneclouder.pidm.download.model.FileModel;

import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-27.
 */
public interface IFileManageService {

    public void saveFile(FileModel model);

    List<FileModel> getFileList(Map<String, Object> params);

    int getFileCount();

    void deleteFile(Integer id);
}

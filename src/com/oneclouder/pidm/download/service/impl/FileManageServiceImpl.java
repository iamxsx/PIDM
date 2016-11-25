package com.oneclouder.pidm.download.service.impl;

import com.oneclouder.pidm.download.dao.FileDao;
import com.oneclouder.pidm.download.model.FileModel;
import com.oneclouder.pidm.download.service.IFileManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 16-9-27.
 */
@Service("fileManageService")
public class FileManageServiceImpl implements IFileManageService{

    @Resource
    private FileDao fileDao;

    @Override
    public void saveFile(FileModel model) {
        fileDao.saveFile(model);
    }

    @Override
    public List<FileModel> getFileList(Map<String, Object> params) {
        return fileDao.getFileList(params);
    }

    @Override
    public int getFileCount() {
        return fileDao.getFileCount();
    }

    @Override
    public void deleteFile(Integer id) {
        fileDao.deleteFile(id);
    }
}

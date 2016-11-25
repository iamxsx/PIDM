package com.oneclouder.pidm.download.model;

/**
 * Created by clouder on 16-9-27.
 */
public class FileModel {

    private String id;

    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件url
     */
    private String fileUrl;
    /**
     * 文件上传时间
     */
    private String uploadTime;
    /**
     * 文件描述
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.oneclouder.pidm.download.controller;

import com.oneclouder.pidm.download.model.FileModel;
import com.oneclouder.pidm.download.service.IFileManageService;
import com.oneclouder.pidm.util.DateUtil;
import com.oneclouder.pidm.util.FtpUtil;
import com.oneclouder.pidm.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.oneclouder.pidm.article.controller.ArticleAction.generateFileName;

/**
 * Created by clouder on 16-9-27.
 */
@Controller
@RequestMapping("back/download-center")
public class DonwloadCenter {

    @Resource
    private IFileManageService fileManageService;

    @RequestMapping("")
    public String toDownloadCenter(){
        return "back/download-center/upload-file";
    }


    @RequestMapping("fileUpload")
    public String fileUpload(HttpServletRequest request,String description){
        FileModel model = new FileModel();
        try {
            upload(request,model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setDescription(description);
        fileManageService.saveFile(model);
        return "back/download-center/upload-success";
    }

    public void upload(HttpServletRequest request,FileModel model) throws Exception {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                System.out.println(file.getName());
                if (file != null) {
                    String filename = file.getOriginalFilename();
                    if (filename.trim() != "") {
                        filename = generateFileName(filename);
                        String location = PropertiesUtil.getProperty("CENTER_UPLOAD_PATH");
                        FtpUtil.upload(file.getInputStream(), filename, location);
                        model.setFileName(filename);
                        model.setFileUrl(location + filename);
                        model.setUploadTime(DateUtil.getCurrentDate());
                    }
                }
            }
        }
    }

    @RequestMapping("getFileList")
    @ResponseBody
    public Map<String,Object> getFileList(Integer limit,Integer offset){
        Map<String,Object> map = new HashMap<>();
        map.put("limit",limit);
        map.put("offset",offset);
        List<FileModel> files = fileManageService.getFileList(map);
        int total = fileManageService.getFileCount();
        map.clear();
        map.put("rows",files);
        map.put("total",total);
        return map;
    }

    @RequestMapping("download")
    @ResponseBody
    public void download(HttpServletResponse response, String fileUrl) throws IOException {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/"));
        FtpUtil.downloadFile(response,fileUrl,fileName);
    }

    @RequestMapping("delete")
    @ResponseBody
    public void delete(Integer id,String fileUrl){
        fileManageService.deleteFile(id);
        FtpUtil.deleteFile(fileUrl);
    }

}

package com.oneclouder.pidm.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.SocketException;
import java.util.*;

/**
 * Created by clouder on 16-9-11.
 */
public class FtpUtil {

    /**
     * 获取FTPClient对象
     *
     * @return
     */
    public static FTPClient getFTPClient() {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(
                    PropertiesUtil.getProperty("HOST"),
                    Integer.parseInt(PropertiesUtil.getProperty("PORT"))
            );
            ftpClient.login(
                    PropertiesUtil.getProperty("USERNAME"),
                    PropertiesUtil.getProperty("PASSWORD")
            );
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setDataTimeout(3000);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    public static FTPFile[] listFtpFiles(String filePath) throws IOException {
        FTPClient ftpClient = getFTPClient();
        return ftpClient.listFiles(filePath);
    }

    /**
     *
     * @param inputStream 需上传的文件的输入流
     * @param fileName 上传到 ftp 服务器后的文件名
     * @param location  文件的存放路径
     * @throws Exception
     */
    public static void upload(InputStream inputStream, String fileName,String location) throws Exception {
        FTPClient ftpClient = getFTPClient();
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        // 设置以二进制流的方式传输
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.makeDirectory(location);
        ftpClient.changeWorkingDirectory(location);
        boolean isSuccess = ftpClient.storeFile(fileName ,inputStream);
        if (isSuccess){
            System.out.println("上传成功");
        }else{
            System.out.println("上传失败");
        }
        ftpClient.logout();
        ftpClient.disconnect();
    }

    //private static Map<String,String> imageContentType = new HashMap<>();

    /*static {
        imageContentType.put("jpg", "image/jpeg");
        imageContentType.put("jpeg", "image/jpeg");
        imageContentType.put("png", "image/png");
        imageContentType.put("tif", "image/tiff");
        imageContentType.put("tiff", "image/tiff");
        imageContentType.put("ico", "image/x-icon");
        imageContentType.put("bmp", "image/bmp");
        imageContentType.put("gif", "image/gif");
    }*/
    /**
     * 在浏览器中显示图片
     * @param response
     * @param path
     */
    public static void showImages(HttpServletResponse response, String path) throws IOException {
        FTPClient ftpClient = getFTPClient();
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        // 设置以二进制流的方式传输
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        OutputStream out = null;
        //String contentType = path.substring(path.lastIndexOf(".")+1);
        response.setContentType("image/*");
        out = response.getOutputStream();
        boolean retrieveFile = ftpClient.retrieveFile(path, out);
        if (retrieveFile == true)
        {
            out.close();
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     *
     * 下载文件到本地
     *
     * @param destPath 目标路径（包括文件名）
     * @param fileName 文件名
     * @return
     */
    public static void download(String destPath ,String fileName) throws IOException {
        FTPClient ftpClient = getFTPClient();
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        // 设置以二进制流的方式传输
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        // 下载存放的文件
        File outfile = new File(PropertiesUtil.getProperty("DOWNLOAD_FILE_PATH") + "/" + fileName);
        OutputStream oStream = null;
        try {
            oStream = new FileOutputStream(outfile);
            ftpClient.retrieveFile(destPath, oStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oStream != null){
                try {
                    oStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

//    /**
//     *
//     * 下载文件到本地
//     *
//     * @param filePaths   文件路径集合
//     * @throws IOException
//     */
//    public static void download(List<String> filePaths) throws IOException {
//        FTPClient ftpClient = getFTPClient();
//        // 设置PassiveMode传输
//        ftpClient.enterLocalPassiveMode();
//        // 设置以二进制流的方式传输
//        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//        // 下载存放的文件
//        OutputStream oStream = null;
//        try {
//        for (String filePath : filePaths){
//            //下载到本地
//            File outfile = new File(PropertiesUtil.getProperty("DOWNLOAD_FILE_PATH") + "/" + filePath);
//
//            oStream = new FileOutputStream(outfile);
//            ftpClient.retrieveFile(filePath, oStream);
//        }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (oStream != null){
//                try {
//                    oStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }



    public static void downloadFile(HttpServletResponse response,String destPath ,String fileName) throws IOException {
        FTPClient ftpClient = getFTPClient();
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        // 设置以二进制流的方式传输
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        OutputStream out = response.getOutputStream();
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        boolean retrieveFile = ftpClient.retrieveFile(destPath, out);
        if (retrieveFile == true) {
            out.close();
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }


    public static boolean deleteFile(String filePath){
        FTPClient ftpClient = getFTPClient();
        try {
            return ftpClient.deleteFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }







}

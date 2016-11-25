package com.oneclouder.pidm.util;

import com.oneclouder.pidm.user.webBean.UserSimpleInfo;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by clouder on 9/10/16.
 */
public class POIUtil {

    public static void exportToExcel(HttpServletResponse response, String[] key, String[] headerList, List<Map> map) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HSSFWorkbook excelWorkBook = new HSSFWorkbook();
        HSSFSheet sheet = excelWorkBook.createSheet("客户信息表");

        HSSFCellStyle style = excelWorkBook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFRow headrow = sheet.createRow(0);
        for (int i = 0; i < headerList.length;i++){
            HSSFCell headercell = headrow.createCell(i);
            headercell.setCellValue(headerList[i]);
            headercell.setCellStyle(style);
        }

        for (int i = 0;i < map.size();i++){
            HSSFRow contentRow = sheet.createRow(i+1);
            for (int j = 0; j < key.length;j++){
                HSSFCell contentcell = contentRow.createCell(j);
                if (map.get(i).get(key[j])!=null){
                    contentcell.setCellValue(map.get(i).get(key[j]).toString());
                }else{
                    contentcell.setCellValue("");
                }

                contentcell.setCellStyle(style);
            }
        }

        for (int i = 0;i < map.size()+1;i++){
            sheet.autoSizeColumn(i);
        }

        /*FileOutputStream out = null;*/
        try {
            response.reset();
            response.setContentType("application/x-msdownload;charset=uft-8");
            response.addHeader("Content-Disposition","attachment;filename="+ new String("客户信息表.xls".getBytes("UTF-8"),"iso-8859-1"));
            OutputStream out = response.getOutputStream();

            /*out = new FileOutputStream("/home/clouder/Downloads/a.xls");*/
            excelWorkBook.write(out);
            /*out.close();*/
            excelWorkBook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

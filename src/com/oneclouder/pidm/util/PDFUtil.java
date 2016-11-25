package com.oneclouder.pidm.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.fonts.otf.TableHeader;
import com.oneclouder.pidm.user.model.Company;
import com.oneclouder.pidm.user.model.CompanyEmployee;
import com.oneclouder.pidm.user.model.Description;
import com.oneclouder.pidm.user.model.User;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

/**
 * Created by clouder on 9/19/16.
 */
public class PDFUtil {

    public static void exportToPDF(HttpServletResponse response,List<Map> clientInfo){//,List<Object> Info
        Document doc = new Document(PageSize.A4,20,20,20,20);
        OutputStream out = null;
        try {
            //设置中文字体
            BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese);
            font.setSize(11);
            font.setStyle(FontFactory.HELVETICA);

            //以输出流下载到本地
            response.reset();
            response.setContentType("application/x-msdownload;charset=uft-8");
            response.addHeader("Content-Disposition","attachment;filename="+ new String("客户信息.pdf".getBytes("UTF-8"),"iso-8859-1"));
            out = response.getOutputStream();
            PdfWriter wr = PdfWriter.getInstance(doc,out);
            doc.open();

            /*User user = (User)Info.get(0);
            Company company = user.getCompany();
            CompanyEmployee legalEmployee = (CompanyEmployee) Info.get(1);
            CompanyEmployee sEmployee = user.getDesignatedContact();
            Description des = (Description) Info.get(2);

            doc.addTitle(company.getName());*/


            //设置单元格高度
            float lineHeight = (float) 30.0;
            float lineHeight2 = (float) 80.0;
            PdfPTable itable;
            //文档内容
            PdfPTable table = new PdfPTable(5);

            PdfPCell cell;
            PdfPCell icell;

            cell = new PdfPCell(new Paragraph("帐号",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setBackgroundColor(new BaseColor(240,240,240));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("account").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("帐号昵称",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setBackgroundColor(new BaseColor(240,240,240));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("客户姓名",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setBackgroundColor(new BaseColor(240,240,240));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("real_name").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("客户身份证",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setBackgroundColor(new BaseColor(240,240,240));

            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("IDcard").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("客户手机号码",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setBackgroundColor(new BaseColor(240,240,240));

            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("phone_num").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("客户联系邮箱",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setBackgroundColor(new BaseColor(240,240,240));

            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("uemail").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("单位名称",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setBackgroundColor(new BaseColor(240,240,240));

            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("companyName").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setColspan(3);
            table.addCell(cell);

            itable = new PdfPTable(2);
            icell = new PdfPCell(new Paragraph("单位性质",font));
            icell.setBackgroundColor(new BaseColor(240,240,240));

            icell.setHorizontalAlignment(Element.ALIGN_CENTER);
            icell.setVerticalAlignment(Element.ALIGN_MIDDLE);

//            icell.setBorder(1);
            icell.setFixedHeight(lineHeight);
            itable.addCell(icell);

            icell = new PdfPCell(new Paragraph(clientInfo.get(0).get("mnature").toString(),font));
            icell.setHorizontalAlignment(Element.ALIGN_CENTER);
            icell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            icell.setBorder(1);
            icell.setFixedHeight(lineHeight);
            itable.addCell(icell);

            cell = new PdfPCell(itable);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(1);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("通讯地址",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("address").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            cell.setColspan(3);
            table.addCell(cell);

            itable = new PdfPTable(2);
            icell = new PdfPCell(new Paragraph("邮政编码",font));
            icell.setBackgroundColor(new BaseColor(240,240,240));

            icell.setHorizontalAlignment(Element.ALIGN_CENTER);
            icell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            icell.setBorder(1);
            icell.setFixedHeight(lineHeight);
            itable.addCell(icell);

            icell = new PdfPCell(new Paragraph(clientInfo.get(0).get("zip_code").toString(),font));
            icell.setHorizontalAlignment(Element.ALIGN_CENTER);
            icell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            icell.setBorder(1);
            icell.setFixedHeight(lineHeight);
            itable.addCell(icell);
            cell = new PdfPCell(itable);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(1);
            table.addCell(cell);


            for (Map map:clientInfo) {
                if(map.get("emnature").equals(2)){
                    cell = new PdfPCell(new Paragraph("法人代表",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setRowspan(2);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("姓名",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("单位职务",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("办公电话",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("手机",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("desName").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("job_position").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("office_phone_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("cell_phone_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                }

            }
            cell = new PdfPCell(new Paragraph("加入协会",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setRowspan(3);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("协会/分会",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setColspan(2);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("会员单位级别",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setColspan(2);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("association_name").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setColspan(2);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("association_unit").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setColspan(2);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);


            cell = new PdfPCell(new Paragraph("分会",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("chapter_name").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("chapter_unit").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setColspan(2);
            cell.setFixedHeight(lineHeight);
            table.addCell(cell);

            for (Map map:clientInfo) {
                if(map.get("emnature").equals(1)){
                    cell = new PdfPCell(new Paragraph("推荐人选及拟任分会职务",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setRowspan(4);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("姓名",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("单位职务",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("协会职务",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("分会职务",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("desName").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setRowspan(3);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("job_position").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("asct_job_position").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("chapter_job_position").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("电子邮箱",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("办公电话",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("手机",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("ememail").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("office_phone_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("cell_phone_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                }

            }

            for (Map map:clientInfo) {
                if(map.get("emnature").equals(0)){
                    cell = new PdfPCell(new Paragraph("单位指定联系人",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setRowspan(4);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("姓名",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("单位职务",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("办公电话",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("手机",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("desName").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setRowspan(3);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("job_position").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("office_phone_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("cell_phone_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("电子邮箱",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("传真号码",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph("微信号/QQ号",font));
                    cell.setBackgroundColor(new BaseColor(240,240,240));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("ememail").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("fax_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(map.get("online_num").toString(),font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
                    cell.setFixedHeight(lineHeight);
                    table.addCell(cell);
                }
            }
            cell = new PdfPCell(new Paragraph("单位简介",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setRowspan(3);
            cell.setFixedHeight(lineHeight2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("introduction").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setRowspan(3);
            cell.setColspan(4);
            cell.setFixedHeight(lineHeight2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("希望得到协会或者分会服务和支持的主要内容",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setRowspan(3);
            cell.setFixedHeight(lineHeight2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("demand").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setRowspan(3);
            cell.setColspan(4);
            cell.setFixedHeight(lineHeight2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("省协会意见",font));
            cell.setBackgroundColor(new BaseColor(240,240,240));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setRowspan(3);
            cell.setFixedHeight(lineHeight2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(clientInfo.get(0).get("audit_opinion").toString(),font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBorder(1);
            cell.setRowspan(3);
            cell.setColspan(4);
            cell.setFixedHeight(lineHeight2);
            table.addCell(cell);




            doc.add(table);

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

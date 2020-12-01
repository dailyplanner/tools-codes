package com.dailyplanner.toolscodes.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ExcelUtil {

    public static boolean writeData() {
        // 表格路径
        String path = "F:\\study\\testExcel.xlsx";
        Workbook workbook = new XSSFWorkbook();
        // 创建表中sheet
        Sheet sheet = workbook.createSheet("sheet1");

// 表头
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("X");

// 内容
        for (int index=1; index<10; index++) {
            row = sheet.createRow(index);
            row.createCell(0).setCellValue("90");
        }

// 写入文件
        try {
            FileOutputStream out = new FileOutputStream(path);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Boolean saveJsonDataToExcel(JsonArray jsonArray) throws Exception {
        String path = "F:\\study\\testExcel.xlsx";
        InputStream iStream = new FileInputStream(path);
        // .xlsx
        XSSFWorkbook workbook = new XSSFWorkbook(iStream);

        //循环每一页，并处理当前页
        for (Sheet xssfSheet : workbook) {
            if (xssfSheet == null) {
                continue;
            }
            // 表头
            Row headRow = xssfSheet.createRow(0);
            headRow.createCell(0).setCellValue("省份");
            headRow.createCell(1).setCellValue("城市");
            headRow.createCell(2).setCellValue("站点名");
            headRow.createCell(3).setCellValue("位置");
            headRow.createCell(4).setCellValue("电话");

            // 写入内容
            for (int i = 1; i <= jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i-1).getAsJsonObject();
                // 新增一行Excel表格
                Row row = xssfSheet.createRow(i);
                row.createCell(0).setCellValue(jsonObject.get("province").getAsString());
                row.createCell(1).setCellValue(jsonObject.get("city").getAsString());
                row.createCell(2).setCellValue(jsonObject.get("name").getAsString());
                row.createCell(3).setCellValue(jsonObject.get("address").getAsString());
                row.createCell(4).setCellValue(jsonObject.get("phone").getAsString());
            }

            try {
                // 写入文件
                FileOutputStream out = new FileOutputStream(path);
                workbook.write(out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }
}

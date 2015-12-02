package com.mlnx.doc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.mlnx.doc.entity.Doctor;

public class DoctorInfoExcel {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DoctorInfoExcel.class);
	
	public static List<Doctor> importBuildInfo(File file ,HttpServletRequest request){
    try {
        // 创建需要批量插入数据集合
        List<Doctor> list = new ArrayList<Doctor>();
        // 创建一个FileInputStream 文件输入流
        FileInputStream inputStream = new FileInputStream(file);
        // 创建对Excel工作簿文件的引用
        Workbook wookbook = null;
        String name = file.getName();
        String fileType = name.substring(name.lastIndexOf(".") + 1,
                name.length());
        if (fileType.equals("xlsx")) {
            wookbook = new XSSFWorkbook(inputStream);
        } else if (fileType.equals("xls")) {
            wookbook = new HSSFWorkbook(inputStream);
        }
        // 在Excel文档中，第一张工作表的缺省索引是0
        Sheet sheet = wookbook.getSheetAt(0);
        // 获取到Excel文件中的所有行数
        int rows = sheet.getPhysicalNumberOfRows();
        // 遍历行 从第二行开始遍历
        for (int i = 1; i < rows; i++) {
            // 读取左上端单元格
            Row row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                // 将对象增加到集合中
                list.add(null);
            }
        }
        // 返回集合
        return list;
    } catch (IOException e) {
        logger.error("创建导入excel对象报错！", e);
    }
    return null;
}
}

package com.mlnx.doc.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String savePic(HttpServletRequest request, MultipartFile file) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String realPath = path.substring(0, a + 7) + "/upload/" + today;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		// String fileName = new Date().getTime()+".jpg";
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String pic = "/upload/" + today + "/" + fileName;
		return pic;
	}
}

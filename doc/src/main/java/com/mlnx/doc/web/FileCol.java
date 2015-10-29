package com.mlnx.doc.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileCol {
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, ModelMap model) throws IOException {
		//V:\tomcatspringmvc\apache-tomcat-7.0.57\webapps\doc\
		String path = request.getSession().getServletContext()
				.getRealPath("/");
		int a = path.indexOf("webapps");
		
		String realPath = path.substring(0,a+7)+"/upload";
		String fileName = file.getOriginalFilename();
		// String fileName = new Date().getTime()+".jpg";
		System.out.println(realPath);
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
		model.addAttribute("fileUrl",  "/upload/"
				+ fileName);

//		if (!file.isEmpty()) {
//			byte[] bytes = file.getBytes();
			// store the bytes somewhere
//			return "uploadSuccess";
//		}

		return "uploadSuccess";
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file() {
		return "fileUpload";
	}
}

package com.mlnx.doc.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolKit {

	private static final Logger logger = LoggerFactory.getLogger(ToolKit.class);

	public static File getFileFromBytes(byte[] bytes, String path) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(path);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(bytes);
		} catch (Exception e) {
			logger.error("文件保存出错", e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					logger.error("文件流关闭出错", e1);
				}
			}
		}
		return file;
	}

	public static void deleteFile(String path) {
		// TODO Auto-generated method stub
		System.out.println("执行删除文件");
	}

}

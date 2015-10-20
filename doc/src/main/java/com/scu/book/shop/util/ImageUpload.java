package com.scu.book.shop.util;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
/**
 * 1:jsp:
 * enctype="multipart/form-data"
 * <input type="file" name="file" />
 * 2:spring配置改动:
 * <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
 *       <property name="maxUploadSize" value="1000000"/>
 *  </bean>
 * @author Administrator
 *
 */
public class ImageUpload {
	@Value("#{imagePropertise['bs.image.path']}")
	private String imagePath;
	
	private String uploadImage(MultipartFile file) throws Exception{
		 String name = createName(file.getOriginalFilename());
		 mkFilePath();
		 if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            FileOutputStream fos = new FileOutputStream(imagePath+name); // 上传到写死的上传路径
            fos.write(bytes);  //写入文件
          } 
		 return name;
	}
	private void mkFilePath(){
		File file =new File(imagePath);    
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    System.out.println("//不存在");  
		    file .mkdir();    
		} else   
		{  
		    System.out.println("//目录存在");  
		}  
	}
	private String createName(String name){
		File file =new File(imagePath+name);
		if(file.exists()){
			name = name.substring(0,name.lastIndexOf("."))+"-副本"+name.substring(name.lastIndexOf("."));
			return createName(name);
		}else{
			return name;
		}
	}
}

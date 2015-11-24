package com.mlnx.mlnxapp.test.rest;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* city test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class PatientTest {

	/**
	 * 已经注册的会报错
	 */
	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("hospital", "南京医院");
		obj.put("office", "心电内科");
		obj.put("domain", "二号病区");
		obj.put("room", "302室");
		obj.put("bed", "1号床");
		obj.put("doctor_id", 1);
		obj.put("name", "1号病人");
		obj.put("state","在疗");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/patients/register.do",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findByDoctorIdAndState(){
		String sr = HttpUtil
				.sendPost("http://localhost:8080/doc/patients/find/doctor/1/state.do","1");
		System.out.println(sr);
	}
	private static void findById(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/patients/find/1/patient.do");
		System.out.println(sr);
	}
	private static void modify(){
		JSONObject obj = new JSONObject();
		obj.put("id", 1);
		obj.put("hospital", "南京医院");
		obj.put("office", "心电内科");
		obj.put("domain", "三号病区");
		obj.put("room", "302室");
		obj.put("bed", "1号床");
		obj.put("doctor_id", 1);
		obj.put("name", "1号病人");
		obj.put("state","在疗");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/patients/modify.do",
				obj.toJSONString());
		System.out.println(sr);
	}
	
	private static void findOnline() throws DocumentException, UnsupportedEncodingException{
		String sr = HttpUtil
				.sendGet("http://121.40.137.14:8080/pms-server/rest/devices/online");
		 SAXReader saxReader = new SAXReader();
		 Document document = saxReader.read(new ByteArrayInputStream(sr.getBytes("UTF-8")));
		   // 获取根元素
	        Element root = document.getRootElement();
	        System.out.println("Root: " + root.getName());

	        // 获取所有子元素
	        List<Element> childList = root.elements();
	        System.out.println("total child count: " + childList.size());

	        // 获取特定名称的子元素
//	        List<Element> childList2 = root.elements("hello");
//	        System.out.println("hello child: " + childList2.size());
//
//	        // 获取名字为指定名称的第一个子元素
//	        Element firstWorldElement = root.element("world");
//	        // 输出其属性
//	        System.out.println("first World Attr: "
//	                + firstWorldElement.attribute(0).getName() + "="
//	                + firstWorldElement.attributeValue("name"));

	        System.out.println("迭代输出-----------------------");
	        // 迭代输出
	        for (Iterator iter = root.elementIterator(); iter.hasNext();)
	        {
	            Element e = (Element) iter.next();
	            System.out.println(e.getName());
	            for(Iterator it = e.elementIterator();it.hasNext();){
	            	Element ee = (Element)it.next();
	            	System.out.println(ee.getName()+":"+ee.getText());
	            }
	        }
		System.out.println(sr);
	}
	public static void main(String[] args) throws Exception {

//		regist();
//		findByDoctorIdAndState();
//		findById();
//		modify();
		findOnline();
	}
}

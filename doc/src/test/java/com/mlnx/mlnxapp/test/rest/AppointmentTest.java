package com.mlnx.mlnxapp.test.rest;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* appointment test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class AppointmentTest {

	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("key", "value");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/appointments",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/appointments/all");
		System.out.println(sr);
	}

	private static void delete() {

		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/appointments/delete",
				"{id}");
		System.out.println(sr);
	}

	private static void findById() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/appointments/{id}");
		System.out.println(sr);
	}
	
	private static void findByAskId(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/appointments/ask/9");
		System.out.println(sr);
	}
	
	private static void findByAnswerId(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/appointments/answer/9");
		System.out.println(sr);
	}

	public static void main(String[] args) {

//		regist();
//		findAll();
//		delete();
//		findById();
		findByAskId();
		findByAnswerId();
	}
}

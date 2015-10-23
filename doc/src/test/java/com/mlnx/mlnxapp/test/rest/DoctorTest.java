package com.mlnx.mlnxapp.test.rest;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* doctor test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class DoctorTest {

	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("phone", "15867405827");
		obj.put("password","123456");
		obj.put("date", new Date());
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/doctors/register",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/doctors/all");
		System.out.println(sr);
	}

	private static void delete() {

		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/doctors/delete",
				"{id}");
		System.out.println(sr);
	}

	private static void findById() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/doctors/{id}");
		System.out.println(sr);
	}
	
	private static void findByHospital(){
		String sr = HttpUtil
				.sendPost("http://localhost:8080/doc/doctors/hospital","一");
		System.out.println(sr);
	}
	
	private static void findByName(){
		String sr = HttpUtil
				.sendPost("http://localhost:8080/doc/doctors/find/name","王");
		System.out.println(sr);
	}
	
	private static void findByPhone(){
		String sr = HttpUtil
				.sendPost("http://localhost:8080/doc/doctors/find/phone","158");
		System.out.println(sr);
	}

	public static void main(String[] args) {

//		regist();
//		findAll();
//		delete();
//		findById();
//		findByHospital();
//		findByName();
//		findByPhone();
	}
}

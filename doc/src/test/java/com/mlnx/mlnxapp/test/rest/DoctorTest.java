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
		obj.put("phone", "15867404001");
		obj.put("password","123456");
//		obj.put("date", new Date());
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/doctors/register.do",
				obj.toJSONString());
		System.out.println(sr);
	}
	
	private static void login(){
		JSONObject obj = new JSONObject();
		obj.put("username", "admin");
		obj.put("password","12345");
		obj.put("date", new Date());
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/doctors/login.do",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/doctors/all.do");
		System.out.println(sr);
	}

	private static void delete() {

		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/doctors/delete.do",
				"{id}");
		System.out.println(sr);
	}

	private static void findById() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/doctors/find/1/doctor.do");
		System.out.println(sr);
	}
	
	private static void findByHospital(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/doctors/find/hospital/1/doctors.do");
		System.out.println(sr);
	}
	
	private static void findByName(){
		String sr = HttpUtil
				.sendPost("http://localhost:8080/doc/doctors/find/name.do","王");
		System.out.println(sr);
	}
	
	private static void findByPhone(){
		String sr = HttpUtil
				.sendPost("http://localhost:8080/doc/doctors/find/phone.do","158");
		System.out.println(sr);
	}
	
	private static void findByDoctorId(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/doctors/find/1/friends.do");
		System.out.println(sr);
	}

	private static void modify(){
		JSONObject obj = new JSONObject();
		obj.put("id", 1);
		obj.put("achievement", "取得的成就");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/doctors/modify.do",
				obj.toJSONString());
		System.out.println(sr);
	}
	public static void main(String[] args) {

		regist();
//		login();
//		findAll();
//		delete();
//		findById();
//		findByHospital();
//		findByName();
//		findByPhone();
//		findByDoctorId();
	}
}

package com.mlnx.mlnxapp.test.rest;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* doctor_doctor test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class Doctor_doctorTest {

	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("doctor_id", 3);
		obj.put("friend_id",4);
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/doctor_doctors",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/doctor_doctors/all");
		System.out.println(sr);
	}

	private static void delete() {

		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/doctor_doctors/delete",
				"{id}");
		System.out.println(sr);
	}

	private static void findById() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/doctor_doctors/{id}");
		System.out.println(sr);
	}

	public static void main(String[] args) {

//		regist();
		findAll();
//		delete();
//		findById();
	}
}

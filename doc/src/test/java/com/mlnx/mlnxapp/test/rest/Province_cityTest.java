package com.mlnx.mlnxapp.test.rest;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* province_city test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class Province_cityTest {

	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("key", "value");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/province_citys",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/province_citys/all");
		System.out.println(sr);
	}

	private static void delete() {

		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/province_citys/delete",
				"{id}");
		System.out.println(sr);
	}

	private static void findById() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/province_citys/{id}");
		System.out.println(sr);
	}

	public static void main(String[] args) {

		regist();
		findAll();
		delete();
		findById();
	}
}

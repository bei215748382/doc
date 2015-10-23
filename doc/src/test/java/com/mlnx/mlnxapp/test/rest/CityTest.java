package com.mlnx.mlnxapp.test.rest;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* city test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class CityTest {

	/**
	 * 已经注册的会报错
	 */
	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("name", "南京");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/citys",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/citys/all");
		System.out.println(sr);
	}

	private static void delete() {

		String sr = HttpUtil.sendPost(
				"http://localhost:8080/hospital-doctor/rest/citys/delete",
				"{id}");
		System.out.println(sr);
	}

	private static void findById() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/citys/{id}");
		System.out.println(sr);
	}

	private static void findByProvinceId(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/hospital-doctor/rest/citys/province/6");
		System.out.println(sr);
	}
	public static void main(String[] args) {

//		regist();
//		findAll();
//		delete();
//		findById();
		findByProvinceId();
	}
}

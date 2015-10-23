package com.mlnx.mlnxapp.test.rest;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* province test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class ProvinceTest {

	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("name", "江苏");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/provinces/register",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/provinces/all");
		System.out.println(sr);
	}

	private static void delete() {

		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/provinces/delete",
				"{id}");
		System.out.println(sr);
	}

	private static void findById() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/provinces/{id}");
		System.out.println(sr);
	}

	public static void main(String[] args) {

//		regist();
		findAll();
//		delete();
//		findById();
	}
}

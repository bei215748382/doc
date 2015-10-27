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
		obj.put("name", "浙江省");
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/provinces/register.do",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findAll() {

		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/provinces/all.do");
		System.out.println(sr);
	}


	public static void main(String[] args) {

		regist();
//		findAll();
	}
}

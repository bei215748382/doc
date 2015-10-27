package com.mlnx.mlnxapp.test.rest;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* hospital test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class HospitalTest {

	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("name", "江苏省第一医院");
		obj.put("city_id",1);
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/hospitals/register.do",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findByCity(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/hospitals/find/city/1/hospital.do");
		System.out.println(sr);
	}
	
	public static void main(String[] args) {

		regist();
		findByCity();
	}
}

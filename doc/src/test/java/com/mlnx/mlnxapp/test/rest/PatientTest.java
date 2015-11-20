package com.mlnx.mlnxapp.test.rest;

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
	public static void main(String[] args) {

//		regist();
		findByDoctorIdAndState();
//		findById();
//		modify();
	}
}

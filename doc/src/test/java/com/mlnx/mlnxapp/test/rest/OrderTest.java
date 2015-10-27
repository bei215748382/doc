package com.mlnx.mlnxapp.test.rest;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.mlnx.mlnxapp.test.util.HttpUtil;
/**
* city test类
* Fri Oct 16 15:42:57 CST 2015 GenEntityMysql工具类生成
*/ 
public class OrderTest {

	/**
	 * 已经注册的会报错
	 */
	private static void regist() {

		JSONObject obj = new JSONObject();
		obj.put("doctor_id", 1);
		obj.put("friend_id", 4);
		obj.put("date", new Date());
		String sr = HttpUtil.sendPost(
				"http://localhost:8080/doc/orders/register.do",
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findOrdersByDoctorIdAndState(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/orders/find/doctor/1/state/0/orders.do");
		System.out.println(sr);
	}
	
	private static void findOrdersByFriendIdAndState(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/orders/find/friend/2/state/0/orders.do");
		System.out.println(sr);
	}
	private static void findByDoctorIdAndToday(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/orders/find/doctor/1/today.do");
		System.out.println(sr);
	}
	private static void findByFriendIdAndToday(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/orders/find/friend/4/today.do");
		System.out.println(sr);
	}
	private static void updateRemind(){
		String sr = HttpUtil
				.sendGet("http://localhost:8080/doc/orders/update/8/remind.do");
		System.out.println(sr);
	}
	public static void main(String[] args) {

//		regist();
//		findOrdersByDoctorIdAndState();
//		findOrdersByFriendIdAndState();
//		findByDoctorIdAndToday();
//		findByFriendIdAndToday();
		updateRemind();
	}
}

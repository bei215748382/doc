package com.mlnx.mlnxapp.test.rest;

import java.util.Date;

import org.junit.Test;

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
		obj.put("doctor_id", 60);
		obj.put("friend_id", 62);
		String sr = HttpUtil.sendPost(String.format("http://localhost:8082/doc/orders/%d/register.do", new Date().getTime())
				,
				obj.toJSONString());
		System.out.println(sr);
	}

	private static void findOrdersByDoctorIdAndState(){
		String sr = HttpUtil
				.sendGet("http://localhost:8082/doc/orders/find/doctor/1591/state/0/orders.do");
		System.out.println(sr);
	}
	
	private static void findOrdersByFriendIdAndState(){
		String sr = HttpUtil
				.sendGet("http://localhost:8082/doc/orders/find/friend/1591/state/0/orders.do");
		System.out.println(sr);
	}
	private static void iosFindOrdersByDoctorIdAndState(){
		String sr = HttpUtil
				.sendGet("http://localhost:8082/doc/orders/ios/find/doctor/1591/state/0/orders.do");
		System.out.println(sr);
	}
	
	private static void iosFindOrdersByFriendIdAndState(){
		String sr = HttpUtil
				.sendGet("http://localhost:8082/doc/orders/ios/find/friend/1591/state/0/orders.do");
		System.out.println(sr);
	}
	private static void findByDoctorIdAndToday(){
		String sr = HttpUtil
				.sendGet("http://localhost:8082/doc/orders/find/doctor/1591/today.do");
		System.out.println(sr);
	}
	private static void findByFriendIdAndToday(){
		String sr = HttpUtil
				.sendGet("http://localhost:8082/doc/orders/find/friend/1591/today.do");
		System.out.println(sr);
	}
	private static void updateRemind(){
		String sr = HttpUtil
				.sendGet("http://localhost:8082/doc/orders/update/1591/remind.do");
		System.out.println(sr);
	}
	public static void main(String[] args) {

	//	regist();
//		findOrdersByDoctorIdAndState();
//		findOrdersByFriendIdAndState();
//		findByDoctorIdAndToday();
//		findByFriendIdAndToday();
//		updateRemind();
		iosFindOrdersByDoctorIdAndState();
//		iosFindOrdersByFriendIdAndState();
	}
}

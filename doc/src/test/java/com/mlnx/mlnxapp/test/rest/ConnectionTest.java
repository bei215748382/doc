package com.mlnx.mlnxapp.test.rest;

import org.junit.Test;

import com.mlnx.mlnxapp.test.util.HttpUtil;

public class ConnectionTest {
	@Test
	public void test() {
		String url = "http://121.40.137.14:8080/pms-server/rest/devices/online";
		String s = HttpUtil.sendGet(url);
		System.out.println(s);
	}

}

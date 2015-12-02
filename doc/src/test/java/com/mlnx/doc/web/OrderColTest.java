package com.mlnx.doc.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.service.OrderService;

public class OrderColTest extends BaseCol{
	@Autowired
	private OrderService orderService;
	@Test
	public void testUpdateState() {
		System.out.println(orderService.updateState(8));
	}

}

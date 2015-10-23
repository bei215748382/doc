package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Order;
import com.mlnx.doc.service.OrderService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/orders")
public class OrderCol {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "all")
	@ResponseBody
	public List<Order> findAll() {
		return orderService.findAll();
	}

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody Order order) {
		orderService.save(order);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.ORDER_REGISTER_SUSSESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.ORDER_REGISTER_SUSSESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/doctor/{id}/state/{sid}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByDoctorIdAndState(@PathVariable int id,@PathVariable int sid) {
		return orderService.findByDoctorIdAndState(id, sid);
	}
	
	@RequestMapping(value = "find/friend/{id}/state/{sid}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByFriendIdAndState(@PathVariable int id,@PathVariable int sid) {
		return orderService.findByFriendIdAndState(id, sid);
	}
	
	@RequestMapping(value = "find/doctor/{id}/today", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByDoctorIdAndState(@PathVariable int id) {
		return orderService.findByDoctorIdAndToday(id);
	}
	
	@RequestMapping(value = "find/friend/{id}/today", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByFriendIdAndState(@PathVariable int id) {
		return orderService.findByFriendIdAndToday(id);
	}
}

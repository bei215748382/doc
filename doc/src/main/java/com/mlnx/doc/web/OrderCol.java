package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.Order;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.service.OrderService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/orders")
public class OrderCol {

	@Autowired
	private OrderService orderService;

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<Order> findAll() {
		return orderService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody Order order) {
		int id = order.getFriend_id();
		Doctor doctor = doctorService.get(id);
		order.setDoctor_name(doctor.getName());
		order.setHospital_name(doctor.getHospital());
		order.setState(0);// 状态为0表示未接受
		orderService.save(order);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.ORDER_REGISTER_SUSSESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.ORDER_REGISTER_SUSSESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/doctor/{id}/state/{sid}/orders.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByDoctorIdAndState(@PathVariable int id,
			@PathVariable int sid) {
		return orderService.findByDoctorIdAndState(id, sid);
	}

	@RequestMapping(value = "find/friend/{id}/state/{sid}/orders.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByFriendIdAndState(@PathVariable int id,
			@PathVariable int sid) {
		return orderService.findByFriendIdAndState(id, sid);
	}

	@RequestMapping(value = "find/doctor/{id}/today.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByDoctorIdAndToday(@PathVariable int id) {
		return orderService.findByDoctorIdAndToday(id);
	}

	@RequestMapping(value = "find/friend/{id}/today.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByFriendIdAndToday(@PathVariable int id) {
		return orderService.findByFriendIdAndToday(id);
	}

	@RequestMapping(value = "update/{id}/remind.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response updateRemind(@PathVariable int id) {
		orderService.updateRemind(id);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.ORDER_REMIND_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.ORDER_REMIND_SUCCESS
				.getMsg());
		return response;
	}
}

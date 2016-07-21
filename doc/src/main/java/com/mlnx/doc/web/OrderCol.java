package com.mlnx.doc.web;

import java.util.Date;
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
import com.mlnx.doc.vo.OrderInfo;

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

	/**
	 * 预约
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/{date}/register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@PathVariable long date, @RequestBody Order order) {
		int id = order.getFriend_id();
		Doctor doctor = doctorService.get(id);
		order.setDoctor_name(doctor.getName());
		order.setHospital_name(doctor.getHospital());
		order.setState(0);// 状态为0表示未接受
		order.setDate(new Date(date));
		orderService.save(order);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.ORDER_REGISTER_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.ORDER_REGISTER_SUCCESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/doctor/{id}/state/{sid}/orders.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByDoctorIdAndState(@PathVariable int id,
			@PathVariable int sid) {
		if(sid ==0){//如果是未接受的话，过期的不再返回给客户端
			return orderService.findByDoctorIdAndStateAndValid(id);
		} else{
			return orderService.findByDoctorIdAndState(id, sid);
		}
	}

	@RequestMapping(value = "find/friend/{id}/state/{sid}/orders.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Order> findByFriendIdAndState(@PathVariable int id,
			@PathVariable int sid) {
		if(sid == 0){//如果是未接受的话，过期的不再返回给客户端
			return orderService.findByFriendIdAndStateAndValid(id);
		} else{
			return orderService.findByFriendIdAndState(id, sid);
		}
		
	}
	
	@RequestMapping(value = "ios/find/doctor/{id}/state/{sid}/orders.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<OrderInfo> iosFindByDoctorIdAndState(@PathVariable int id,
			@PathVariable int sid) {
		if(sid ==0){//如果是未接受的话，过期的不再返回给客户端
			return orderService.iosFindByDoctorIdAndStateAndValid(id);
		} else{
			return orderService.iosFindByDoctorIdAndState(id, sid);
		}
	}

	@RequestMapping(value = "ios/find/friend/{id}/state/{sid}/orders.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<OrderInfo> iosFindByFriendIdAndState(@PathVariable int id,
			@PathVariable int sid) {
		if(sid == 0){//如果是未接受的话，过期的不再返回给客户端
			return orderService.iosFindByFriendIdAndStateAndValid(id);
		} else{
			return orderService.iosFindByFriendIdAndState(id, sid);
		}
		
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
		return orderService.updateRemind(id);
	}

	@RequestMapping(value = "update/{id}/state.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response updateState(@PathVariable int id) {
		return orderService.updateState(id);
	}
}

package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.City;
import com.mlnx.doc.entity.Doctor_doctor;
import com.mlnx.doc.service.CityService;
import com.mlnx.doc.service.Doctor_doctorService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/doctor_doctors")
public class Doctor_doctorCol {

	@Autowired
	private Doctor_doctorService doctor_doctorService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<Doctor_doctor> findAll() {
		return doctor_doctorService.findAll();
	}

	@RequestMapping(value = "addFriends/{id}/doctor.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response addFriends(@PathVariable int id,@RequestBody List<Integer> ids) {
		doctor_doctorService.addFriends(id, ids);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.ADD_FRIENDS_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.ADD_FRIENDS_SUCCESS
				.getMsg());
		return response;
	}

}

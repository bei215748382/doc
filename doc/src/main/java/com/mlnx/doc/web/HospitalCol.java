package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Hospital;
import com.mlnx.doc.service.HospitalService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/hospitals")
public class HospitalCol {

	@Autowired
	private HospitalService hospitalService;

	@RequestMapping(value = "all")
	@ResponseBody
	public List<Hospital> findAll() {
		return hospitalService.findAll();
	}

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody Hospital hospital) {
		hospitalService.save(hospital);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.HOSPITAL_REGISTER_SUSSESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.HOSPITAL_REGISTER_SUSSESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/city/{id}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Hospital> findByProvinceId(@PathVariable int id) {
		return hospitalService.findByCityId(id);
	}

	@RequestMapping(value = "find/name", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Hospital> findByName(@RequestBody String name) {
		return hospitalService.findByName(name);
	}
}

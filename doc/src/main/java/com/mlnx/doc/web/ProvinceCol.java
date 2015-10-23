package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Province;
import com.mlnx.doc.service.ProvinceService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;
@Controller
@RequestMapping(value = "/provinces")
public class ProvinceCol {

	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "all")
	@ResponseBody
	public List<Province> findAll() {
		return provinceService.findAll();
	}
	
	@RequestMapping(value = "register",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public Response register(@RequestBody Province province){
		provinceService.save(province);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.PROVINCE_REGISTER_SUCCESS.getCode());
		response.setMsg(EnumCollection.ResponseCode.PROVINCE_REGISTER_SUCCESS.getMsg());
		return response;
	}
	
	@RequestMapping(value = "find/name", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Province> findByProvinceId(@RequestBody String name) {
		return provinceService.findByName(name);
	}
}

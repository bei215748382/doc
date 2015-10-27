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
import com.mlnx.doc.service.CityService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/cities")
public class CityCol {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<City> findAll() {
		return cityService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody City city) {
		cityService.save(city);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.CITY_REGISTER_SUSSESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.CITY_REGISTER_SUSSESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/province/{id}/cities.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<City> findByProvinceId(@PathVariable int id) {
		return cityService.findByProvinceId(id);
	}
	
	@RequestMapping(value = "find/name.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<City> findByProvinceId(@RequestBody String name) {
		return cityService.findByName(name);
	}
}

package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Domain;
import com.mlnx.doc.service.DomainService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/domains")
public class DomainCol {

	@Autowired
	private DomainService domainService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<Domain> findAll() {
		return domainService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody Domain domain) {
		domainService.save(domain);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.DOMAIN_REGISTER_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.DOMAIN_REGISTER_SUCCESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/hospital/{id}/domains.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Domain> findByHospitalId(@PathVariable int id) {
		return domainService.findByHospitalId(id);
	}
	
	@RequestMapping(value = "find/name.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Domain> findByName(@RequestBody String name) {
		return domainService.findByName(name);
	}
}

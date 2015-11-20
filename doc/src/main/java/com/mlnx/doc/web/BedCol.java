package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Bed;
import com.mlnx.doc.service.BedService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/beds")
public class BedCol {

	@Autowired
	private BedService bedService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<Bed> findAll() {
		return bedService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody Bed bed) {
		bedService.save(bed);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.BED_REGISTER_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.BED_REGISTER_SUCCESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/room/{id}/beds.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Bed> findByRoomId(@PathVariable int id) {
		return bedService.findByRoomId(id);
	}
	
	@RequestMapping(value = "find/name.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Bed> findByName(@RequestBody String name) {
		return bedService.findByName(name);
	}
}

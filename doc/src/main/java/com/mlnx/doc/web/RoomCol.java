package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Room;
import com.mlnx.doc.service.RoomService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/rooms")
public class RoomCol {

	@Autowired
	private RoomService roomService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<Room> findAll() {
		return roomService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody Room room) {
		roomService.save(room);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.ROOM_REGISTER_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.ROOM_REGISTER_SUCCESS
				.getMsg());
		return response;
	}

	@RequestMapping(value = "find/domain/{id}/rooms.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Room> findByDomainId(@PathVariable int id) {
		return roomService.findByDomainId(id);
	}
	
	@RequestMapping(value = "find/name.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Room> findByName(@RequestBody String name) {
		return roomService.findByName(name);
	}
}

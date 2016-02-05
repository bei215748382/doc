package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.doc.util.VideoUtil;
import com.mlnx.doc.video.MedicalServer;
import com.mlnx.doc.video.MeetingRoom;

@Controller
@RequestMapping(value = "/video")
public class VideoCol {
	
	@Autowired
	private MedicalServer server;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("video/zy");
		List<MeetingRoom> list = server.getServer().getMeetingRoomList(server.getUser(), "");
		mav.addObject("rooms", list);
		return mav;
	}
	
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String index2() {
		return "video/zy2";
	}

	@RequestMapping(value = "get_meet_place", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object> get_meet_place(int YYSPID) throws Exception {
		try {
			List<Object> msgdata = VideoUtil.getMeetPlace(server,YYSPID);
			return msgdata;
		} catch (Exception e) {
			throw new Exception();
		}
	}

}

package com.mlnx.doc.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.ClientLog;
import com.mlnx.doc.service.ClientLogService;
import com.mlnx.doc.util.Response;
@Controller
@RequestMapping(value = "/clientLog")
public class ClientLogCol {
	
	@Autowired
	private ClientLogService clientLogService;
	
	@RequestMapping(value = "add/{date}/log.do",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response add(@PathVariable long date,@RequestBody String content) {
		ClientLog clientLog = new ClientLog();
		clientLog.setDate(new Date(date));
		clientLog.setContent(content);
		clientLog.setType(1);
		return clientLogService.save(clientLog);
	}
}

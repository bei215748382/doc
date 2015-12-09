package com.mlnx.doc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.doc.entity.ClientLog;
import com.mlnx.doc.service.ClientLogService;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/log")
public class LogManagerCol {
	
	@Autowired
	private ClientLogService clientLogService;
	
	// 登陆提交
	// userid：用户账号，pwd：密码
	@RequestMapping("/login")
	public String loginsubmit(HttpSession session, String username,
			String password) throws Exception {

		// 向session记录用户身份信息
		// TODO 进行用户登入验证、权限验证等等
		if (username.equals("dan") && password.equals("dan")) {
			session.setAttribute("activeUserLog", username);
		}
		return "redirect:index.do";
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// session过期
		session.invalidate();

		return "redirect:login.do";
	}
	
	@RequestMapping(value = "index.do")
	public String index() {
		return "log/index";
	}
	
	@RequestMapping(value = "index_info.do")
	public ModelAndView index_info(HttpServletRequest request,
			HttpServletResponse response) {
		List<ClientLog> cls = clientLogService.findAll();
		ModelAndView modelAndView = new ModelAndView("log/ajax/logs_info");
		modelAndView.addObject("logs", cls);
		return modelAndView;
	}
	
	@RequestMapping(value = "delete_client_log.do")
	@ResponseBody
	public Response delete(int id) {
		return clientLogService.delete(id);
	}

}

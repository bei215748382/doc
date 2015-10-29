package com.mlnx.doc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.service.DoctorService;

@Controller
@RequestMapping(value = "/admin")
public class AdminCol {
	
	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "index.do")
	public String index() {
		return "admin/index";
	}
	
	/**
	 * 注册医生账号
	 * @param doctor
	 * @return
	 */
	@RequestMapping(value = "doctor_add_json.do",method = RequestMethod.POST)
	public String doctor_add_json(Doctor doctor) {
		doctorService.registerByUsername(doctor);
		return "admin/index";
	}
	
	@RequestMapping(value = "doctors_info.do")
	public ModelAndView tables_datatables(HttpServletRequest request,  
	        HttpServletResponse response) {
		List<Doctor> doctors = doctorService.findAll();
		
		ModelAndView modelAndView = new ModelAndView("admin/ajax/doctors_info");  
        modelAndView.addObject("doctors", doctors);  
		return modelAndView;
	}
	
	@RequestMapping(value = "doctor_add.do")
	public String tables_beauty() {
		return "admin/ajax/doctor_add";
	}
	
}

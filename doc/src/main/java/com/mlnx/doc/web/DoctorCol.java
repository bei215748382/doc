package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.service.DoctorService;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorCol {

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "all")
	@ResponseBody
	public List<Doctor> findAll() {
		return doctorService.findAll();
	}
}

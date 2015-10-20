package com.scu.book.shop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scu.book.shop.entity.Doctor;
import com.scu.book.shop.service.DoctorService;

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

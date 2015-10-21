package com.mlnx.doc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mlnx.doc.service.DoctorService;

@Controller
@RequestMapping(value = "/admin")
public class AdminCol {

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping(value = "tables_simple")
	public String tables_simple() {
		return "admin/ajax/tables_simple";
	}
	
	@RequestMapping(value = "tables_datatables")
	public String tables_datatables() {
		return "admin/ajax/tables_datatables";
	}
	
	@RequestMapping(value = "tables_beauty")
	public String tables_beauty() {
		return "admin/ajax/tables_beauty";
	}
	
}

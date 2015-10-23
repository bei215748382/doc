package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorCol {

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "all",method=RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Doctor> findAll() {
		return doctorService.findAll();
	}
	
	@RequestMapping(value = "register",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public Response register(@RequestBody Doctor doctor){
		return doctorService.register(doctor);
	}
	
	@RequestMapping(value = "find/name",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Doctor> findByName(@RequestBody String name){
		return doctorService.findByName(name);
	}
	
	@RequestMapping(value = "find/phone",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Doctor> findByPhone(@RequestBody String phone){
		return doctorService.findByPhone(phone);
	}
	
	@RequestMapping(value = "find/hospital/{id}",method=RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Doctor> findByHospitalId(@PathVariable int id){
		return doctorService.findByHospitalId(id);
	}
	
	@RequestMapping(value = "find/{id}",method=RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseBody
	public Doctor findById(@PathVariable int id){
		return doctorService.get(id);
	}
	
	@RequestMapping(value = "find/doctor/{id}",method=RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseBody
	public Doctor findByDoctorId(@PathVariable int id){
		return doctorService.get(id);
	}
}

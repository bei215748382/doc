package com.mlnx.doc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@RequestMapping(value = "all.do",method=RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Doctor> findAll() {
		return doctorService.findAll();
	}
	
	@RequestMapping(value = "register.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public Response register(@RequestBody Doctor doctor){
		return doctorService.register(doctor);
	}
	
	@RequestMapping(value = "modify.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public Response modify(@RequestBody Doctor doctor){
		return doctorService.modify(doctor);
	}
	
	@RequestMapping(value = "login.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public Map<String,String> login(@RequestBody Doctor doctor){
		return doctorService.login(doctor);
	}
	
	@RequestMapping(value = "find/name.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Doctor> findByName(@RequestBody String name){
		return doctorService.findByName(name);
	}

	@RequestMapping(value = "find/phone.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Doctor> findByPhone(@RequestBody String phone){
		return doctorService.findByPhone(phone);
	}
	
	@RequestMapping(value = "find/username",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Map<String,Boolean> findByUsername(String username){
		boolean valid = doctorService.findByUsername(username);
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", valid);
		return map;
	}
	
	@RequestMapping(value = "find/regist/phone",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Map<String,Boolean> findByRegistPhone(String phone){
		boolean valid = doctorService.findByRegistPhone(phone);
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", valid);
		return map;
	}
	@RequestMapping(value = "find/hospital/{id}/doctors.do",method=RequestMethod.GET)
	@ResponseBody
	public List<Doctor> findByHospitalId(@PathVariable int id){
		return doctorService.findByHospitalId(id);
	}
	
	@RequestMapping(value = "find/{id}/doctor.do",method=RequestMethod.GET)
	@ResponseBody
	public Doctor findById(@PathVariable int id){
		return doctorService.get(id);
	}
	
	@RequestMapping(value = "find/{id}/friends.do",method=RequestMethod.GET)
	@ResponseBody
	public List<Doctor> findByDoctorId(@PathVariable int id){
		return doctorService.findByDoctorId(id);
	}
	
	@RequestMapping(value = "find/voip/{voip}/doctor.do",method=RequestMethod.GET)
	@ResponseBody
	public Doctor findByVoipAccount(@PathVariable String voip){
		return doctorService.findByVoipAccount(voip);
	}
}

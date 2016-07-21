package com.mlnx.doc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.State;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorCol {

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "all.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Doctor> findAll() {
		return doctorService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response register(@RequestBody Doctor doctor) {
		return doctorService.register(doctor);
	}

	@RequestMapping(value = "modify.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response modify(@RequestBody Doctor doctor) {
		return doctorService.modify(doctor);
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> login(@RequestHeader("phone") String phone,@RequestHeader("password") String password,
			@RequestHeader("state") int state,HttpServletRequest request) {
		System.out.println(request.getContentType());
		return doctorService.login(phone,password,state);
	}
	
	@RequestMapping(value = "ios/login.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> iosLogin(@RequestHeader("phone") String phone,@RequestHeader("password") String password,
			@RequestHeader("state") int state,HttpServletRequest request) {
		return doctorService.iosLogin(phone,password,state);
	}
	
	@RequestMapping(value = "modify/password.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> modifyPassword(@RequestHeader("phone") String phone,@RequestHeader("old_password") String old_password,@RequestHeader("new_password") String new_password) {
		return doctorService.modifyPassword(phone,old_password,new_password);
	}

	@RequestMapping(value = "find/name.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Doctor> findByName(@RequestBody String name) {
		return doctorService.findByName(name);
	}

	@RequestMapping(value = "find/phone.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Doctor> findByPhone(@RequestBody String phone) {
		return doctorService.findByPhone(phone);
	}

	@RequestMapping(value = "find/username", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> findByUsername(String username) {
		boolean valid = doctorService.findByUsername(username);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", valid);
		return map;
	}

	@RequestMapping(value = "find/regist/phone", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> findByRegistPhone(String phone) {
		boolean valid = doctorService.findByRegistPhone(phone);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", valid);
		return map;
	}

	@RequestMapping(value = "find/hospital/{id}/doctors.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Doctor> findByHospitalId(@PathVariable int id) {
		return doctorService.findByHospitalId(id);
	}

	@RequestMapping(value = "find/{id}/doctor.do", method = RequestMethod.GET)
	@ResponseBody
	public Doctor findById(@PathVariable int id) {
		return doctorService.get(id);
	}

	@RequestMapping(value = "find/{id}/friends.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Doctor> findByDoctorId(@PathVariable int id) {
		return doctorService.findByDoctorId(id);
	}

	@RequestMapping(value = "find/voip/{voip}/doctor.do", method = RequestMethod.GET)
	@ResponseBody
	public Doctor findByVoipAccount(@PathVariable String voip) {
		return doctorService.findByVoipAccount(voip);
	}

	@RequestMapping(value = "find/state.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findDoctorState(@RequestHeader("id") int id) {
		return doctorService.findDoctorState(id);
	}

	@RequestMapping(value = "update/state.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateDoctorState(@RequestHeader("id") int id,
			@RequestHeader("state") int state) {
		State st = new State();
		st.setDoctor_id(id);
		st.setState(state);
		return doctorService.updateDoctorState(st);
	}

}

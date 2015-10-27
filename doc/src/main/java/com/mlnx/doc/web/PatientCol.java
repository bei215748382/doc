package com.mlnx.doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.doc.entity.Patient;
import com.mlnx.doc.service.PatientService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;

@Controller
@RequestMapping(value = "/patients")
public class PatientCol {

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<Patient> findAll() {
		return patientService.findAll();
	}
	
	@RequestMapping(value = "register.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public Response register(@RequestBody Patient patient){
		patientService.save(patient);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.PATIENT_REGISTER_SUSSESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.PATIENT_REGISTER_SUSSESS
				.getMsg());
		return response;
	}
	
	@RequestMapping(value = "modify.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public Response modify(@RequestBody Patient patient){
		patientService.save(patient);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.PATIENT_MODIFY_SUSSESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.PATIENT_MODIFY_SUSSESS
				.getMsg());
		return response;
	}
	
	@RequestMapping(value = "find/doctor/{id}/state.do",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public List<Patient> findByName(@PathVariable int id,@RequestBody String state){
		return patientService.findByStateAndDoctorId(state, id);
	}
	
	@RequestMapping(value = "find/{id}/patient.do",method=RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseBody
	public Patient findById(@PathVariable int id){
		return patientService.get(id);
	}
}

package com.mlnx.doc.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.datastax.driver.core.Session;
import com.mlnx.doc.entity.Bed;
import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.Hospital;
import com.mlnx.doc.entity.Patient;
import com.mlnx.doc.service.BedService;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.service.HospitalService;
import com.mlnx.doc.service.PatientService;
import com.mlnx.doc.util.EnumCollection.ResponseCode;
import com.mlnx.doc.util.FileUtil;
import com.mlnx.doc.util.Response;
import com.mlnx.doc.util.StringUtil;

@Controller
@RequestMapping(value = "/doc_admin")
public class DocManagerCol {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private BedService bedService;

	// 登陆提交
	// userid：用户账号，pwd：密码
	@RequestMapping("/login")
	public String loginsubmit(HttpSession session, String username,
			String password) throws Exception {
		Doctor doctor = new Doctor();
		doctor.setUsername(username);
		doctor.setPassword(password);
		Map<String, String> map = doctorService.login(doctor);
		// 向session记录用户身份信息
		// TODO 进行用户登入验证、权限验证等等
		if (map.get(StringUtil.responseCode).endsWith(
				ResponseCode.LOGIN_SUCCESS.getCode())) {
			String id = map.get(StringUtil.id);
			Doctor doc = doctorService.get(Integer.parseInt(id));
			session.setAttribute("activeDoc", doc);
		}
		return "redirect:index.do";
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// session过期
		session.invalidate();

		return "redirect:index.do";
	}

	@RequestMapping(value = "index.do")
	public String index() {
		return "doc_admin/index";
	}

	@RequestMapping(value = "index_info.do")
	public String index_info(HttpServletRequest request,
			HttpServletResponse response) {
		return "doc_admin/ajax/doctor_info";
	}

	@RequestMapping(value = "doctor_edit.do")
	public ModelAndView doctor_edit(int id) {
		Doctor doctor = doctorService.get(id);
		ModelAndView modelAndView = new ModelAndView(
				"doc_admin/ajax/doctor_edit");
		modelAndView.addObject("doctor", doctor);
		return modelAndView;
	}

	@RequestMapping(value = "doctor_edit_json.do")
	public void doctor_edit_json(MultipartFile file, Doctor doctor,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			doctor.setPic(pic);
		}
		Hospital hospital = hospitalService.get(doctor.getHospital_id());
		doctor.setHospital(hospital.getName());
		doctorService.update(doctor);
		session.setAttribute("activeDoc", doctor);
		response.sendRedirect("index.do");
	}

	@RequestMapping(value = "doctor_upload_pic.do")
	public ModelAndView doctor_upload_pic(int id) {
		Doctor doctor = doctorService.get(id);
		ModelAndView modelAndView = new ModelAndView(
				"doc_admin/ajax/doctor_upload_pic");
		modelAndView.addObject("doctor", doctor);
		return modelAndView;
	}

	@RequestMapping(value = "doctor_upload_pic_add.do")
	public void doctor_upload_pic_add(MultipartFile file, Doctor doctor,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		Doctor d = doctorService.get(doctor.getId());
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			d.setPic(pic);
		}
		doctorService.update(d);
		session.setAttribute("activeDoc", d);
		response.sendRedirect("index.do");
	}

	// -------------------------------------- 病人信息管理
	// ---------------------------------------
	@RequestMapping(value = "patients_info.do")
	public ModelAndView patients_info(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Doctor doctor = (Doctor) session.getAttribute("activeDoc");
		List<Patient> patients = patientService.findByDoctorId(doctor.getId());
		ModelAndView modelAndView = new ModelAndView(
				"doc_admin/ajax/patients_info");
		modelAndView.addObject("patients", patients);
		return modelAndView;
	}

	@RequestMapping(value = "patient_add.do")
	public String patient_add() {
		return "doc_admin/ajax/patient_add";
	}

	@RequestMapping(value = "patient_add_json.do", method = RequestMethod.POST)
	public void patient_add_json(MultipartFile file, Patient patient,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			patient.setPic(pic);
		}
		Bed bed = bedService.get(patient.getBed_id());
		patient.setBed(bed.getName());
		patientService.register(patient);
		response.sendRedirect("index.do#patients_info.do");
	}

	@RequestMapping(value = "patient_delete.do")
	@ResponseBody
	public Response patient_delete(int id, HttpServletResponse response)
			throws IOException {
		return patientService.delete(id);
	}

	@RequestMapping(value = "patient_edit")
	public ModelAndView patient_edit(int id) {
		Patient patient = patientService.get(id);
		ModelAndView modelAndView = new ModelAndView(
				"doc_admin/ajax/patient_edit");
		modelAndView.addObject("patient", patient);
		return modelAndView;
	}

	@RequestMapping(value = "patient_edit_json.do")
	public void patient_edit_json(MultipartFile file, Patient patient,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			patient.setPic(pic);
		}
		Bed bed = bedService.get(patient.getBed_id());
		patient.setBed(bed.getName());
		patientService.modifyPatient(patient);
		response.sendRedirect("index.do#patients_info.do");
	}
}

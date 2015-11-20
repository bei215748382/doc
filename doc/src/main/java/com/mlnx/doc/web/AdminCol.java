package com.mlnx.doc.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.mlnx.doc.entity.Bed;
import com.mlnx.doc.entity.City;
import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.Domain;
import com.mlnx.doc.entity.Hospital;
import com.mlnx.doc.entity.Patient;
import com.mlnx.doc.entity.Province;
import com.mlnx.doc.entity.Room;
import com.mlnx.doc.service.BedService;
import com.mlnx.doc.service.CityService;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.service.DomainService;
import com.mlnx.doc.service.HospitalService;
import com.mlnx.doc.service.PatientService;
import com.mlnx.doc.service.ProvinceService;
import com.mlnx.doc.service.RoomService;
import com.mlnx.doc.util.FileUtil;
import com.mlnx.doc.util.Response;
import com.mlnx.doc.util.StringUtil;

@Controller
@RequestMapping(value = "/admin")
public class AdminCol {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CityService cityService;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private DomainService domainService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private BedService bedService;

	@RequestMapping(value = "index.do")
	public String index() {
		return "admin/index";
	}

	/**
	 * 注册医生账号、包括上传头像
	 * 
	 * @param doctor
	 * @return
	 */
	@RequestMapping(value = "doctor_add_json.do", method = RequestMethod.POST)
	public String doctor_add_json(MultipartFile file,
			HttpServletRequest request, Doctor doctor) {
		if (file != null) {
			String pic = FileUtil.savePic(request, file);
			doctor.setPic(pic);
		}
		Hospital hospital = hospitalService.get(doctor.getHospital_id());
		doctor.setHospital(hospital.getName());
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
	public String doctor_add() {
		return "admin/ajax/doctor_add";
	}
	
	@RequestMapping(value = "doctor_edit.do")
	public ModelAndView doctor_edit(int id) {
		Doctor doctor = doctorService.get(id);
		ModelAndView modelAndView = new ModelAndView("admin/ajax/doctor_edit");
		modelAndView.addObject("doctor", doctor);
		return modelAndView;
	}
	
	@RequestMapping(value = "doctor_delete_json.do")
	@ResponseBody
	public Response doctor_delete_json(int id) {
		return doctorService.delete(id);
	}
	
	@RequestMapping(value = "doctor_edit_json.do")
	public void doctor_edit_json(MultipartFile file, Doctor doctor,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			doctor.setPic(pic);
		}
		Hospital hospital = hospitalService.get(doctor.getHospital_id());
		doctor.setHospital(hospital.getName());
		doctorService.update(doctor);
		response.sendRedirect("index.do#doctors_info.do");
	}
	// -------------------------------------- voip账号信息
	// ---------------------------------------
	@RequestMapping(value = "voip_all")
	public ModelAndView voip_all() {
		ModelAndView modelAndView = new ModelAndView("admin/ajax/voip_info");
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(StringUtil.accountSid, StringUtil.accountToken);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(StringUtil.appId);// 初始化应用ID
		result = restAPI.getSubAccounts("0", "100");

		System.out.println("SDKTestGetSubAccounts result=" + result);

		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
			modelAndView.addObject("data", data);
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
		}
		return modelAndView;
	}

	// -------------------------------------- 病人信息管理
	// ---------------------------------------
	@RequestMapping(value = "patients_info.do")
	public ModelAndView patients_info(HttpServletRequest request,
			HttpServletResponse response) {
		List<Patient> patients = patientService.findAll();

		ModelAndView modelAndView = new ModelAndView("admin/ajax/patients_info");
		modelAndView.addObject("patients", patients);
		return modelAndView;
	}

	@RequestMapping(value = "patient_add.do")
	public ModelAndView patient_add() {
		List<Doctor> doctors = doctorService.findAll();

		ModelAndView modelAndView = new ModelAndView("admin/ajax/patient_add");
		modelAndView.addObject("doctors", doctors);
		return modelAndView;
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
		ModelAndView modelAndView = new ModelAndView("admin/ajax/patient_edit");
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

	// -------------------------------------- 字典信息管理
	// ---------------------------------------
	@RequestMapping(value = "dictionary_info.do")
	public ModelAndView dictionary_info(HttpServletRequest request,
			HttpServletResponse response) {
		List<Province> provinces = provinceService.findAll();
		List<City> cities = cityService.findAll();
		List<Hospital> hospitals = hospitalService.findAll();
		List<Domain> domains = domainService.findAll();
		List<Room> rooms = roomService.findAll();
		List<Bed> beds = bedService.findAll();
		ModelAndView modelAndView = new ModelAndView(
				"admin/ajax/dictionary_info");
		modelAndView.addObject("provinces", provinces);
		modelAndView.addObject("cities", cities);
		modelAndView.addObject("hospitals", hospitals);
		modelAndView.addObject("domains", domains);
		modelAndView.addObject("rooms", rooms);
		modelAndView.addObject("beds", beds);
		return modelAndView;
	}

	@RequestMapping(value = "dictionary_add.do")
	public ModelAndView dictionary_add(HttpServletRequest request) {
		String title = request.getParameter("title");
		String foreign_id = request.getParameter("foreign_id");
		ModelAndView modelAndView = new ModelAndView(
				"admin/ajax/dictionary_add");
		modelAndView.addObject("title", title);
		modelAndView.addObject("foreign_id", foreign_id);
		return modelAndView;
	}

	@RequestMapping(value = "dictionary_add_json.do")
	public void dictionary_add_json(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String foreign_id = request.getParameter("foreign_id");
		int id = 0;
		if (foreign_id != null) {
			id = Integer.parseInt(foreign_id);
		}
		switch (type) {
		case "province":
			Province province = new Province();
			province.setName(name);
			provinceService.save(province);
			break;
		case "city":
			City city = new City();
			city.setName(name);
			city.setProvince_id(id);
			cityService.save(city);
			break;
		case "hospital":
			Hospital hospital = new Hospital();
			hospital.setName(name);
			hospital.setCity_id(id);
			hospitalService.save(hospital);
			break;
		case "domain":
			Domain domain = new Domain();
			domain.setName(name);
			domain.setHospital_id(id);
			domainService.save(domain);
			break;
		case "room":
			Room room = new Room();
			room.setName(name);
			room.setDomain_id(id);
			roomService.save(room);
			break;
		case "bed":
			Bed bed = new Bed();
			bed.setName(name);
			bed.setRoom_id(id);
			bedService.save(bed);
			break;

		}
		response.sendRedirect("index.do#dictionary_info.do");
	}
	
	@RequestMapping(value = "dictionary_delete_json.do")
	public void dictionary_delete_json(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
		String primary_id = request.getParameter("primary_id");
		int id = 0;
		if (primary_id != null) {
			id = Integer.parseInt(primary_id);
		}
		switch (type) {
		case "province":
			provinceService.delete(id);
			break;
		case "city":
			cityService.delete(id);
			break;
		case "hospital":
			hospitalService.delete(id);
			break;
		case "domain":
			domainService.delete(id);
			break;
		case "room":
			roomService.delete(id);
			break;
		case "bed":
			bedService.delete(id);
			break;

		}
		response.sendRedirect("index.do#dictionary_info.do");
	}
	
	@RequestMapping(value = "dictionary_edit.do")
	public ModelAndView dictionary_edit(HttpServletRequest request) {
		String title = request.getParameter("title");
		String primary_id = request.getParameter("primary_id");
		String name = request.getParameter("name");
		ModelAndView modelAndView = new ModelAndView(
				"admin/ajax/dictionary_edit");
		modelAndView.addObject("title", title);
		modelAndView.addObject("primary_id", primary_id);
		modelAndView.addObject("name", name);
		return modelAndView;
	}
	
	@RequestMapping(value = "dictionary_edit_json.do")
	public void dictionary_edit_json(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String primary_id = request.getParameter("primary_id");
		int id = 0;
		if (primary_id != null) {
			id = Integer.parseInt(primary_id);
		}
		switch (type) {
		case "province":
			Province province = provinceService.get(id);
			province.setName(name);
			provinceService.update(province);
			break;
		case "city":
			City city = cityService.get(id);
			city.setName(name);
			cityService.update(city);
			break;
		case "hospital":
			Hospital hospital = hospitalService.get(id);
			hospital.setName(name);
			hospitalService.update(hospital);
			break;
		case "domain":
			Domain domain = domainService.get(id);
			domain.setName(name);
			domainService.update(domain);
			break;
		case "room":
			Room room = roomService.get(id);
			room.setName(name);
			roomService.update(room);
			break;
		case "bed":
			Bed bed = bedService.get(id);
			bed.setName(name);
			bedService.update(bed);
			break;

		}
		response.sendRedirect("index.do#dictionary_info.do");
	}
}

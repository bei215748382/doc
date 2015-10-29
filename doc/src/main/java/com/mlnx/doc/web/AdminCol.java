package com.mlnx.doc.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.util.StringUtil;

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
	 * 注册医生账号、包括上传头像
	 * 
	 * @param doctor
	 * @return
	 */
	@RequestMapping(value = "doctor_add_json.do", method = RequestMethod.POST)
	public String doctor_add_json(MultipartFile file,
			HttpServletRequest request,Doctor doctor) {
		String path = request.getSession().getServletContext()
				.getRealPath("/");
		int a = path.indexOf("webapps");
		String realPath = path.substring(0,a+7)+"/upload";//指定项目同级下的一个目录，永久保存
		String fileName = file.getOriginalFilename();
		// String fileName = new Date().getTime()+".jpg";
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String s =  "/upload/"+ fileName ;
		doctor.setPic(s);
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
			modelAndView.addObject("data",data);
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
		}
		return modelAndView;
	}
}

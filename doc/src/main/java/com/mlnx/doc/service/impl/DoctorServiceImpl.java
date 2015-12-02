package com.mlnx.doc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.Hospital;
import com.mlnx.doc.repository.DoctorDao;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;
import com.mlnx.doc.util.StringUtil;

@Service
public class DoctorServiceImpl implements DoctorService {

	private static Logger log = LoggerFactory
			.getLogger(DoctorServiceImpl.class);

	private String table_name = "t_doctor";

	@Autowired
	private DoctorDao doctorDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Doctor> list(Pageable pageable) {

		return doctorDao.findAll(pageable);
	}

	@Override
	public void save(Doctor doctor) {
		doctorDao.save(doctor);
	}

	@Override
	public Doctor get(Integer id) {
		return doctorDao.findOne(id);
	}

	@Override
	public Response delete(Integer id) {
		doctorDao.delete(id);
		return new Response(EnumCollection.ResponseCode.DELETE_DOCTOR_SUCCESS);

	}

	@Override
	public List<Doctor> findAll() {
		return doctorDao.findAll();
	}

	@Override
	public Response register(Doctor doctor) {
		Response response = new Response();
		String sqlString = "select * from t_doctor where phone = "
				+ doctor.getPhone();
		Query query = em.createNativeQuery(sqlString);
		List<Doctor> list = query.getResultList();
		if (list != null && list.size() != 0) {
			response.setResponseCode(EnumCollection.ResponseCode.EXIST
					.getCode());
			response.setMsg(EnumCollection.ResponseCode.EXIST.getMsg());
			return response;
		}
		String voipAccount = null;
		String voipPassword = null;
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(StringUtil.accountSid, StringUtil.accountToken);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(StringUtil.appId);// 初始化应用ID
		result = restAPI.createSubAccount(doctor.getPhone());
		log.info("SDKTestCreateSubAccount result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				log.info(key + " = " + object);
				String[] s = object.toString().split(",");
				String[] a = s[1].split("=");
				String[] b = s[3].split("=");
				voipAccount = a[1];
				voipPassword = b[1];
			}
		} else {
			// 异常返回输出错误码和错误信息
			log.info("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
			response.setResponseCode(EnumCollection.ResponseCode.VOIP_EXIST
					.getCode());
			response.setMsg(EnumCollection.ResponseCode.VOIP_EXIST.getMsg());
			return response;
		}
		doctor.setVoipAccount(voipAccount);
		doctor.setVoipPassword(voipPassword);
		doctorDao.save(doctor);
		response.setResponseCode(EnumCollection.ResponseCode.SUCCESS.getCode());
		response.setMsg(EnumCollection.ResponseCode.SUCCESS.getMsg());
		return response;
	}

	/**
	 * 根据用户名 注册voip账号
	 * 
	 * @param doctor
	 * @return
	 */
	public Response registerByUsername(Doctor doctor) {
		Response response = new Response();
		String sqlString = String.format(
				"select * from %s where username = '%s'", table_name,
				doctor.getUsername());
		Query query = em.createNativeQuery(sqlString);
		List<Doctor> list = query.getResultList();
		if (list != null && list.size() != 0) {
			response.setResponseCode(EnumCollection.ResponseCode.EXIST
					.getCode());
			response.setMsg(EnumCollection.ResponseCode.EXIST.getMsg());
			return response;
		}
		String voipAccount = null;
		String voipPassword = null;
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(StringUtil.accountSid, StringUtil.accountToken);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(StringUtil.appId);// 初始化应用ID
		result = restAPI.createSubAccount(doctor.getUsername());
		log.info("SDKTestCreateSubAccount result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				log.info(key + " = " + object);
				String[] s = object.toString().split(",");
				String[] a = s[1].split("=");
				String[] b = s[3].split("=");
				voipAccount = a[1];
				voipPassword = b[1];
			}
		} else {
			// 异常返回输出错误码和错误信息
			log.info("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
			response.setResponseCode(EnumCollection.ResponseCode.VOIP_EXIST
					.getCode());
			response.setMsg(EnumCollection.ResponseCode.VOIP_EXIST.getMsg());
			return response;
		}
		doctor.setVoipAccount(voipAccount);
		doctor.setVoipPassword(voipPassword);
		doctorDao.save(doctor);
		response.setResponseCode(EnumCollection.ResponseCode.SUCCESS.getCode());
		response.setMsg(EnumCollection.ResponseCode.SUCCESS.getMsg());
		return response;
	}

	@Override
	public List<Doctor> findByName(String name) {
		String sql = "SELECT * FROM t_doctor where name like '%" + name + "%'";
		// 创建原生SQL查询QUERY实例,指定了返回的实体类型
		Query query = em.createNativeQuery(sql, Doctor.class);
		// 执行查询，返回的是实体列表,
		return query.getResultList();
	}

	@Override
	public List<Doctor> findByPhone(String phone) {
		String sql = "SELECT * FROM t_doctor where phone like '%" + phone
				+ "%'";
		// 创建原生SQL查询QUERY实例,指定了返回的实体类型
		Query query = em.createNativeQuery(sql, Doctor.class);
		// 执行查询，返回的是实体列表,
		return query.getResultList();
	}

	@Override
	public List<Doctor> findByHospitalId(Integer id) {
		String sql = "SELECT * FROM t_doctor where hospital_id = " + id;
		Query query = em.createNativeQuery(sql, Doctor.class);
		return query.getResultList();
	}

	@Override
	public List<Doctor> findByDoctorId(Integer id) {
		String sql = "SELECT * FROM t_doctor where id in (select friend_id from t_doctor_doctor where doctor_id = "
				+ id + ")";
		Query query = em.createNativeQuery(sql, Doctor.class);
		return query.getResultList();
	}

	/**
	 * 根据用户名或者手机号登入
	 */
	@Override
	public Map<String, String> login(Doctor doctor) {
		Map<String, String> map = new HashMap<String, String>();
		String sql = null;
		if (doctor.getUsername() != null && doctor.getUsername() != "") {
			sql = "SELECT * FROM t_doctor where username = '"
					+ doctor.getUsername() + "'";
		}
		if (doctor.getPhone() != null && doctor.getPhone() != "") {
			sql = "SELECT * FROM t_doctor where phone = '" + doctor.getPhone()
					+ "'";
		}
		Query query = em.createNativeQuery(sql, Doctor.class);
		try {
			Doctor d = (Doctor) query.getSingleResult();
			if (d != null) {
				if (doctor.getPassword().equals(d.getPassword())) {
					// 返回登入成功
					map.put("responseCode",
							EnumCollection.ResponseCode.LOGIN_SUCCESS.getCode());
					map.put("msg",
							EnumCollection.ResponseCode.LOGIN_SUCCESS.getMsg());
					map.put("id", d.getId() + "");
					return map;
				} else {
					// 返回用户名密码错误
					map.put("responseCode",
							EnumCollection.ResponseCode.LOGIN_PASSWORD_ERROR
									.getCode());
					map.put("msg",
							EnumCollection.ResponseCode.LOGIN_PASSWORD_ERROR
									.getMsg());
					return map;
				}
			}
		} catch (Exception e) {
			// 返回用户名不存在

		}
		map.put("responseCode",
				EnumCollection.ResponseCode.LOGIN_USERNAME_NOT_EXIST.getCode());
		map.put("msg",
				EnumCollection.ResponseCode.LOGIN_USERNAME_NOT_EXIST.getMsg());
		return map;
	}

	@Override
	public Response modify(Doctor doctor) {
		// TODO 未完成，要么修改全字段
		StringBuilder sql = new StringBuilder("UPDATE t_doctor SET ");
		if (doctor.getAchievement() != null) {
			sql.append("achievement = '" + doctor.getAchievement() + "'");
		}
		return new Response(EnumCollection.ResponseCode.DOCTOR_MODIFY_SUCCESS);
	}

	@Override
	public boolean findByUsername(String username) {
		String sql = String.format("SELECT * FROM %s WHERE username = '%s'",
				table_name, username);
		Query query = em.createNativeQuery(sql, Doctor.class);
		List<Doctor> doctors = query.getResultList();
		if (doctors != null && doctors.size() > 0) {
			return false;
		}
		return true;

	}

	@Transactional
	@Override
	public void update(Doctor doctor) {
		em.merge(doctor);
		em.flush();// 手动将更新立刻刷新进数据库
	}

	@Override
	public boolean findByRegistPhone(String phone) {
		String sql = String.format("SELECT * FROM %s WHERE phone = '%s'",
				table_name, phone);
		Query query = em.createNativeQuery(sql, Doctor.class);
		List<Doctor> doctors = query.getResultList();
		if (doctors != null && doctors.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public Doctor findByVoipAccount(String voip) {
		try {
			String sqlString = String.format(
					"select * from %s where voip_account = '%s'", table_name,
					voip);
			Query query = em.createNativeQuery(sqlString, Doctor.class);
			Doctor d = (Doctor) query.getSingleResult();
			return d;
		} catch (Exception e) {
			return null;
		}
	}
}

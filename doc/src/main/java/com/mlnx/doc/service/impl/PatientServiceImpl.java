package com.mlnx.doc.service.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.doc.entity.Patient;
import com.mlnx.doc.repo.PatientRepository;
import com.mlnx.doc.repository.PatientDao;
import com.mlnx.doc.service.PatientService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.HttpUtil;
import com.mlnx.doc.util.PropertiyUtil;
import com.mlnx.doc.util.Response;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private PatientRepository patientRepository;

	private static final Logger logger = LoggerFactory
			.getLogger(PatientServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Patient> list(Pageable pageable) {

		return patientDao.findAll(pageable);
	}

	@Override
	public void save(Patient patient) {
		patientDao.save(patient);
	}

	@Override
	public Patient get(Integer id) {
		return patientDao.findOne(id);
	}

	@Override
	public Response delete(Integer id) {
		patientDao.delete(id);
		return new Response(EnumCollection.ResponseCode.DELETE_PATIENT_SUCCESS);

	}

	@Override
	public List<Patient> findAll() {
		return patientDao.findAll();
	}

	@Override
	public List<Patient> findByName(String name) {
		String sqlString = "select * from t_patient where name like '%" + name
				+ "%'";
		Query query = em.createNativeQuery(sqlString, Patient.class);
		return query.getResultList();
	}

	@Override
	public List<Patient> findByDoctorId(Integer id) {
		String sqlString = "select * from t_patient where doctor_id = " + id;
		Query query = em.createNativeQuery(sqlString, Patient.class);
		return query.getResultList();
	}

	@Override
	public List<Patient> findByStateAndDoctorId(String state, Integer id) {
		String sqlString = "select * from t_patient where doctor_id = " + id
				+ " and state = '" + state + "'";
		Query query = em.createNativeQuery(sqlString, Patient.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void modifyPatient(Patient patient) {
		em.merge(patient);
		em.flush();// 手动将更新立刻刷新进数据库
	}

	@Override
	public Patient findByBedId(Integer id) {
		String sqlString = "select * from t_patient where bed_id = " + id;
		Query query = em.createNativeQuery(sqlString, Patient.class);
		Patient patient = (Patient) query.getSingleResult();
		return patient;
	}

	@Override
	public Response register(Patient patient) {
		com.mlnx.doc.model.Patient p = new com.mlnx.doc.model.Patient();
		int patientId = patientRepository.save(p);
		patient.setPatient_id(patientId);
		logger.info(String.format("patientId : %d", patientId));
		System.out.println(patientId);
		patientDao.save(patient);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.PATIENT_REGISTER_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.PATIENT_REGISTER_SUCCESS
				.getMsg());
		return response;
	}

	@Override
	public List<Integer> getOnlinePatientIds(Integer doctorId)
			throws UnsupportedEncodingException, DocumentException {
		String sr = HttpUtil
				.sendGet("http://121.40.137.14:8080/pms-server/rest/devices/online");
		if (PropertiyUtil.getInstance().isDebug()) {
			logger.debug(String.format("在线设备远程的访问情况为：\r\n%s", sr));
		}
		List<Integer> ids = new ArrayList<Integer>();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(sr
				.getBytes("UTF-8")));
		// 获取根元素
		Element root = document.getRootElement();
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			for (Iterator it = e.elementIterator(); it.hasNext();) {
				Element ee = (Element) it.next();
				if (ee.getName().equals("patientId")) {
					ids.add(Integer.parseInt(ee.getText()));
				}
			}
		}
		if (ids.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for (int i = 0; i < ids.size() - 1; i++) {
				sb.append(ids.get(i)+",");
			}
			sb.append(ids.get(ids.size() - 1) + ")");
			String sqlString = String.format(
					"select id from t_patient where doctor_id=%d and patient_id in %s",doctorId,
					sb.toString());
			Query query = em.createNativeQuery(sqlString);
			return query.getResultList();
		} else {
			return null;
		}
	}
}

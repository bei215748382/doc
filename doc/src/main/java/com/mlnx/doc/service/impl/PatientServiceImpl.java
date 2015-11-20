package com.mlnx.doc.service.impl;

import java.util.List;

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

import com.mlnx.doc.entity.Patient;
import com.mlnx.doc.repo.PatientRepository;
import com.mlnx.doc.repository.PatientDao;
import com.mlnx.doc.service.PatientService;
import com.mlnx.doc.util.EnumCollection;
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
		Query query = em.createNativeQuery(sqlString,Patient.class);
		return query.getResultList();
	}

	@Override
	public List<Patient> findByDoctorId(Integer id) {
		String sqlString = "select * from t_patient where doctor_id = " + id;
		Query query = em.createNativeQuery(sqlString,Patient.class);
		return query.getResultList();
	}

	@Override
	public List<Patient> findByStateAndDoctorId(String state, Integer id) {
		String sqlString = "select * from t_patient where doctor_id = " + id +" and state = '"+state+"'";
		Query query = em.createNativeQuery(sqlString,Patient.class);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public void modifyPatient(Patient patient) {
		em.merge(patient);
		em.flush();//手动将更新立刻刷新进数据库
	}

	@Override
	public Patient findByBedId(Integer id) {
		String sqlString = "select * from t_patient where bed_id = " + id;
		Query query = em.createNativeQuery(sqlString,Patient.class);
		Patient patient = (Patient) query.getSingleResult();
		return patient;
	}

	@Override
	public Response register(Patient patient) {
		com.mlnx.doc.model.Patient p = new com.mlnx.doc.model.Patient();
		int patientId = patientRepository.save(p);
		patient.setPatient_id(patientId);
		logger.info(String.format("patientId : %d",patientId));
		System.out.println(patientId);
		patientDao.save(patient);
		Response response = new Response();
		response.setResponseCode(EnumCollection.ResponseCode.PATIENT_REGISTER_SUCCESS
				.getCode());
		response.setMsg(EnumCollection.ResponseCode.PATIENT_REGISTER_SUCCESS
				.getMsg());
		return response;
	}
}

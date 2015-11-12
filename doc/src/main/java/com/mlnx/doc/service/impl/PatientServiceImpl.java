package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.doc.entity.Patient;
import com.mlnx.doc.repository.PatientDao;
import com.mlnx.doc.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;

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
	public void delete(Integer id) {
		patientDao.delete(id);

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

	@Override
	public void modifyPatient(Patient patient) {
		em.merge(patient);
	}
}

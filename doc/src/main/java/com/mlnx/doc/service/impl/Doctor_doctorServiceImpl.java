package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.doc.entity.Doctor_doctor;
import com.mlnx.doc.repository.Doctor_doctorDao;
import com.mlnx.doc.service.Doctor_doctorService;

@Service
public class Doctor_doctorServiceImpl implements Doctor_doctorService {

	@Autowired
	private Doctor_doctorDao doctor_doctorDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Doctor_doctor> list(Pageable pageable) {

		return doctor_doctorDao.findAll(pageable);
	}

	@Override
	public void save(Doctor_doctor city) {
		doctor_doctorDao.save(city);
	}

	@Override
	public Doctor_doctor get(Integer id) {
		return doctor_doctorDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		doctor_doctorDao.delete(id);

	}

	@Override
	public List<Doctor_doctor> findAll() {
		return doctor_doctorDao.findAll();
	}

	@Override
	public void addFriends(Integer id, List<Integer> ids) {
		for (Integer i : ids) {
			Doctor_doctor doctors = new Doctor_doctor();
			doctors.setDoctor_id(id);
			doctors.setFriend_id(i);
			doctor_doctorDao.save(doctors);
		}
	}

}

package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.doc.entity.Bed;
import com.mlnx.doc.entity.City;
import com.mlnx.doc.entity.Hospital;
import com.mlnx.doc.repository.HospitalDao;
import com.mlnx.doc.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Hospital> list(Pageable pageable) {

		return hospitalDao.findAll(pageable);
	}

	@Override
	public void save(Hospital city) {
		hospitalDao.save(city);
	}

	@Override
	public Hospital get(Integer id) {
		return hospitalDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		hospitalDao.delete(id);

	}

	@Override
	public List<Hospital> findAll() {
		return hospitalDao.findAll();
	}

	@Override
	public List<Hospital> findByName(String name) {
		String sqlString = "select * from t_hospital where name like '%" + name
				+ "%'";
		Query query = em.createNativeQuery(sqlString, Hospital.class);
		return query.getResultList();
	}

	@Override
	public List<Hospital> findByCityId(Integer id) {
		String sqlString = "select * from t_hospital where city_id = " + id;
		Query query = em.createNativeQuery(sqlString, Hospital.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void update(Hospital hospital) {
		String sqlString = String.format(
				"update t_hospital set name = '%s' where id = %d",
				hospital.getName(), hospital.getId());
		Query query = em.createNativeQuery(sqlString, Hospital.class);
		query.executeUpdate();
	}

	@Override
	public Hospital findByUName(String hospitalName) {
		try {
			String sqlString = String.format(
					"select * from t_hospital where name = '%s'", hospitalName);
			Query query = em.createNativeQuery(sqlString, Hospital.class);
			Hospital h = (Hospital) query.getSingleResult();
			return h;
		} catch (Exception e) {
			return null;
		}
	}

}

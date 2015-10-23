package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
		Query query = em.createNativeQuery(sqlString);
		return query.getResultList();
	}

	@Override
	public List<Hospital> findByCityId(Integer id) {
		String sqlString = "select * from t_hospital where city_id = " + id;
		Query query = em.createNativeQuery(sqlString);
		return query.getResultList();
	}

}

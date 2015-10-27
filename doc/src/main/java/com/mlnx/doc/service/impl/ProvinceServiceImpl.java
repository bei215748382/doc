package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.doc.entity.Province;
import com.mlnx.doc.repository.ProvinceDao;
import com.mlnx.doc.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceDao provinceDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Province> list(Pageable pageable) {

		return provinceDao.findAll(pageable);
	}

	@Override
	public void save(Province province) {
		provinceDao.save(province);
	}

	@Override
	public Province get(Integer id) {
		return provinceDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		provinceDao.delete(id);

	}

	@Override
	public List<Province> findAll() {
		return provinceDao.findAll();
	}

	@Override
	public List<Province> findByName(String name) {
		String sqlString = "select * from t_province where name like '%" + name
				+ "%'";
		Query query = em.createNativeQuery(sqlString);
		return query.getResultList();
	}

}

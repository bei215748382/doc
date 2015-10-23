package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.doc.entity.City;
import com.mlnx.doc.entity.Province;
import com.mlnx.doc.repository.CityDao;
import com.mlnx.doc.repository.ProvinceDao;
import com.mlnx.doc.service.CityService;
import com.mlnx.doc.service.ProvinceService;
import com.mlnx.doc.util.Response;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<City> list(Pageable pageable) {

		return cityDao.findAll(pageable);
	}

	@Override
	public void save(City city) {
		cityDao.save(city);
	}

	@Override
	public City get(Integer id) {
		return cityDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		cityDao.delete(id);

	}

	@Override
	public List<City> findAll() {
		return cityDao.findAll();
	}

	@Override
	public List<City> findByName(String name) {
		String sqlString = "select * from t_city where name like '%" + name
				+ "'%";
		Query query = em.createNativeQuery(sqlString);
		return query.getResultList();
	}

	@Override
	public List<City> findByProvinceId(Integer id) {
		String sqlString = "select * from t_city where province_id = " + id;
		Query query = em.createNativeQuery(sqlString);
		return query.getResultList();
	}

}

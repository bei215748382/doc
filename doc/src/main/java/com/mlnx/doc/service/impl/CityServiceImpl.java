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
		Query query = em.createNativeQuery(sqlString, City.class);
		return query.getResultList();
	}

	@Override
	public List<City> findByProvinceId(Integer id) {
		String sqlString = "select * from t_city where province_id = " + id;
		Query query = em.createNativeQuery(sqlString, City.class);
		List<City> cities = query.getResultList();
		return cities;
	}

	@Transactional
	@Override
	public void update(City city) {
		String sqlString = String.format(
				"update t_city set name = '%s' where id = %d", city.getName(),
				city.getId());
		Query query = em.createNativeQuery(sqlString, City.class);
		query.executeUpdate();
	}

	@Override
	public City findByUName(String cityName) {
		try {
			String sqlString = String.format(
					"select * from t_city where name = '%s'", cityName);
			Query query = em.createNativeQuery(sqlString, City.class);
			City c = (City) query.getSingleResult();
			return c;
		} catch (Exception e) {
			return null;
		}
	}

}

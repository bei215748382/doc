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
import com.mlnx.doc.repository.BedDao;
import com.mlnx.doc.service.BedService;

@Service
public class BedServiceImpl implements BedService {

	@Autowired
	private BedDao bedDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Bed> list(Pageable pageable) {

		return bedDao.findAll(pageable);
	}

	@Override
	public void save(Bed city) {
		bedDao.save(city);
	}

	@Override
	public Bed get(Integer id) {
		return bedDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		bedDao.delete(id);

	}

	@Override
	public List<Bed> findAll() {
		return bedDao.findAll();
	}

	@Override
	public List<Bed> findByName(String name) {
		String sqlString = "select * from t_bed where name like '%" + name
				+ "'%";
		Query query = em.createNativeQuery(sqlString,Bed.class);
		return query.getResultList();
	}

	@Override
	public List<Bed> findByRoomId(Integer id) {
		String sqlString = "select * from t_bed where room_id = " + id;
		Query query = em.createNativeQuery(sqlString,Bed.class);
		List<Bed> cities = query.getResultList();
		return cities;
	}

	@Transactional
	@Override
	public void update(Bed bed) {
		String sqlString =String.format("update t_bed set name = '%s' where id = %d",bed.getName(),bed.getId());
		Query query = em.createNativeQuery(sqlString,Bed.class);
		query.executeUpdate();
	}

}

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
import com.mlnx.doc.entity.Domain;
import com.mlnx.doc.entity.Domain;
import com.mlnx.doc.repository.DomainDao;
import com.mlnx.doc.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	private DomainDao domainDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Domain> list(Pageable pageable) {

		return domainDao.findAll(pageable);
	}

	@Override
	public void save(Domain domain) {
		domainDao.save(domain);
	}

	@Override
	public Domain get(Integer id) {
		return domainDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		domainDao.delete(id);

	}

	@Override
	public List<Domain> findAll() {
		return domainDao.findAll();
	}

	@Override
	public List<Domain> findByName(String name) {
		String sqlString = "select * from t_domain where name like '%" + name
				+ "'%";
		Query query = em.createNativeQuery(sqlString,Domain.class);
		return query.getResultList();
	}

	@Override
	public List<Domain> findByHospitalId(Integer id) {
		String sqlString = "select * from t_domain where hospital_id = " + id;
		Query query = em.createNativeQuery(sqlString,Domain.class);
		List<Domain> domains = query.getResultList();
		return domains;
	}
	@Transactional
	@Override
	public void update(Domain domain) {
		String sqlString =String.format("update t_domain set name = '%s' where id = %d",domain.getName(),domain.getId());
		Query query = em.createNativeQuery(sqlString,Domain.class);
		query.executeUpdate();
		
	}

}

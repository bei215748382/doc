package com.scu.book.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scu.book.shop.entity.Doctor;
import com.scu.book.shop.repository.DoctorDao;
import com.scu.book.shop.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorDao doctorDao;
	
	@Override
	public Page<Doctor> list(Pageable pageable) {
		
		return doctorDao.findAll(pageable);
	}

	@Override
	public void save(Doctor book) {
		doctorDao.save(book);
	}

	@Override
	public Doctor get(Integer id) {
		return doctorDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		doctorDao.delete(id);
		
	}

	@Override
	public List<Doctor> findAll(){
		return doctorDao.findAll();
	}
}

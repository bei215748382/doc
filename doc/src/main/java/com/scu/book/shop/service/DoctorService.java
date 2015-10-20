package com.scu.book.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scu.book.shop.entity.Doctor;

public interface DoctorService {

	/**
	 * 列表 
	 */
	Page<Doctor> list(Pageable pageable);
	
	/**
	 * 保存
	 */
	void save(Doctor doctor);

	/**
	 * 查询
	 */
	Doctor get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);
	
	List<Doctor> findAll();
}

package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Patient;

public interface PatientService {

	/**
	 * 列表 
	 */
	Page<Patient> list(Pageable pageable);
	
	/**
	 * 保存
	 */
	void save(Patient patient);

	/**
	 * 查询
	 */
	Patient get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);
	
	/**
	 * 查找所有
	 * @return
	 */
	List<Patient> findAll();
	
	/**
	 * 根据病人名模糊查找
	 * @param name
	 * @return
	 */
	List<Patient> findByName(String name);
	
	/**
	 * 根据医生id查找
	 * @param id
	 * @return
	 */
	List<Patient> findByDoctorId(Integer id);
	
	/**
	 * 根据医生id和当前状态查找病人
	 * @param state
	 * @param id
	 * @return
	 */
	List<Patient> findByStateAndDoctorId(String state,Integer id);
}

package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Hospital;

public interface HospitalService {

	/**
	 * 列表
	 */
	Page<Hospital> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(Hospital patient);

	/**
	 * 查询
	 */
	Hospital get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Hospital> findAll();

	/**
	 * 根据医院名称模糊查找
	 * 
	 * @param name
	 * @return
	 */
	List<Hospital> findByName(String name);

	/**
	 * 根据城市id查找
	 * 
	 * @param id
	 * @return
	 */
	List<Hospital> findByCityId(Integer id);

}

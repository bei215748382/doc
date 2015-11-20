package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Domain;

public interface DomainService {

	/**
	 * 列表
	 */
	Page<Domain> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(Domain patient);

	/**
	 * 查询
	 */
	Domain get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Domain> findAll();

	/**
	 * 根据名称模糊查找病区
	 * 
	 * @param name
	 * @return
	 */
	List<Domain> findByName(String name);

	/**
	 * 根据省id查找城市
	 * 
	 * @param id
	 * @return
	 */
	List<Domain> findByHospitalId(Integer id);

	/**
	 * 更新数据
	 * @param domain
	 */
	void update(Domain domain);

}

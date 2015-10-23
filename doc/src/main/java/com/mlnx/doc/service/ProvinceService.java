package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Province;

public interface ProvinceService {

	/**
	 * 列表 
	 */
	Page<Province> list(Pageable pageable);
	
	/**
	 * 保存
	 */
	void save(Province patient);

	/**
	 * 查询
	 */
	Province get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);
	
	/**
	 * 查找所有
	 * @return
	 */
	List<Province> findAll();
	
	/**
	 * 根据城市名模糊查找
	 * @param name
	 * @return
	 */
	List<Province> findByName(String name);

}

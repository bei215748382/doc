package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.City;

public interface CityService {

	/**
	 * 列表
	 */
	Page<City> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(City patient);

	/**
	 * 查询
	 */
	City get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<City> findAll();

	/**
	 * 根据名称模糊查找城市
	 * 
	 * @param name
	 * @return
	 */
	List<City> findByName(String name);

	/**
	 * 根据省id查找城市
	 * 
	 * @param id
	 * @return
	 */
	List<City> findByProvinceId(Integer id);

	/**
	 * 更新数据
	 * @param city
	 */
	void update(City city);

	/**
	 * 根据唯一城市名查找城市
	 * @param cityName
	 * @return
	 */
	City findByUName(String cityName);

}

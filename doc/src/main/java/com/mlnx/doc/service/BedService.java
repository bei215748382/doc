package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Bed;

public interface BedService {

	/**
	 * 列表
	 */
	Page<Bed> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(Bed patient);

	/**
	 * 查询
	 */
	Bed get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Bed> findAll();

	/**
	 * 根据名称模糊查找城市
	 * 
	 * @param name
	 * @return
	 */
	List<Bed> findByName(String name);

	/**
	 * 根据省id查找城市
	 * 
	 * @param id
	 * @return
	 */
	List<Bed> findByRoomId(Integer id);

	/**
	 * 更新数据
	 * 
	 * @param bed
	 */
	void update(Bed bed);

}

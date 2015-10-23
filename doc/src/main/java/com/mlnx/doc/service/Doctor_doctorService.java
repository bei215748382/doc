package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Doctor_doctor;

public interface Doctor_doctorService {

	/**
	 * 列表
	 */
	Page<Doctor_doctor> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(Doctor_doctor doctor_doctor);

	/**
	 * 查询
	 */
	Doctor_doctor get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Doctor_doctor> findAll();


	/**
	 * 根据好友列表批量添加
	 */
	void addFriends(Integer id,List<Integer> ids);

}

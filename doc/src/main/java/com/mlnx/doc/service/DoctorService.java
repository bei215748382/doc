package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.util.Response;

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
	
	/**
	 * 查找所有
	 * @return
	 */
	List<Doctor> findAll();
	
	/**
	 * 注册医生
	 * @param doctor
	 * @return
	 */
	Response register(Doctor doctor);

	/**
	 * 根据姓名模糊查找医生
	 * @param name
	 * @return
	 */
	List<Doctor> findByName(String name);

	/**
	 * 根据电话模糊查找医生
	 * @param phone
	 * @return
	 */
	List<Doctor> findByPhone(String phone);
	
	/**
	 * 根据医院id查找医生
	 * @param id
	 * @return
	 */
	List<Doctor> findByHospitalId(Integer id);
	
	/**
	 * 根据医生id查找好友医生
	 * @param id
	 * @return
	 */
	List<Doctor> findByDoctorId(Integer id);

	/**
	 * 用户登入
	 * @param doctor
	 * @return
	 */
	Response login(Doctor doctor);

	/**
	 * 修改个人信息
	 * @param doctor
	 * @return
	 */
	Response modify(Doctor doctor);
}

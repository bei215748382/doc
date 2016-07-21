package com.mlnx.doc.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.State;
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
	Response delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Doctor> findAll();

	/**
	 * 注册医生
	 * 
	 * @param doctor
	 * @return
	 */
	Response register(Doctor doctor);

	/**
	 * 注册医生
	 * 
	 * @param doctor
	 * @return
	 */
	Response registerByUsername(Doctor doctor);

	/**
	 * 根据姓名模糊查找医生
	 * 
	 * @param name
	 * @return
	 */
	List<Doctor> findByName(String name);

	/**
	 * 根据电话模糊查找医生
	 * 
	 * @param phone
	 * @return
	 */
	List<Doctor> findByPhone(String phone);

	/**
	 * 根据医院id查找医生
	 * 
	 * @param id
	 * @return
	 */
	List<Doctor> findByHospitalId(Integer id);

	/**
	 * 根据医生id查找好友医生
	 * 
	 * @param id
	 * @return
	 */
	List<Doctor> findByDoctorId(Integer id);

	/**
	 * 用户登入
	 * 
	 * @param doctor
	 * @return
	 */
	Map<String, String> login(Doctor doctor);

	/**
	 * 修改个人信息
	 * 
	 * @param doctor
	 * @return
	 */
	Response modify(Doctor doctor);

	/**
	 * 验证用户名是否已存在
	 * 
	 * @param username
	 * @return
	 */
	boolean findByUsername(String username);

	/**
	 * 更新医生信息
	 * 
	 * @param doctor
	 * @return
	 */
	void update(Doctor doctor);

	/**
	 * 验证手机号是否被注册
	 * 
	 * @param phone
	 * @return
	 */
	boolean findByRegistPhone(String phone);

	/**
	 * 根据voip账号获取医生详细信息
	 * 
	 * @param voip
	 * @return
	 */
	Doctor findByVoipAccount(String voip);

	/**
	 * 登入时候携带状态
	 * 
	 * @param doctor
	 * @param state
	 * @return
	 */
	Map<String, String> login(Doctor doctor, int state);
	
	/**
	 * 登入时候携带状态
	 * 
	 * @param doctor
	 * @param state
	 * @return
	 */
	Map<String, String> login(String phone,String password, int state);

	/**
	 * 登入时候携带状态
	 * 
	 * @param doctor
	 * @param state
	 * @return
	 */
	Map<String, Object> iosLogin(String phone,String password, int state);
	
	/**
	 * 查找医生状态
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> findDoctorState(int id);

	/**
	 * 更新医生登入状态
	 * 
	 * @param state
	 * @return
	 */
	Map<String, Object> updateDoctorState(State state);

	/**
	 * 修改密码
	 * @param old_password
	 * @param new_password
	 * @param phone 
	 * @return
	 */
	Map<String, String> modifyPassword(String phone,String old_password, String new_password);

}

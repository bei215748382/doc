package com.mlnx.doc.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.DocumentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Patient;
import com.mlnx.doc.util.Response;

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
	Response delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Patient> findAll();

	/**
	 * 根据病人名模糊查找
	 * 
	 * @param name
	 * @return
	 */
	List<Patient> findByName(String name);

	/**
	 * 根据医生id查找
	 * 
	 * @param id
	 * @return
	 */
	List<Patient> findByDoctorId(Integer id);

	/**
	 * 根据医生id和当前状态查找病人
	 * 
	 * @param state
	 * @param id
	 * @return
	 */
	List<Patient> findByStateAndDoctorId(String state, Integer id);

	/**
	 * 修改病人信息
	 */
	void modifyPatient(Patient patient);

	/**
	 * 根据床位号查找病人
	 * 
	 * @param id
	 * @return
	 */
	Patient findByBedId(Integer id);

	/**
	 * 注册病人，包括原服务器的病人注册
	 * 
	 * @param patient
	 * @return
	 */
	Response register(Patient patient);

	/**
	 * 获取在线病人id列表
	 * 
	 * @return
	 * @throws DocumentException 
	 * @throws UnsupportedEncodingException 
	 */
	List<Integer> getOnlinePatientIds(Integer doctorId) throws UnsupportedEncodingException, DocumentException;
}

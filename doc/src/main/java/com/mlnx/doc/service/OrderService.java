package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Order;

public interface OrderService {

	/**
	 * 列表
	 */
	Page<Order> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(Order order);

	/**
	 * 查询
	 */
	Order get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Order> findAll();

	/**
	 * 根据医生id查找order
	 * 
	 * @param name
	 * @return
	 */
	List<Order> findByDoctorId(Integer id);

	/**
	 * 根据医生id和接受状态查找order
	 * 
	 * @param id
	 * @return
	 */
	List<Order> findByDoctorIdAndState(Integer id, Integer state);

	/**
	 * 根据被预约医生id和接受状态查找order
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	List<Order> findByFriendIdAndState(Integer id, Integer state);
	
	/**
	 * 根据医生id和当天时间查找今日手术的order
	 * @param id
	 * @param date
	 * @return
	 */
	List<Order> findByDoctorIdAndToday(Integer id);
	
	/**
	 * 根据被预约的id和当天的时间查找
	 * @param id
	 * @param date
	 * @return
	 */
	List<Order> findByFriendIdAndToday(Integer id);
	
	/**
	 * 提醒操作
	 * @param id
	 */
	void updateRemind(Integer id);

}

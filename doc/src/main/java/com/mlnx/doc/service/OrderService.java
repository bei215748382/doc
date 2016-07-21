package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Order;
import com.mlnx.doc.util.Response;
import com.mlnx.doc.vo.OrderInfo;
import com.mlnx.doc.vo.OrderVo;

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
	 * 
	 * @return
	 */
	Response delete(Integer id);

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
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	List<Order> findByDoctorIdAndToday(Integer id);

	/**
	 * 根据被预约的id和当天的时间查找
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	List<Order> findByFriendIdAndToday(Integer id);

	/**
	 * 提醒操作
	 * 
	 * @param id
	 */
	Response updateRemind(Integer id);

	/**
	 * 更新预约表
	 * 
	 * @param order
	 * @return
	 */
	Response update(Order order);

	/**
	 * 根据状态查找预约列表
	 * 
	 * @return
	 */
	List<Order> findAllBystate(Integer id);

	/**
	 * 添加预约
	 * 
	 * @param order
	 * @return
	 */
	Response insert(OrderVo order);

	/**
	 * 接受预约
	 * 
	 * @param id
	 * @return
	 */
	Response updateState(Integer id);

	/**
	 * 返回不过期的预约列表
	 * 
	 * @param id
	 * @return
	 */
	List<Order> findByDoctorIdAndStateAndValid(int id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<Order> findByFriendIdAndStateAndValid(int id);

	/**
	 * 返回不过期的预约列表 IOS
	 * 
	 * @param id
	 * @return
	 */
	List<OrderInfo> iosFindByDoctorIdAndStateAndValid(int id);

	/**
	 * IOS
	 * @param id
	 * @return
	 */
	List<OrderInfo> iosFindByDoctorIdAndState(int id, int sid);

	/**
	 * IOS
	 * @param id
	 * @return
	 */
	List<OrderInfo> iosFindByFriendIdAndStateAndValid(int id);

	/**
	 * IOS
	 * @param id
	 * @param sid
	 * @return
	 */
	List<OrderInfo> iosFindByFriendIdAndState(int id, int sid);

}

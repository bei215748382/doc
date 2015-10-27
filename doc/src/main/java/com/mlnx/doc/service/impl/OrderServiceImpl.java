package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.doc.entity.Order;
import com.mlnx.doc.entity.Order;
import com.mlnx.doc.repository.OrderDao;
import com.mlnx.doc.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Order> list(Pageable pageable) {

		return orderDao.findAll(pageable);
	}

	@Override
	public void save(Order city) {
		orderDao.save(city);
	}

	@Override
	public Order get(Integer id) {
		return orderDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		orderDao.delete(id);

	}

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public List<Order> findByDoctorId(Integer id) {
		String sql = "SELECT * FROM t_order where doctor_id = " + id;
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByDoctorIdAndState(Integer id, Integer state) {
		String sql = "SELECT * FROM t_order where doctor_id = " + id
				+ " and state = " + state;
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByFriendIdAndState(Integer id, Integer state) {
		String sql = "SELECT * FROM t_order where friend_id = " + id
				+ " and state = " + state;
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByDoctorIdAndToday(Integer id) {
		String sql = "SELECT * FROM t_order where date(date) = curdate() and doctor_id = " + id;
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByFriendIdAndToday(Integer id) {
		String sql = "SELECT * FROM t_order where date(date) = curdate() and friend_id = " + id;
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public void updateRemind(Integer id) {
		String sql = "UPDATE t_order set remind = 1 where id = " + id;
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
	}


}

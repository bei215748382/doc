package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.Order;
import com.mlnx.doc.repository.OrderDao;
import com.mlnx.doc.service.OrderService;
import com.mlnx.doc.util.EnumCollection;
import com.mlnx.doc.util.Response;
import com.mlnx.doc.vo.OrderInfo;
import com.mlnx.doc.vo.OrderVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Response delete(Integer id) {
		orderDao.delete(id);
		return new Response(EnumCollection.ResponseCode.DELETE_DOCTOR_SUCCESS);

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
		String sql = String
				.format("SELECT * FROM t_order where doctor_id = %d and state = %d order by date asc",
						id, state);
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByFriendIdAndState(Integer id, Integer state) {
		String sql = String
				.format("SELECT * FROM t_order where friend_id = %d and state = %d order by date asc",
						id, state);
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByDoctorIdAndToday(Integer id) {
		String sql = "SELECT * FROM t_order where date(date) = curdate() and doctor_id = "
				+ id + " order by date desc";
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByFriendIdAndToday(Integer id) {
		String sql = "SELECT * FROM t_order where date(date) = curdate() and friend_id = "
				+ id + " order by date desc";
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public Response updateRemind(Integer id) {
		String sql = "UPDATE t_order set remind = 1 where id = " + id;
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
		return new Response(EnumCollection.ResponseCode.ORDER_REMIND_SUCCESS);
	}

	@Transactional
	@Override
	public Response update(Order order) {
		em.merge(order);
		em.flush();
		return new Response(EnumCollection.ResponseCode.UPDATE_DOCTOR_SUCCESS);
	}

	@Override
	public List<Order> findAllBystate(Integer id) {
		String sql = "SELECT * from t_order where state = " + id;
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public Response insert(OrderVo orderVo) {
		Doctor doctor = em.find(Doctor.class, orderVo.getFriend_id());
		Order order = new Order();
		order.setDoctor_id(orderVo.getDoctor_id());
		order.setDoctor_name(doctor.getName());
		order.setFriend_id(orderVo.getFriend_id());
		order.setHospital_name(doctor.getHospital());
		order.setDate(orderVo.getDate());
		order.setRemind(0);
		order.setState(0);
		em.persist(order);
		return new Response(EnumCollection.ResponseCode.ADD_ORDER_SUCCESS);

	}

	@Override
	public Response updateState(Integer id) {
		String sql = String.format(
				"UPDATE t_order set state = 1 where id = %d", id);
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
		return new Response(
				EnumCollection.ResponseCode.UPDATE_ORDER_STATE_SUCCESS);
	}

	@Override
	public List<Order> findByDoctorIdAndStateAndValid(int id) {
		String sql = String
				.format("SELECT * FROM t_order where doctor_id = %d and state = 0 and date > now() order by date asc",
						id);
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByFriendIdAndStateAndValid(int id) {
		String sql = String
				.format("SELECT * FROM t_order where friend_id = %d and state = 0 and date > now() order by date asc",
						id);
		Query query = em.createNativeQuery(sql, Order.class);
		return query.getResultList();
	}

	@Override
	public List<OrderInfo> iosFindByDoctorIdAndStateAndValid(int id) {
		String sql = String
				.format("SELECT o.*, d.name friend_name,d.voip_account friend_voip_account,dd.voip_account doctor_voip_account FROM t_order o left outer join t_doctor d on d.id=o.friend_id left outer join t_doctor dd on dd.id = o.doctor_id where doctor_id = %d and state = 0 and o.date > now() order by o.date asc;",
						id);
		Query query = em.createNativeQuery(sql, OrderInfo.class);
		return query.getResultList();
	}

	@Override
	public List<OrderInfo> iosFindByDoctorIdAndState(int id, int sid) {
		String sql = String
				.format("SELECT o.*, d.name friend_name,d.voip_account friend_voip_account,dd.voip_account doctor_voip_account FROM t_order o left outer join t_doctor d on d.id=o.friend_id left outer join t_doctor dd on dd.id = o.doctor_id where doctor_id = %d and state = %d order by o.date asc",
						id, sid);
		Query query = em.createNativeQuery(sql, OrderInfo.class);
		return query.getResultList();
	}

	@Override
	public List<OrderInfo> iosFindByFriendIdAndStateAndValid(int id) {
		String sql = String
				.format("SELECT o.*, d.name friend_name,d.voip_account friend_voip_account,dd.voip_account doctor_voip_account FROM t_order o left outer join t_doctor d on d.id=o.friend_id left outer join t_doctor dd on dd.id = o.doctor_id where friend_id = %d and state = 0 and o.date > now() order by o.date asc",
						id);
		Query query = em.createNativeQuery(sql, OrderInfo.class);
		return query.getResultList();
	}

	@Override
	public List<OrderInfo> iosFindByFriendIdAndState(int id, int sid) {
		String sql = String
				.format("SELECT o.*, d.name friend_name,d.voip_account friend_voip_account,dd.voip_account doctor_voip_account FROM t_order o left outer join t_doctor d on d.id=o.friend_id left outer join t_doctor dd on dd.id = o.doctor_id where friend_id = %d and state = %d order by o.date asc",
						id, sid);
		Query query = em.createNativeQuery(sql, OrderInfo.class);
		return query.getResultList();
	}

}

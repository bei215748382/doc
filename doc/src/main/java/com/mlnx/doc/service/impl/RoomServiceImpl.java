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

import com.mlnx.doc.entity.Bed;
import com.mlnx.doc.entity.Room;
import com.mlnx.doc.repository.RoomDao;
import com.mlnx.doc.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Room> list(Pageable pageable) {

		return roomDao.findAll(pageable);
	}

	@Override
	public void save(Room room) {
		roomDao.save(room);
	}

	@Override
	public Room get(Integer id) {
		return roomDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		roomDao.delete(id);

	}

	@Override
	public List<Room> findAll() {
		return roomDao.findAll();
	}

	@Override
	public List<Room> findByName(String name) {
		String sqlString = "select * from t_room where name like '%" + name
				+ "'%";
		Query query = em.createNativeQuery(sqlString,Room.class);
		return query.getResultList();
	}

	@Override
	public List<Room> findByDomainId(Integer id) {
		String sqlString = "select * from t_room where domain_id = " + id;
		Query query = em.createNativeQuery(sqlString,Room.class);
		List<Room> cities = query.getResultList();
		return cities;
	}
	@Transactional
	@Override
	public void update(Room room) {
		String sqlString =String.format("update t_room set name = '%s' where id = %d",room.getName(),room.getId());
		Query query = em.createNativeQuery(sqlString,Room.class);
		query.executeUpdate();
		
	}

}

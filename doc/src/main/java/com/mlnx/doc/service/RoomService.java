package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Room;

public interface RoomService {

	/**
	 * 列表
	 */
	Page<Room> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(Room patient);

	/**
	 * 查询
	 */
	Room get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Room> findAll();

	/**
	 * 根据名称模糊查找房间
	 * 
	 * @param name
	 * @return
	 */
	List<Room> findByName(String name);

	/**
	 * 根据省id查找房间号
	 * 
	 * @param id
	 * @return
	 */
	List<Room> findByDomainId(Integer id);

	/**
	 * 更新数据
	 * @param room
	 */
	void update(Room room);

}

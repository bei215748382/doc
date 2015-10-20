package com.scu.book.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scu.book.shop.entity.Task;
import com.scu.book.shop.repository.TaskDao;
import com.scu.book.shop.service.TaskService;

/**
 * task sev impl
 * 
 * @author lynch
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	/**
	 * 列表 
	 */
	public Page<Task> list(Pageable pageable){
		
		return taskDao.findAll(pageable);
	}
	
	/**
	 * 保存 
	 */
	public void save(Task task){
	
		taskDao.save(task);
	}

	/**
	 * 查询
	 */
	public Task get(Integer id) {

		return taskDao.findOne(id);
	}

	/**
	 * 删除
	 */
	public void delete(Integer id) {

		taskDao.delete(id);
	}

}

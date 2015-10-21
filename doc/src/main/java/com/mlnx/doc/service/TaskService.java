package com.mlnx.doc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Task;

/**
 * task sev inf
 * 
 * @author lynch
 */
public interface TaskService {
	
	/**
	 * 列表 
	 */
	Page<Task> list(Pageable pageable);
	
	/**
	 * 保存
	 */
	void save(Task task);

	/**
	 * 查询
	 */
	Task get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

}

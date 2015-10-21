package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Task;

/**
 * task dao
 * 
 * @author lynch
 */
public interface TaskDao extends JpaRepository<Task, Integer>,
		JpaSpecificationExecutor<Task> {

	
	
	
	
	
}

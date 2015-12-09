package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.ClientLog;
import com.mlnx.doc.util.Response;

public interface ClientLogService {

	/**
	 * 列表
	 */
	Page<ClientLog> list(Pageable pageable);

	/**
	 * 保存
	 */
	Response save(ClientLog clientLog);

	/**
	 * 查询
	 */
	ClientLog get(Integer id);

	/**
	 * 删除
	 * @return 
	 */
	Response delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<ClientLog> findAll();

}

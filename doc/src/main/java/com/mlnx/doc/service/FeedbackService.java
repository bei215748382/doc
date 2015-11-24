package com.mlnx.doc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.doc.entity.Feedback;

public interface FeedbackService {
	/**
	 * 列表
	 */
	Page<Feedback> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(Feedback feedback);

	/**
	 * 查询
	 */
	Feedback get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Feedback> findAll();

}

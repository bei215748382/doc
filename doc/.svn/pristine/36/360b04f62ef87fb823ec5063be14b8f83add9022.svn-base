package com.scu.book.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scu.book.shop.entity.Book;

public interface BookService {

	/**
	 * 列表 
	 */
	Page<Book> list(Pageable pageable);
	
	/**
	 * 保存
	 */
	void save(Book book);

	/**
	 * 查询
	 */
	Book get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);
}

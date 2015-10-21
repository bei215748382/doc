package com.mlnx.doc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.doc.entity.Book;
import com.mlnx.doc.repository.BookDao;
import com.mlnx.doc.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	@Override
	public Page<Book> list(Pageable pageable) {
		
		return bookDao.findAll(pageable);
	}

	@Override
	public void save(Book book) {
		bookDao.save(book);
	}

	@Override
	public Book get(Integer id) {
		return bookDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		bookDao.delete(id);
		
	}

}

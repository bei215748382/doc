package com.scu.book.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scu.book.shop.entity.Book;
import com.scu.book.shop.repository.BookDao;
import com.scu.book.shop.service.BookService;

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

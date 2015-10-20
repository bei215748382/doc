package com.scu.book.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.scu.book.shop.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer>,
JpaSpecificationExecutor<Book> {

}

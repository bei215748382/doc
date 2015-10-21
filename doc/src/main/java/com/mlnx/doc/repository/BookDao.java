package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer>,
JpaSpecificationExecutor<Book> {

}

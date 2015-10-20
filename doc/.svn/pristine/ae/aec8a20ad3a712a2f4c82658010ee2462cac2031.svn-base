package com.scu.book.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scu.book.shop.entity.Test;
import com.scu.book.shop.repository.TestDao;
import com.scu.book.shop.service.TestService;

/**
 * test sev impl
 * 
 * @author lynch
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	
	public Page<Test> list(Pageable pageable) {
		
		return testDao.findAll(pageable);
	}

	public void save(Test test) {
		
		testDao.save(test);
	}

}

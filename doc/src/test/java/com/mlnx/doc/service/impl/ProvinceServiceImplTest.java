package com.mlnx.doc.service.impl;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mlnx.doc.entity.Province;
import com.mlnx.doc.repository.ProvinceDao;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"file:src/main/resources/spring/spring.xml"}) 
public class ProvinceServiceImplTest {
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before test");
	}

	@Test
	public void testList() {

	}

	@Test
	public void testSave() {
		Province p = new Province();
		p.setName("江苏省");
		provinceDao.save(p);
	}

	@Test
	public void testGet() {
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testFindAll() {
		List<Province> p =provinceDao.findAll();
		System.out.println(p);
	}

	@Test
	public void testFindByName() {
	}

}

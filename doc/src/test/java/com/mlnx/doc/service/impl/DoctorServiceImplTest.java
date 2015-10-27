package com.mlnx.doc.service.impl;

import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.repository.DoctorDao;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"file:src/main/resources/spring/spring.xml"}) 
public class DoctorServiceImplTest {

	@Autowired
	private DoctorDao doctorDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister() {
		Doctor doctor = new Doctor();
		doctor.setPhone("15867404048");
		doctor.setPassword("123456");
		doctorDao.save(doctor);
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByPhone() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByHospitalId() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByDoctorId() {
		fail("Not yet implemented");
	}

}

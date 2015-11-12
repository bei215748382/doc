package com.mlnx.doc.web;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.util.Response;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"file:src/main/resources/spring/spring.xml"}) 
public class DoctorColTest {
	@Autowired
	private DoctorService doctorService;
	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister() {
		Doctor doctor = new Doctor();
		doctor.setPhone("15864520522");
		doctor.setPassword("123456");
		Response respone = doctorService.register(doctor);
		System.out.println(respone.toString());
	}

	@Test
	public void testLogin() {
		Doctor doctor = new Doctor();
		doctor.setPhone("15864521558");
		doctor.setUsername("admin");
		doctor.setPassword("123456");
		Map<String, String> map = doctorService.login(doctor);
		System.out.println(map.toString());
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
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByDoctorId() {
		List<Doctor> doctor = doctorService.findByDoctorId(1);
		System.out.println(doctor.toString());
	}

}

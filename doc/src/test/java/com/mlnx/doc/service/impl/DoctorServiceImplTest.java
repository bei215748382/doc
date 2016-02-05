package com.mlnx.doc.service.impl;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.State;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.web.BaseCol;

public class DoctorServiceImplTest extends BaseCol {

	@Autowired
	private DoctorService doctorService;

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
		System.out.println(doctorService.get(77));
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
		doctor.setPhone("15867400025");
		doctor.setPassword("123456");
		System.out.println(doctorService.register(doctor));
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

	@Test
	public void testLogin() {
		Doctor doctor = new Doctor();
		doctor.setPhone("15867400025");
		doctor.setPassword("123456");
		System.out.println(doctorService.login(doctor, 2));
	}
	
	@Test
	public void testFindDoctorState(){
		System.out.println(doctorService.findDoctorState(1546));
	}

	@Test 
	public void testUpdateDoctorState(){
		State state = new State();
		state.setDoctor_id(1546);
		state.setState(1);
		System.out.println(doctorService.updateDoctorState(state));
	}
}

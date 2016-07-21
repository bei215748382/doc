package com.mlnx.doc.web;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mlnx.doc.util.HttpUtil;

public class PatientColTest {

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testModify() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		String url = "http://121.40.137.14/doc/patients/find/3/patient.do";
		System.out.println(HttpUtil.sendGet(url));
	}

	@Test
	public void testFindByPatientsOnline() {
		fail("Not yet implemented");
	}

}

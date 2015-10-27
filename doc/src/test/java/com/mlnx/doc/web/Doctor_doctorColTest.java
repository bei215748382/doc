package com.mlnx.doc.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.mlnx.doc.service.Doctor_doctorService;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"file:src/main/resources/spring/spring.xml"}) 
public class Doctor_doctorColTest {
	
	@Autowired
	private Doctor_doctorService doctor_doctorService;
	
	@Test
	public void test() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(2);
		ids.add(3);
		doctor_doctorService.addFriends(1, ids);
	}

}

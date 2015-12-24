package com.mlnx.doc.video;

public class Doctor extends User {

	public Doctor(String id, String pass) {
		// TODO Auto-generated constructor stub

		super.type = UserType.DOCTOR;
		super.ID = id;
		super.pass = pass;
	}
}

package com.mlnx.doc.video;

public class User {
	enum UserType {
		DOCTOR, PATIONT
	}

	enum UserStatu {
		ONLINE, OUTLINE
	}

	protected String ID;
	protected String name;
	protected String pass;
	protected UserType type;
	protected UserStatu statu;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public UserType getType() {
		return type;
	}

	public UserStatu getStatu() {
		return statu;
	}

	public void setStatu(UserStatu statu) {
		this.statu = statu;
	}
	
	

}

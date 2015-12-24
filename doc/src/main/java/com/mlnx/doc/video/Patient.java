package com.mlnx.doc.video;

public class Patient {
	public enum Sex {
		MAN, WOMAN
	}

	private int ID;
	private String name;
	private String phoneNum;
	private String descriptor;
	private int age;
	private Sex sex;
	private String number;

	Patient() {

	}

	Patient(String name, String phoneNum, String descriptor, int age, Sex sex) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.descriptor = descriptor;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	

}

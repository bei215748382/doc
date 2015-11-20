package com.mlnx.doc.model;

import java.nio.ByteBuffer;
import java.util.Date;


public class Patient {

    public static enum Gender {

        MALE, FEMALE, OTHER
    }

    private Integer id;

    private String name;

    private Gender gender;

    private String deviceId;

    private Integer age;

    private String remark;

    private Date birthday;

    private String identification;

    private String pastDiseaseHistory;

    private String contact;

    private String pastMedicineHistory;

    private ByteBuffer picture;

    private String sign;

    private Date lastUpdateTime;
    
    public Patient(){
    	
    }

    public Patient(String phone) {
    	this.contact = phone;
	}

	public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPastDiseaseHistory() {
        return pastDiseaseHistory;
    }

    public void setPastDiseaseHistory(String pastDiseaseHistory) {
        this.pastDiseaseHistory = pastDiseaseHistory;
    }

    public String getPastMedicineHistory() {
        return pastMedicineHistory;
    }

    public void setPastMedicineHistory(String pastMedicineHistory) {
        this.pastMedicineHistory = pastMedicineHistory;
    }

    public ByteBuffer getPicture() {
        return picture;
    }

    public void setPicture(ByteBuffer picture) {
        this.picture = picture;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Gender getGender() {

        return gender;
    }

    public void setGender(Gender gender) {

        this.gender = gender;
    }

    public String getDeviceId() {

        return deviceId;
    }

    public void setDeviceId(String deviceId) {

        this.deviceId = deviceId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

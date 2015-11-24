package com.mlnx.doc.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderVo {
	
	private Integer doctor_id;
	
	private Integer friend_id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(Integer friend_id) {
		this.friend_id = friend_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

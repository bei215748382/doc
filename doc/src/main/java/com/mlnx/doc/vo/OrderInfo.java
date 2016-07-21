package com.mlnx.doc.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.mlnx.doc.entity.Order;
 
@Entity
public class OrderInfo  implements Serializable{
	
	private Integer id;
	private String hospital_name;
	private String doctor_name;
	private Integer state;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	private Integer doctor_id;
	private Integer friend_id;
	private Integer remind;

	private String friend_name;

	private String friend_voipAccount;

	private String doctor_voipAccount;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="hospital_name", nullable=true)
	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	
	@Column(name="doctor_name", nullable=true)
	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	@Column(name="state", nullable=true)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date", nullable=true)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	@Column(name="doctor_id", nullable=false)
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getFriend_id() {
		return friend_id;
	}
	
	@Column(name="friend_id", nullable=false)
	public void setFriend_id(Integer friend_id) {
		this.friend_id = friend_id;
	}

	public Integer getRemind() {
		return remind;
	}

	public void setRemind(Integer remind) {
		this.remind = remind;
	}


	public String getFriend_name() {
		return friend_name;
	}

	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}

	public String getFriend_voipAccount() {
		return friend_voipAccount;
	}

	public void setFriend_voipAccount(String friend_voipAccount) {
		this.friend_voipAccount = friend_voipAccount;
	}

	public String getDoctor_voipAccount() {
		return doctor_voipAccount;
	}

	public void setDoctor_voipAccount(String doctor_voipAccount) {
		this.doctor_voipAccount = doctor_voipAccount;
	}

}

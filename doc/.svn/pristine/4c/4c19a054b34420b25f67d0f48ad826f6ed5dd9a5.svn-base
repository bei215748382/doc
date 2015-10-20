package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cosumer database table.
 * 
 */
@Entity
@Table(name="cosumer")
public class Cosumer implements Serializable {
	
	private Integer cosumerId;
	private String cosumerAddress;
	private Short cosumerCondition;
	private String cosumerHeadpicture;
	private String cosumerName;
	private String cosumerPassword;
	private Integer cosumerPhonenumber;
	private Integer cosumerPoints;
	private Short cosumerSex;
	private Integer cosumerZipcode;
	private String maiAddress;

	public Cosumer() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cosumer_id", unique=true, nullable=false)
	public Integer getCosumerId() {
		return this.cosumerId;
	}

	public void setCosumerId(Integer cosumerId) {
		this.cosumerId = cosumerId;
	}


	@Column(name="cosumer_address", length=60)
	public String getCosumerAddress() {
		return this.cosumerAddress;
	}

	public void setCosumerAddress(String cosumerAddress) {
		this.cosumerAddress = cosumerAddress;
	}


	@Column(name="cosumer_condition", nullable=false)
	public Short getCosumerCondition() {
		return this.cosumerCondition;
	}

	public void setCosumerCondition(Short cosumerCondition) {
		this.cosumerCondition = cosumerCondition;
	}


	@Column(name="cosumer_headpicture", length=60)
	public String getCosumerHeadpicture() {
		return this.cosumerHeadpicture;
	}

	public void setCosumerHeadpicture(String cosumerHeadpicture) {
		this.cosumerHeadpicture = cosumerHeadpicture;
	}


	@Column(name="cosumer_name", nullable=false, length=60)
	public String getCosumerName() {
		return this.cosumerName;
	}

	public void setCosumerName(String cosumerName) {
		this.cosumerName = cosumerName;
	}


	@Column(name="cosumer_password", nullable=false, length=60)
	public String getCosumerPassword() {
		return this.cosumerPassword;
	}

	public void setCosumerPassword(String cosumerPassword) {
		this.cosumerPassword = cosumerPassword;
	}


	@Column(name="cosumer_phonenumber", nullable=false)
	public Integer getCosumerPhonenumber() {
		return this.cosumerPhonenumber;
	}

	public void setCosumerPhonenumber(Integer cosumerPhonenumber) {
		this.cosumerPhonenumber = cosumerPhonenumber;
	}


	@Column(name="cosumer_points")
	public Integer getCosumerPoints() {
		return this.cosumerPoints;
	}

	public void setCosumerPoints(Integer cosumerPoints) {
		this.cosumerPoints = cosumerPoints;
	}


	@Column(name="cosumer_sex")
	public Short getCosumerSex() {
		return this.cosumerSex;
	}

	public void setCosumerSex(Short cosumerSex) {
		this.cosumerSex = cosumerSex;
	}


	@Column(name="cosumer_zipcode")
	public Integer getCosumerZipcode() {
		return this.cosumerZipcode;
	}

	public void setCosumerZipcode(Integer cosumerZipcode) {
		this.cosumerZipcode = cosumerZipcode;
	}


	@Column(name="mai_address", nullable=false, length=100)
	public String getMaiAddress() {
		return this.maiAddress;
	}

	public void setMaiAddress(String maiAddress) {
		this.maiAddress = maiAddress;
	}

}
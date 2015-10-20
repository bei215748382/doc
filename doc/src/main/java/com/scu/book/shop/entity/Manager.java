package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the manager database table.
 * 
 */
@Entity
@Table(name="manager")
public class Manager implements Serializable {
	
	private Integer managerId;
	private String managerCode;
	private String managerImage;
	private String managerNumber;
	private Boolean stopAdministrator;
	private Boolean superAdministrator;

	public Manager() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="manager_id", unique=true, nullable=false)
	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}


	@Column(name="manager_code", nullable=false, length=60)
	public String getManagerCode() {
		return this.managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}


	@Column(name="manager_image", length=50)
	public String getManagerImage() {
		return this.managerImage;
	}

	public void setManagerImage(String managerImage) {
		this.managerImage = managerImage;
	}


	@Column(name="manager_number", nullable=false, length=60)
	public String getManagerNumber() {
		return this.managerNumber;
	}

	public void setManagerNumber(String managerNumber) {
		this.managerNumber = managerNumber;
	}


	@Column(name="stop_administrator", nullable=false)
	public Boolean getStopAdministrator() {
		return this.stopAdministrator;
	}

	public void setStopAdministrator(Boolean stopAdministrator) {
		this.stopAdministrator = stopAdministrator;
	}


	@Column(name="super_administrator", nullable=false)
	public Boolean getSuperAdministrator() {
		return this.superAdministrator;
	}

	public void setSuperAdministrator(Boolean superAdministrator) {
		this.superAdministrator = superAdministrator;
	}

}
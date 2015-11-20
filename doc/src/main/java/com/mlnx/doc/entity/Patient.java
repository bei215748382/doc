package com.mlnx.doc.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@Table(name = "t_patient")
public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String phone;
	private String state;//状态，在疗、出院
	private String sex;//性别
	private Date date;
	private String hospital;// 医院
	private String office;// 科室
	private String domain;// 病区
	private String room;// 房间号
	private String bed;// 床位号
	private String pic;// 床位照片
	private Integer doctor_id;// 管理的医生id
	private Integer bed_id;//床位号id
	private Integer patient_id;//pms-server的病人id

	public Patient() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = true, length = 60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", nullable = true, length = 60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "state", nullable = true, length = 60)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = true)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "hospital", nullable = true, length = 60)
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	@Column(name = "office", nullable = true, length = 60)
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Column(name = "domain", nullable = true, length = 60)
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "room", nullable = true, length = 60)
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Column(name = "bed", nullable = true, length = 60)
	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	@Column(name = "pic", nullable = true, length = 255)
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getBed_id() {
		return bed_id;
	}

	public void setBed_id(Integer bed_id) {
		this.bed_id = bed_id;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", state=" + state + ", date=" + date + ", hospital="
				+ hospital + ", office=" + office + ", domain=" + domain
				+ ", room=" + room + ", bed=" + bed + ", pic=" + pic
				+ ", doctor_id=" + doctor_id + ", bed_id=" + bed_id
				+ ", patient_id=" + patient_id + "]";
	}
	
}
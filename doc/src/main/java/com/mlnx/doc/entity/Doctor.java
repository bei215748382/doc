package com.mlnx.doc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_doctor")
public class Doctor implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	private String phone;

	@NotNull
	private String password;

	private Date date;

	private String name;

	private String sex;

	private Date birthday;

	private String hospital;

	private String office;

	private String title;

	private String skill;

	private String background;

	private String achievement;

	private String province;

	private String city;

	private String pic;

	private String voipAccount;

	private String voipPassword;

	private Integer hospital_id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getHospital() {
		return hospital;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOffice() {
		return office;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getSkill() {
		return skill;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getBackground() {
		return background;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic() {
		return pic;
	}

	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}

	public String getVoipAccount() {
		return voipAccount;
	}

	public void setVoipPassword(String voipPassword) {
		this.voipPassword = voipPassword;
	}

	public String getVoipPassword() {
		return voipPassword;
	}

	public Integer getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(Integer hospital_id) {
		this.hospital_id = hospital_id;
	}

}

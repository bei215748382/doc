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

	private String phone;

	@NotNull
	private String password;

	private String username;

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

	private String subAccountSid;

	private String subToken;

	private String friendName;

	private String dateCreated;
	
	private String voipAccount2;

	private String voipPassword2;
	
	private String subAccountSid2;  

	private String subToken2;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getSubAccountSid() {
		return subAccountSid;
	}

	public void setSubAccountSid(String subAccountSid) {
		this.subAccountSid = subAccountSid;
	}

	public String getSubToken() {
		return subToken;
	}

	public void setSubToken(String subToken) {
		this.subToken = subToken;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getVoipAccount2() {
		return voipAccount2;
	}

	public void setVoipAccount2(String voipAccount2) {
		this.voipAccount2 = voipAccount2;
	}

	public String getVoipPassword2() {
		return voipPassword2;
	}

	public void setVoipPassword2(String voipPassword2) {
		this.voipPassword2 = voipPassword2;
	}

	public String getSubAccountSid2() {
		return subAccountSid2;
	}

	public void setSubAccountSid2(String subAccountSid2) {
		this.subAccountSid2 = subAccountSid2;
	}

	public String getSubToken2() {
		return subToken2;
	}

	public void setSubToken2(String subToken2) {
		this.subToken2 = subToken2;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", phone=" + phone + ", password="
				+ password + ", username=" + username + ", date=" + date
				+ ", name=" + name + ", sex=" + sex + ", birthday=" + birthday
				+ ", hospital=" + hospital + ", office=" + office + ", title="
				+ title + ", skill=" + skill + ", background=" + background
				+ ", achievement=" + achievement + ", province=" + province
				+ ", city=" + city + ", pic=" + pic + ", voipAccount="
				+ voipAccount + ", voipPassword=" + voipPassword
				+ ", hospital_id=" + hospital_id + ", subAccountSid="
				+ subAccountSid + ", subToken=" + subToken + ", friendName="
				+ friendName + ", dateCreated=" + dateCreated
				+ ", voipAccount2=" + voipAccount2 + ", voipPassword2="
				+ voipPassword2 + ", subAccountSid2=" + subAccountSid2
				+ ", subToken2=" + subToken2 + "]";
	}

}

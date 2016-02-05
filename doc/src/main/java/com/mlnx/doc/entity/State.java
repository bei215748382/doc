package com.mlnx.doc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_state")
public class State {
	
	private Integer doctor_id;
	
	private Integer state;

	@Id
	@Column(name="doctor_id",nullable=false)
	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	@Column(name="state",nullable=false)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "State [doctor_id=" + doctor_id + ", state=" + state + "]";
	}

}

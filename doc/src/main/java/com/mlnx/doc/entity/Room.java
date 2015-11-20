package com.mlnx.doc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_room")
public class Room implements Serializable {
	private Integer id;
	private String name;
	private Integer domain_id;

	public Room(){}
	
	public Room(String name){
		this.setName(name);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="name", unique=true, nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDomain_id() {
		return domain_id;
	}

	public void setDomain_id(Integer domain_id) {
		this.domain_id = domain_id;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", domain_id=" + domain_id
				+ "]";
	}
}

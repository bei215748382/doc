package com.mlnx.doc.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the area_table database table.
 * 
 */
@Entity
@Table(name="area_table")
public class AreaTable implements Serializable {
	
	private Integer areaId;
	private String areaName;
	private Integer oneLevel;
	private Integer rank;
	private Integer threeLevel;
	private Integer twoLevel;

	public AreaTable() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="area_id", unique=true, nullable=false)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}


	@Column(name="area_name", nullable=false, length=60)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	@Column(name="one_level", nullable=false)
	public Integer getOneLevel() {
		return this.oneLevel;
	}

	public void setOneLevel(Integer oneLevel) {
		this.oneLevel = oneLevel;
	}


	@Column(nullable=false)
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}


	@Column(name="three_level", nullable=false)
	public Integer getThreeLevel() {
		return this.threeLevel;
	}

	public void setThreeLevel(Integer threeLevel) {
		this.threeLevel = threeLevel;
	}


	@Column(name="two_level", nullable=false)
	public Integer getTwoLevel() {
		return this.twoLevel;
	}

	public void setTwoLevel(Integer twoLevel) {
		this.twoLevel = twoLevel;
	}

}
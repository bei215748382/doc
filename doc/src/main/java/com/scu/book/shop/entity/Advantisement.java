package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the advantisement database table.
 * 
 */
@Entity
@Table(name="advantisement")
public class Advantisement implements Serializable {
	
	private Integer adId;
	private String adName;
	private Boolean adShow;
	private Date buildTime;
	private Integer clickTimes;
	private String detailDepict;
	private Date endTime;
	private String image;
	private Date releaseTime;
	private String simpleDepict;
	private String url;

	public Advantisement() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ad_id", unique=true, nullable=false)
	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}


	@Column(name="ad_name", nullable=false, length=50)
	public String getAdName() {
		return this.adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}


	@Column(name="ad_show", nullable=false)
	public Boolean getAdShow() {
		return this.adShow;
	}

	public void setAdShow(Boolean adShow) {
		this.adShow = adShow;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="build_time", nullable=false)
	public Date getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}


	@Column(name="click_times", nullable=false)
	public Integer getClickTimes() {
		return this.clickTimes;
	}

	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}


	@Lob
	@Column(name="detail_depict")
	public String getDetailDepict() {
		return this.detailDepict;
	}

	public void setDetailDepict(String detailDepict) {
		this.detailDepict = detailDepict;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time", nullable=false)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	@Column(length=50)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="release_time", nullable=false)
	public Date getReleaseTime() {
		return this.releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}


	@Column(name="simple_depict", length=100)
	public String getSimpleDepict() {
		return this.simpleDepict;
	}

	public void setSimpleDepict(String simpleDepict) {
		this.simpleDepict = simpleDepict;
	}


	@Column(nullable=false, length=50)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
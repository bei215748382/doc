package com.mlnx.doc.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the delivery_address database table.
 * 
 */
@Entity
@Table(name="delivery_address")
public class DeliveryAddress implements Serializable {
	
	private Integer deliveryAddressId;
	private Boolean acquiesce;
	private Integer cityId;
	private String cityName;
	private Integer connectNumber;
	private String consigneeName;
	private String detailAddress;
	private Integer districtId;
	private String districtName;
	private Integer postcode;
	private Integer provinceId;
	private String provinceName;
	private Integer userId;

	public DeliveryAddress() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="delivery_address_id", unique=true, nullable=false)
	public Integer getDeliveryAddressId() {
		return this.deliveryAddressId;
	}

	public void setDeliveryAddressId(Integer deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}


	@Column(nullable=false)
	public Boolean getAcquiesce() {
		return this.acquiesce;
	}

	public void setAcquiesce(Boolean acquiesce) {
		this.acquiesce = acquiesce;
	}


	@Column(name="city_id", nullable=false)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}


	@Column(name="city_name", nullable=false, length=10)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	@Column(name="connect_number", nullable=false)
	public Integer getConnectNumber() {
		return this.connectNumber;
	}

	public void setConnectNumber(Integer connectNumber) {
		this.connectNumber = connectNumber;
	}


	@Column(name="consignee_name", nullable=false, length=60)
	public String getConsigneeName() {
		return this.consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}


	@Column(name="detail_address", nullable=false, length=100)
	public String getDetailAddress() {
		return this.detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}


	@Column(name="district_id", nullable=false)
	public Integer getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}


	@Column(name="district_name", nullable=false, length=10)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public Integer getPostcode() {
		return this.postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}


	@Column(name="province_id", nullable=false)
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}


	@Column(name="province_name", nullable=false, length=10)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


	@Column(name="user_id", nullable=false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
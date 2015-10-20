package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the indent database table.
 * 
 */
@Entity
@Table(name="indent")
public class Indent implements Serializable {
	
	private Integer indentId;
	private String consigneeName;
	private String consumerName;
	private String consumerRemark;
	private Integer contactNumber;
	private String deliverAddress;
	private Date indentBuildTime;
	private Double indentOldprice;
	private Short indentPaymentWay;
	private Double indentPayprice;
	private Short indentState;
	private Double logisticFee;
	private Integer logisticNumber;
	private String logisticWay;
	private Integer postcode;
	private Integer userId;
	private List<IndentDetail> indentDetails;

	public Indent() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="indent_id", unique=true, nullable=false)
	public Integer getIndentId() {
		return this.indentId;
	}

	public void setIndentId(Integer indentId) {
		this.indentId = indentId;
	}


	@Column(name="consignee_name", nullable=false, length=60)
	public String getConsigneeName() {
		return this.consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}


	@Column(name="consumer_name", nullable=false, length=60)
	public String getConsumerName() {
		return this.consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}


	@Column(name="consumer_remark", length=400)
	public String getConsumerRemark() {
		return this.consumerRemark;
	}

	public void setConsumerRemark(String consumerRemark) {
		this.consumerRemark = consumerRemark;
	}


	@Column(name="contact_number", nullable=false)
	public Integer getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(Integer contactNumber) {
		this.contactNumber = contactNumber;
	}


	@Column(name="deliver_address", nullable=false, length=60)
	public String getDeliverAddress() {
		return this.deliverAddress;
	}

	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="indent_build_time", nullable=false)
	public Date getIndentBuildTime() {
		return this.indentBuildTime;
	}

	public void setIndentBuildTime(Date indentBuildTime) {
		this.indentBuildTime = indentBuildTime;
	}


	@Column(name="indent_oldprice")
	public Double getIndentOldprice() {
		return this.indentOldprice;
	}

	public void setIndentOldprice(Double indentOldprice) {
		this.indentOldprice = indentOldprice;
	}


	@Column(name="indent_payment_way", nullable=false)
	public Short getIndentPaymentWay() {
		return this.indentPaymentWay;
	}

	public void setIndentPaymentWay(Short indentPaymentWay) {
		this.indentPaymentWay = indentPaymentWay;
	}


	@Column(name="indent_payprice", nullable=false)
	public Double getIndentPayprice() {
		return this.indentPayprice;
	}

	public void setIndentPayprice(Double indentPayprice) {
		this.indentPayprice = indentPayprice;
	}


	@Column(name="indent_state", nullable=false)
	public Short getIndentState() {
		return this.indentState;
	}

	public void setIndentState(Short indentState) {
		this.indentState = indentState;
	}


	@Column(name="logistic_fee")
	public Double getLogisticFee() {
		return this.logisticFee;
	}

	public void setLogisticFee(Double logisticFee) {
		this.logisticFee = logisticFee;
	}


	@Column(name="logistic_number")
	public Integer getLogisticNumber() {
		return this.logisticNumber;
	}

	public void setLogisticNumber(Integer logisticNumber) {
		this.logisticNumber = logisticNumber;
	}


	@Column(name="logistic_way", length=20)
	public String getLogisticWay() {
		return this.logisticWay;
	}

	public void setLogisticWay(String logisticWay) {
		this.logisticWay = logisticWay;
	}


	public Integer getPostcode() {
		return this.postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}


	@Column(name="user_id", nullable=false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	//bi-directional many-to-one association to IndentDetail
	@OneToMany(mappedBy="indent")
	public List<IndentDetail> getIndentDetails() {
		return this.indentDetails;
	}

	public void setIndentDetails(List<IndentDetail> indentDetails) {
		this.indentDetails = indentDetails;
	}

	public IndentDetail addIndentDetail(IndentDetail indentDetail) {
		getIndentDetails().add(indentDetail);
		indentDetail.setIndent(this);

		return indentDetail;
	}

	public IndentDetail removeIndentDetail(IndentDetail indentDetail) {
		getIndentDetails().remove(indentDetail);
		indentDetail.setIndent(null);

		return indentDetail;
	}

}
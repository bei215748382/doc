package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the indent_details database table.
 * 
 */
@Entity
@Table(name="indent_details")
public class IndentDetail implements Serializable {
	
	private Integer id;
	private Date buildTime;
	private Integer goodsAmount;
	private Integer goodsId;
	private String goodsImage;
	private String goodsName;
	private Double goodsPrice;
	private Indent indent;

	public IndentDetail() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="build_time", nullable=false)
	public Date getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}


	@Column(name="goods_amount", nullable=false)
	public Integer getGoodsAmount() {
		return this.goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}


	@Column(name="goods_id", nullable=false)
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	@Column(name="goods_image", length=100)
	public String getGoodsImage() {
		return this.goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}


	@Column(name="goods_name", nullable=false, length=60)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	@Column(name="goods_price", nullable=false)
	public Double getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}


	//bi-directional many-to-one association to Indent
	@ManyToOne
	@JoinColumn(name="indent_id", nullable=false)
	public Indent getIndent() {
		return this.indent;
	}

	public void setIndent(Indent indent) {
		this.indent = indent;
	}

}
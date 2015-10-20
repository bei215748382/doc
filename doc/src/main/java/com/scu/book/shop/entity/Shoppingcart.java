package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the shoppingcart database table.
 * 
 */
@Entity
@Table(name="shoppingcart")
public class Shoppingcart implements Serializable {
	
	private Integer shoppingcartId;
	private Date addingcartTime;
	private Integer bookAmout;
	private Integer bookId;
	private String bookName;
	private Integer bookPoints;
	private Double bookPrice;
	private Double bookRetailprice;
	private Integer cosumerId;

	public Shoppingcart() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shoppingcart_id", unique=true, nullable=false)
	public Integer getShoppingcartId() {
		return this.shoppingcartId;
	}

	public void setShoppingcartId(Integer shoppingcartId) {
		this.shoppingcartId = shoppingcartId;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="addingcart_time", nullable=false)
	public Date getAddingcartTime() {
		return this.addingcartTime;
	}

	public void setAddingcartTime(Date addingcartTime) {
		this.addingcartTime = addingcartTime;
	}


	@Column(name="book_amout", nullable=false)
	public Integer getBookAmout() {
		return this.bookAmout;
	}

	public void setBookAmout(Integer bookAmout) {
		this.bookAmout = bookAmout;
	}


	@Column(name="book_id", nullable=false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}


	@Column(name="book_name", nullable=false, length=60)
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	@Column(name="book_points")
	public Integer getBookPoints() {
		return this.bookPoints;
	}

	public void setBookPoints(Integer bookPoints) {
		this.bookPoints = bookPoints;
	}


	@Column(name="book_price", nullable=false)
	public Double getBookPrice() {
		return this.bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}


	@Column(name="book_retailprice", nullable=false)
	public Double getBookRetailprice() {
		return this.bookRetailprice;
	}

	public void setBookRetailprice(Double bookRetailprice) {
		this.bookRetailprice = bookRetailprice;
	}


	@Column(name="cosumer_id", nullable=false)
	public Integer getCosumerId() {
		return this.cosumerId;
	}

	public void setCosumerId(Integer cosumerId) {
		this.cosumerId = cosumerId;
	}

}
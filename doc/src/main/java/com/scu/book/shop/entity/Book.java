package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@Table(name="book")
public class Book implements Serializable {
	
	private Integer bookId;
	private String bookAuthorname;
	private String bookComments;
	private String bookDetails;
	private String bookName;
	private String bookPictures;
	private Integer bookPoint;
	private String bookPress;
	private Double bookPrice;
	private Date bookPublishTime;
	private Double bookRetailprice;
	private Integer bookStoreamont;
	private Integer buyinglimitiAmount;
	private Boolean ifOnSale;
	private Date onSaleTime;

	public Book() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id", unique=true, nullable=false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}


	@Column(name="book_authorname", nullable=false, length=60)
	public String getBookAuthorname() {
		return this.bookAuthorname;
	}

	public void setBookAuthorname(String bookAuthorname) {
		this.bookAuthorname = bookAuthorname;
	}


	@Column(name="book_comments", nullable=false, length=100)
	public String getBookComments() {
		return this.bookComments;
	}

	public void setBookComments(String bookComments) {
		this.bookComments = bookComments;
	}


	@Lob
	@Column(name="book_details", nullable=false)
	public String getBookDetails() {
		return this.bookDetails;
	}

	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}


	@Column(name="book_name", nullable=false, length=60)
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	@Column(name="book_pictures", nullable=false, length=60)
	public String getBookPictures() {
		return this.bookPictures;
	}

	public void setBookPictures(String bookPictures) {
		this.bookPictures = bookPictures;
	}


	@Column(name="book_point")
	public Integer getBookPoint() {
		return this.bookPoint;
	}

	public void setBookPoint(Integer bookPoint) {
		this.bookPoint = bookPoint;
	}


	@Column(name="book_press", nullable=false, length=50)
	public String getBookPress() {
		return this.bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}


	@Column(name="book_price", nullable=false)
	public Double getBookPrice() {
		return this.bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="book_publish_time", nullable=false)
	public Date getBookPublishTime() {
		return this.bookPublishTime;
	}

	public void setBookPublishTime(Date bookPublishTime) {
		this.bookPublishTime = bookPublishTime;
	}


	@Column(name="book_retailprice", nullable=false)
	public Double getBookRetailprice() {
		return this.bookRetailprice;
	}

	public void setBookRetailprice(Double bookRetailprice) {
		this.bookRetailprice = bookRetailprice;
	}


	@Column(name="book_storeamont", nullable=false)
	public Integer getBookStoreamont() {
		return this.bookStoreamont;
	}

	public void setBookStoreamont(Integer bookStoreamont) {
		this.bookStoreamont = bookStoreamont;
	}


	@Column(name="buyinglimiti_amount", nullable=false)
	public Integer getBuyinglimitiAmount() {
		return this.buyinglimitiAmount;
	}

	public void setBuyinglimitiAmount(Integer buyinglimitiAmount) {
		this.buyinglimitiAmount = buyinglimitiAmount;
	}


	@Column(name="if_on_sale", nullable=false)
	public Boolean getIfOnSale() {
		return this.ifOnSale;
	}

	public void setIfOnSale(Boolean ifOnSale) {
		this.ifOnSale = ifOnSale;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="on_sale_time", nullable=false)
	public Date getOnSaleTime() {
		return this.onSaleTime;
	}

	public void setOnSaleTime(Date onSaleTime) {
		this.onSaleTime = onSaleTime;
	}

}
package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the book_homepage_showing database table.
 * 
 */
@Entity
@Table(name="book_homepage_showing")
public class BookHomepageShowing implements Serializable {
	
	private Integer showingId;
	private Integer bookId;
	private String showingName;
	private Integer showingRecoder;

	public BookHomepageShowing() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="showing_id", unique=true, nullable=false)
	public Integer getShowingId() {
		return this.showingId;
	}

	public void setShowingId(Integer showingId) {
		this.showingId = showingId;
	}


	@Column(name="book_id", nullable=false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}


	@Column(name="showing_name", nullable=false, length=60)
	public String getShowingName() {
		return this.showingName;
	}

	public void setShowingName(String showingName) {
		this.showingName = showingName;
	}


	@Column(name="showing_recoder", nullable=false)
	public Integer getShowingRecoder() {
		return this.showingRecoder;
	}

	public void setShowingRecoder(Integer showingRecoder) {
		this.showingRecoder = showingRecoder;
	}

}
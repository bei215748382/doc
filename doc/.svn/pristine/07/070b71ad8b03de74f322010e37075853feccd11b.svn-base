package com.scu.book.shop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the book_comment database table.
 * 
 */
@Entity
@Table(name="book_comment")
public class BookComment implements Serializable {
	
	private Integer commentId;
	private Integer bookId;
	private String commentContains;
	private Integer commentLevel;
	private String commentName;
	private Date commentTime;
	private Integer cosumerD;
	private Integer orderId;
	private String replyContains;
	private Date replyTime;

	public BookComment() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id", unique=true, nullable=false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}


	@Column(name="book_id", nullable=false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}


	@Column(name="comment_contains", nullable=false, length=300)
	public String getCommentContains() {
		return this.commentContains;
	}

	public void setCommentContains(String commentContains) {
		this.commentContains = commentContains;
	}


	@Column(name="comment_level", nullable=false)
	public Integer getCommentLevel() {
		return this.commentLevel;
	}

	public void setCommentLevel(Integer commentLevel) {
		this.commentLevel = commentLevel;
	}


	@Column(name="comment_name", nullable=false, length=20)
	public String getCommentName() {
		return this.commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="comment_time", nullable=false)
	public Date getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}


	@Column(name="cosumer_d", nullable=false)
	public Integer getCosumerD() {
		return this.cosumerD;
	}

	public void setCosumerD(Integer cosumerD) {
		this.cosumerD = cosumerD;
	}


	@Column(name="order_id", nullable=false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	@Column(name="reply_contains", nullable=false, length=300)
	public String getReplyContains() {
		return this.replyContains;
	}

	public void setReplyContains(String replyContains) {
		this.replyContains = replyContains;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reply_time", nullable=false)
	public Date getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

}
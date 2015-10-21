package com.mlnx.doc.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the book_classification database table.
 * 
 */
@Entity
@Table(name="book_classification")
public class BookClassification implements Serializable {
	
	private Integer classificationId;
	private Integer classFirst;
	private Integer classSecond;
	private Integer classThird;
	private String classificationName;
	private Integer reorderNumber;

	public BookClassification() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="classification_id", unique=true, nullable=false)
	public Integer getClassificationId() {
		return this.classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}


	@Column(name="class_first", nullable=false)
	public Integer getClassFirst() {
		return this.classFirst;
	}

	public void setClassFirst(Integer classFirst) {
		this.classFirst = classFirst;
	}


	@Column(name="class_second", nullable=false)
	public Integer getClassSecond() {
		return this.classSecond;
	}

	public void setClassSecond(Integer classSecond) {
		this.classSecond = classSecond;
	}


	@Column(name="class_third", nullable=false)
	public Integer getClassThird() {
		return this.classThird;
	}

	public void setClassThird(Integer classThird) {
		this.classThird = classThird;
	}


	@Column(name="classification_name", nullable=false, length=15)
	public String getClassificationName() {
		return this.classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}


	@Column(name="reorder_number", nullable=false)
	public Integer getReorderNumber() {
		return this.reorderNumber;
	}

	public void setReorderNumber(Integer reorderNumber) {
		this.reorderNumber = reorderNumber;
	}

}
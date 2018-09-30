package com.gcit.lms.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_copies", catalog="library")
public class BookCopies {
	@EmbeddedId
	private BookCopiesID id;
	
	public BookCopiesID getId() {
		return id;
	}
	public void setId(BookCopiesID id) {
		this.id = id;
	}
	@Column
	private Integer noOfCopies;
	/**
	 * @return the noOfCopies
	 */
	public Integer getNoOfCopies() {
		return noOfCopies;
	}
	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
}

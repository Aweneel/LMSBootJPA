package com.gcit.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_loans", catalog="library")
public class BookLoan {
	@EmbeddedId
	private BookLoanID id;
	
	@Column
	private Date dueDate;
	@Column
	private Date dateIn;
	
	
	public BookLoanID getId() {
		return id;
	}
	public void setId(BookLoanID id) {
		this.id = id;
	}
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the dateIn
	 */
	public Date getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	
}

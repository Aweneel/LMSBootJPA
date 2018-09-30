package com.gcit.lms.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.validation.constraints.NotEmpty;

@Embeddable
public class BookAuthorID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookAuthorID() {
		super();
	}
	@Column(name = "bookId")
	@NotEmpty
	private Integer bookId;
	
	@Column(name = "authorId")
	@NotEmpty
	private Integer authorId;
	
	public BookAuthorID(Integer bookId2, Integer authorId2) {
		this.bookId = bookId2;
		this.authorId = authorId2;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookAuthorID other = (BookAuthorID) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	
}

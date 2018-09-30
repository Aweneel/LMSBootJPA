package com.gcit.lms.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.validation.constraints.NotEmpty;

@Embeddable
public class BookGenreID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookGenreID() {
		super();
	}
	@Column(name = "bookId")
	@NotEmpty
	private Integer bookId;
	
	@Column(name = "genre_id")
	@NotEmpty
	private Integer genre_id;
	
	public BookGenreID(Integer bookId2, Integer genreId2) {
		this.bookId = bookId2;
		this.genre_id = genreId2;
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
		result = prime * result + ((genre_id == null) ? 0 : genre_id.hashCode());
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
		BookGenreID other = (BookGenreID) obj;
		if (genre_id == null) {
			if (other.genre_id != null)
				return false;
		} else if (!genre_id.equals(other.genre_id))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
	public Integer getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
	}
	
	
}

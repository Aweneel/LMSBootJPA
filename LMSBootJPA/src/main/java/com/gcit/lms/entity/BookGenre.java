package com.gcit.lms.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_genres", catalog="library")
public class BookGenre {
	
	@EmbeddedId
	private BookGenreID id;

	public BookGenreID getId() {
		return id;
	}

	public void setId(BookGenreID id) {
		this.id = id;
	}
}

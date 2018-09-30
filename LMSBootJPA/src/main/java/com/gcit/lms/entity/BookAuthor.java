package com.gcit.lms.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_authors", catalog="library")
public class BookAuthor {
	
	@EmbeddedId
	private BookAuthorID id;

	public BookAuthorID getId() {
		return id;
	}

	public void setId(BookAuthorID id) {
		this.id = id;
	}
}

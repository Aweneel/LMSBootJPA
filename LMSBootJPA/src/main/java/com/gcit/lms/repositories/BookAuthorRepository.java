package com.gcit.lms.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.BookAuthor;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
	
	@Query("from BookAuthor where bookId =:searchString")
	List<BookAuthor> findAllById(@Param(value = "searchString")Integer authorId);
	
	
}

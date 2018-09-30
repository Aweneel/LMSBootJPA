package com.gcit.lms.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.gcit.lms.entity.BookGenre;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Integer> {
	
}

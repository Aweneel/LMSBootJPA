package com.gcit.lms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
	@Query("from Genre where genre_name like %:searchString%")
	public List<Genre> readGenresByName(@Param(value = "searchString") String genreName);
}

package com.gcit.lms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.Borrower;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
	@Query("from Borrower where name like %:searchString%")
	public List<Borrower> readBorrowersByName(@Param(value = "searchString") String name);
	
	@Query("from Borrower where cardNo =:cardNo")
	public Borrower verifyId(@Param(value = "cardNo") Integer cardNo);
}

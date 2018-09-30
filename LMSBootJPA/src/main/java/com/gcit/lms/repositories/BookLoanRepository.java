package com.gcit.lms.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.gcit.lms.entity.BookLoan;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
	@Query("from BookLoan where cardNo =:cardNo and dateIn is null")
	List<BookLoan> getAllBooksLoanedByCardNo(@Param(value = "cardNo")Integer cardNo);
	
}

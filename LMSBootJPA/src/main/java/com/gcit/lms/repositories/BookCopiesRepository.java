package com.gcit.lms.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.BookCopies;

@Repository
public interface BookCopiesRepository extends JpaRepository<BookCopies, Integer> {
	@Query("from BookCopies where bookId =:id")
	List<BookCopies> findCopiesAllBranchesByBookId(@Param(value = "id") Integer bookId);
	
	@Query("from BookCopies where branchId =:id")
	List<BookCopies> findCopiesAllBooksByBranchId(@Param(value = "id") Integer branchId);
	
	@Query("from BookCopies where bookId = :id1 and branchId =:id2")
	List<BookCopies> findAllByBookBranchIds(@Param(value = "id1") Integer bookId, @Param(value = "id2") Integer branchId);
	
}

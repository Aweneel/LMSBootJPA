package com.gcit.lms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gcit.lms.entity.LibBranch;

@Repository
public interface LibraryBranchRepository extends JpaRepository<LibBranch, Integer> {
	@Query("from LibBranch where branchName like %:searchString%")
	public List<LibBranch> readLibraryBranchByName(@Param(value = "searchString") String branchName);

}

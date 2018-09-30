package com.gcit.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.repositories.BookCopiesRepository;
import com.gcit.lms.repositories.LibraryBranchRepository;

@RestController
public class LibrarianService {

	@Autowired
	LibraryBranchRepository branchRepo;
	
	@Autowired
	BookCopiesRepository copiesRepo;
	
	@RequestMapping(value = "/lms/readBranches", method = RequestMethod.GET, produces = "application/json")
	public List<LibBranch> readAllBranches(@RequestParam String searchString) {
		try {
			if (!searchString.isEmpty()) {
				return branchRepo.readLibraryBranchByName(searchString);
			} else {
				return branchRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/updateBranchNameAddress", method = RequestMethod.POST, consumes = "application/json")
	public String updateBranchNameAddress(@RequestBody LibBranch branch,@RequestParam String name, @RequestParam String address) {
		/*
		 * Use localhost:8080/lms/updateBranchNameAddress/?name=newName&?address=newAddress
		 */
		String returnString = "";
		try {
			if(!name.isEmpty() && !address.isEmpty()) {
				if(!"N/A".equalsIgnoreCase(name)) {
					branch.setBranchName(name);
				}
				if(!"N/A".equalsIgnoreCase(address)) {
					branch.setBranchAddress(address);
				}
				branchRepo.saveAndFlush(branch);
				if(!"N/A".equalsIgnoreCase(name) || !"N/A".equalsIgnoreCase(address)) 
					returnString = "Branch updated!";
			}			
		} catch (Exception e) {
			e.printStackTrace();		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/readNoOfCopies", method = RequestMethod.GET, produces = "application/json")
	public List<BookCopies> readNoOfCopies(@RequestParam Integer bookId, @RequestParam Integer branchId) {
		/*
		 * Use localhost:8080/lms/readNoOfCopies/?bookI=id1&branchId=id2
		 */
		try {
			if(bookId != null && branchId == null) {
				return copiesRepo.findCopiesAllBranchesByBookId(bookId);
			}
			else if(bookId == null && branchId != null) {
				return copiesRepo.findCopiesAllBooksByBranchId(branchId);
			}
			else if(bookId != null && branchId != null)
				return copiesRepo.findAllByBookBranchIds(bookId, branchId);
			else
				return copiesRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/addBookCopies", method = RequestMethod.POST, consumes = "application/json")
	public String addBookCopies(@RequestBody BookCopies bookCopies, @RequestParam Integer copiesToAdd) {

		String returnString = "";
		Integer currentCopies = 0;
		if(bookCopies.getNoOfCopies() != null)
			currentCopies = bookCopies.getNoOfCopies();
		try {
			bookCopies.setNoOfCopies(currentCopies+copiesToAdd);
			copiesRepo.saveAndFlush(bookCopies);
			returnString= "Copies added!" ;
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return returnString;
	}
	
}

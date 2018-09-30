package com.gcit.lms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.repositories.BookCopiesRepository;
import com.gcit.lms.repositories.BookLoanRepository;
import com.gcit.lms.repositories.BorrowerRepository;
import com.gcit.lms.repositories.LibraryBranchRepository;

@RestController
public class BorrowerService {
	@Autowired
	LibraryBranchRepository branchRepo;
	
	@Autowired
	BookCopiesRepository copiesRepo;
	
	@Autowired
	BookLoanRepository loanRepo;
	
	@Autowired
	BorrowerRepository borrowerRepo;
	
	
	@RequestMapping(value = "/lms/getAllBooksLoanedByCardNo", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoan> getAllBooksLoanedByCardNo(@RequestParam Integer cardNo) {
		Borrower borrower = borrowerRepo.verifyId(cardNo);
		if(borrower==null) {
			System.out.println("Invalid Card No");
			return null;
		}
		try {
			return loanRepo.getAllBooksLoanedByCardNo(cardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	@RequestMapping(value = "/lms/checkOutABook", method = RequestMethod.POST, consumes = "application/json")
	public String checkOutABook(@RequestBody BookLoan loan) {	
		
		String returnString = "";
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		try {
			//Apply dateOut and dueDate in tbl_book_loans
			loan.getId().setdateOut(date);
			cal.add(Calendar.DAY_OF_MONTH, 7);
			loan.setDueDate(cal.getTime());
			loanRepo.saveAndFlush(loan);	
			//decrement noOfCopies in tbl_book_copies
			BookCopies bc = copiesRepo.findAllByBookBranchIds(loan.getId().getBookId(), loan.getId().getBranchId()).get(0);
			bc.setNoOfCopies(bc.getNoOfCopies()-1);
			copiesRepo.saveAndFlush(bc);
			returnString = "Book Checked Out!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/returnABook", method = RequestMethod.POST, consumes = "application/json")
	public String returnABook(@RequestBody BookLoan loan) {	
		
		String returnString = "";
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		try {
			//Apply dateOut and dueDate in tbl_book_loans
			loan.setDateIn(date);
			loanRepo.saveAndFlush(loan);	
			//decrement noOfCopies in tbl_book_copies
			BookCopies bc = copiesRepo.findAllByBookBranchIds(loan.getId().getBookId(), loan.getId().getBranchId()).get(0);
			bc.setNoOfCopies(bc.getNoOfCopies()+1);
			copiesRepo.saveAndFlush(bc);
			returnString = "Book Returned!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}	
	
	
}

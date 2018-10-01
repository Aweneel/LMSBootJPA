package com.gcit.lms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookAuthor;
import com.gcit.lms.entity.BookAuthorID;
import com.gcit.lms.entity.BookGenre;
import com.gcit.lms.entity.BookGenreID;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.repositories.AuthorRepository;
import com.gcit.lms.repositories.BookAuthorRepository;
import com.gcit.lms.repositories.BookGenreRepository;
import com.gcit.lms.repositories.BookLoanRepository;
import com.gcit.lms.repositories.BookRepository;
import com.gcit.lms.repositories.BorrowerRepository;
import com.gcit.lms.repositories.GenreRepository;
import com.gcit.lms.repositories.LibraryBranchRepository;
import com.gcit.lms.repositories.PublisherRepository;

@RestController
public class AdminService {
	
	@Autowired
	AuthorRepository authorRepo;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	BookAuthorRepository bookAuthorRepo;
	
	@Autowired
	BookGenreRepository bookGenreRepo;
	
	@Autowired
	PublisherRepository publisherRepo;
	
	@Autowired
	BookLoanRepository loanRepo;
	
	@Autowired
	GenreRepository genreRepo;
	
	@Autowired
	LibraryBranchRepository branchRepo;
	
	@Autowired
	BorrowerRepository borrowRepo;

	@RequestMapping(value = "/lms/addNewBook", method = RequestMethod.POST, consumes = "application/json")	
	public void addBook(@RequestBody Book book) {
		try {
			book = bookRepo.save(book);
			//inserting into tbl_book_authors
			for (Author a : book.getAuthors()) {
				BookAuthor bookAuthor = new BookAuthor();
				bookAuthor.setId(new BookAuthorID(book.getBookId(),a.getAuthorId()));
				bookAuthorRepo.save(bookAuthor);
			}
			//inserting into tbl_book_genres
			for (Genre a : book.getGenres()) {
				BookGenre bookGenre = new BookGenre();
				bookGenre.setId(new BookGenreID(book.getBookId(),a.getGenreId()));
				bookGenreRepo.save(bookGenre);
			}
			bookAuthorRepo.flush();
			bookGenreRepo.flush();
			bookRepo.flush();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@RequestMapping(value = "/lms/readAuthors", method = RequestMethod.GET, produces = "application/json")
	public List<Author> readAllAuthors(@RequestParam String searchString) {
		List<Author> authors = new ArrayList<>();
		try {
			if (!searchString.isEmpty()) {
				authors = authorRepo.readAuthorsByName(searchString);
			} else {
				authors = (List<Author>) authorRepo.findAll();
			}
			return authors;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/lms/readBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readAllBooks(@RequestParam String searchString) {		
		try {
			if (!searchString.isEmpty()) {
				return bookRepo.readBookByTitle(searchString);				
			}else {
				return bookRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//	@RequestMapping(value = "/lms/readAuthorsByName/{searchString}", method = RequestMethod.GET, produces = "application/json")
//	@ResponseBody
//	public List<Author> readAuthorsByName(@PathVariable("searchString") String searchString) {
//		try {
//			List<Author> authors = authorRepo.readAllAuthorsByName(searchString);
//			for (Author a : authors) {
//				a.setBooks(bdao.getBooksByAuthorID(a.getAuthorId()));
//			}
//			return authors;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@RequestMapping(value = "/lms/updateAuthor", method = RequestMethod.POST, consumes = "application/json")
	public String saveAuthor(@RequestBody Author author) {
		String returnString = "";
		try {
			
			if (author.getAuthorId() != null && author.getAuthorName() != null) {
				authorRepo.saveAndFlush(author);
				returnString = "Author updated sucessfully";
			} else if (author.getAuthorId() != null) {
				authorRepo.deleteById(author.getAuthorId());
				returnString = "Author deleted sucessfully";
			} else {
				authorRepo.saveAndFlush(author);
				returnString = "Author saved sucessfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/updatePublisher", method = RequestMethod.POST, consumes = "application/json")
	public String savePublisher(@RequestBody Publisher publisher) {
		String returnString = "";
		try {
			
			if (publisher.getPublisherId() != null && publisher.getPubName() != null) {
				publisherRepo.saveAndFlush(publisher);
				returnString = "Publisher updated sucessfully";
			} else if (publisher.getPublisherId() != null) {
				publisherRepo.deleteById(publisher.getPublisherId());
				returnString = "Publisher deleted sucessfully";
			} else {
				publisherRepo.saveAndFlush(publisher);
				returnString = "Publisher saved sucessfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/readPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> readPublishers(@RequestParam String searchString) {		
		try {
			if (!searchString.isEmpty()) {
				return publisherRepo.readPublishersByName(searchString);
				
			}else {
				return publisherRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/readLoans", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoan> readAllLoans() {		
		try {
			return loanRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/overrideDueDate", method = RequestMethod.POST, consumes = "application/json")
	public String overrideDueDate(@RequestBody BookLoan loan, @RequestParam String dateString) {	
		/*
		 * use localhost:8080/lms/overrideDueDate/?dateString=yyyy-MM-dd
		 */
		String returnString = "";
		Date date = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
			    //Parsing the String
			    date = dateFormat.parse(dateString);
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			loan.setDueDate(date);
			loanRepo.saveAndFlush(loan);
			returnString = "Due date changed!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/readGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> readAllGenres(@RequestParam String searchString) {
		List<Genre> genres = new ArrayList<>();
		try {
			if (!searchString.isEmpty()) {
				genres = genreRepo.readGenresByName(searchString);
			} else {
				genres = (List<Genre>) genreRepo.findAll();
			}
			return genres;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/updateGenre", method = RequestMethod.POST, consumes = "application/json")
	public String updateGenre(@RequestBody Genre genre) {
		String returnString = "";
		try {
			
			if (genre.getGenreId() != null && genre.getGenreName() != null) {
				genreRepo.saveAndFlush(genre);
				returnString = "Genre updated sucessfully";
			} else if (genre.getGenreId() != null) {
				genreRepo.deleteById(genre.getGenreId());
				returnString = "Genre deleted sucessfully";
			} else {
				genreRepo.saveAndFlush(genre);
				returnString = "Genre saved sucessfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/updateBook", method = RequestMethod.POST, consumes = "application/json")
	public String updateBook(@RequestBody Book book) {
		String returnString = "";
		try {
			
			if (book.getBookId() != null && book.getTitle() != null) {
				bookRepo.saveAndFlush(book);
				returnString = "Book updated sucessfully";
			} else if (book.getBookId() != null) {
				bookRepo.deleteById(book.getBookId());
				returnString = "Book deleted sucessfully";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/updateLibBranch", method = RequestMethod.POST, consumes = "application/json")
	public String saveLibBranch(@RequestBody LibBranch branch) {
		String returnString = "";
		try {
			
			if (branch.getBranchId() != null && branch.getBranchName() != null) {
				branchRepo.saveAndFlush(branch);
				returnString = "LibBranch updated sucessfully";
			} else if (branch.getBranchId() != null) {
				branchRepo.deleteById(branch.getBranchId());
				returnString = "LibBranch deleted sucessfully";
			} else {
				branchRepo.saveAndFlush(branch);
				returnString = "LibBranch saved sucessfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/readLibBranchs", method = RequestMethod.GET, produces = "application/json")
	public List<LibBranch> readLibBranchs(@RequestParam String searchString) {		
		try {
			if (!searchString.isEmpty()) {
				return branchRepo.readLibraryBranchByName(searchString);
				
			}else {
				return branchRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/lms/updateborrower", method = RequestMethod.POST, consumes = "application/json")
	public String saveborrower(@RequestBody Borrower borrower) {
		String returnString = "";
		try {
			
			if (borrower.getCardNo() != null && borrower.getName() != null) {
				borrowRepo.saveAndFlush(borrower);
				returnString = "borrower updated sucessfully";
			} else if (borrower.getCardNo() != null) {
				borrowRepo.deleteById(borrower.getCardNo());
				returnString = "borrower deleted sucessfully";
			} else {
				borrowRepo.saveAndFlush(borrower);
				returnString = "borrower saved sucessfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	@RequestMapping(value = "/lms/readborrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> readborrowers(@RequestParam String searchString) {		
		try {
			if (!searchString.isEmpty()) {
				return borrowRepo.readBorrowersByName(searchString);
				
			}else {
				return borrowRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

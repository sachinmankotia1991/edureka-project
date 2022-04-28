package com.amazingbook.bookms.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazingbook.bookms.Repository.BookRepository;
import com.amazingbook.bookms.model.Book;

@RestController
public class BookResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookResource.class);

	@Autowired
	private BookRepository bookRepository;

	/**
	 * This method will fetch book object from database based on isbn
	 * 
	 * @param isbn
	 * @return book object
	 */
	@GetMapping("/book/{isbn}")
	public Book fetchBook(@PathVariable("isbn") String isbn) {
		LOGGER.info("bookms: fetchBook method is called");
		return bookRepository.getById(isbn);
	}

	/**
	 * This method will save book object in database
	 * 
	 * @param book
	 * @return saved book object
	 */
	@PostMapping("/book")
	public Book addBook(@RequestBody Book book) {
		LOGGER.info("bookms: addBook method is called : " + book.toString());
		return bookRepository.save(book);
	}

	/**
	 * This method will fetch all books from database
	 * 
	 * @return list of books from database
	 */
	@GetMapping("/books")
	public List<Book> fetchBooks() {
		LOGGER.info("bookms: fetchBooks method is called");
		return bookRepository.findAll();
	}

	/**
	 * This method will delete book based on isbn
	 * 
	 * @param isbn
	 */
	@DeleteMapping("/book/{isbn}")
	public void deleteBook(@PathVariable("isbn") String isbn) {
		LOGGER.info("bookms: deleteBook method is called");
		bookRepository.deleteById(isbn);
	}

	/**
	 * This method will update book details in database
	 * 
	 * @param book
	 * @param isbn
	 * @return
	 */
	@PutMapping("/book/{isbn}")
	public Book editBook(@RequestBody Book book, @PathVariable("isbn") String isbn) {
		LOGGER.info("bookms: editBook method is called");
		return bookRepository.save(book);

	}

}

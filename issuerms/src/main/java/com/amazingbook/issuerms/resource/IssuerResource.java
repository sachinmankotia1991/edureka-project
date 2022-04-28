package com.amazingbook.issuerms.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazingbook.issuerms.Repository.IssuerRepository;
import com.amazingbook.issuerms.model.Acknowledgement;
import com.amazingbook.issuerms.model.Book;
import com.amazingbook.issuerms.model.IssuerBO;
import com.amazingbook.issuerms.service.IssuerService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class IssuerResource {

	@Autowired
	private IssuerRepository issuerRepository;

	@Autowired
	private IssuerService issuerService;

	/**
	 * This method will be used to issue book based in its availability - Fetch(REST
	 * call) Book Details eg bookId and availableCopies(totalCopies - issuedCopies)
	 * - Issue the book to the customer, if the book is available - Update
	 * issuedCopies in bookms
	 * 
	 * @param issueBO
	 * @return response message
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IssuerResource.class);

	@HystrixCommand(fallbackMethod = "fallback_method")
	@PostMapping("/issue")
	public ResponseEntity<Acknowledgement> issueBook(@RequestBody IssuerBO issueBO) {
		LOGGER.info("issuerms: issueBook method is called");
		Acknowledgement acknowledgement = new Acknowledgement();

		Book book = issuerService.getBookDetails(issueBO);
		LOGGER.info("issuerms: book object is " + book);

		int availableCopies = book.getTotalCopies() - book.getIssuedCopies();
		LOGGER.info("issuerms: availableCopies is " + availableCopies);

		if (availableCopies >= issueBO.getNoOfCopies()) {
			LOGGER.info("issuerms: Before calling issuerRepository Save method");
			issuerRepository.save(issueBO);
			issuerService.updateBookDetails(book, issueBO);
		} else {
			acknowledgement.setResult("Failure");
			acknowledgement.setErrorCode("ISSUERMS_001");
			acknowledgement.setErrorDescription("insufficient books in stock");
			return new ResponseEntity<>(acknowledgement, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		acknowledgement.setResult("Success");
		return new ResponseEntity<>(acknowledgement, HttpStatus.OK);

	}

	@SuppressWarnings("unused")
	private ResponseEntity<Acknowledgement> fallback_method(@RequestBody IssuerBO issueBO) {
		LOGGER.info("issuerms: fallbakc method implemented");
		Acknowledgement acknowledgement = new Acknowledgement();
		acknowledgement.setResult("Failure");
		acknowledgement.setErrorCode("ISSUERMS_002");
		acknowledgement.setErrorDescription("Not able to invoke Book service. Service will be back shortly");
		return new ResponseEntity<>(acknowledgement, HttpStatus.GATEWAY_TIMEOUT);
	}

	/**
	 * Method to return details about all issued books
	 * 
	 * @param
	 * @return List<IssueBO>
	 */
	@GetMapping("/issues")
	public List<IssuerBO> getAllBookings() {
		LOGGER.info("issuerms: getAllBookings method is called");
		return issuerRepository.findAll();

	}

}

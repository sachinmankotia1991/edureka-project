package com.amazingbook.issuerms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amazingbook.issuerms.model.Book;
import com.amazingbook.issuerms.model.IssuerBO;

@Service
public class IssuerServiceImpl implements IssuerService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Book getBookDetails(IssuerBO issueBO) {
		return restTemplate.getForObject("http://BOOKMS/book/" + issueBO.getIsbn(), Book.class);
	}

	@Override
	public void updateBookDetails(Book book, IssuerBO issueBO) {
		final String uriForPut = "http://BOOKMS/book/" + issueBO.getIsbn();
		book.setIssuedCopies(book.getIssuedCopies() + issueBO.getNoOfCopies());
		restTemplate.put(uriForPut, book);
	}

}

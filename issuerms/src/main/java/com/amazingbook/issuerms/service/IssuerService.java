package com.amazingbook.issuerms.service;

import com.amazingbook.issuerms.model.Book;
import com.amazingbook.issuerms.model.IssuerBO;

public interface IssuerService {

	public Book getBookDetails(IssuerBO issueBO);

	public void updateBookDetails(Book book, IssuerBO issueBO);
}

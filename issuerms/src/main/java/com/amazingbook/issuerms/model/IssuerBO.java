package com.amazingbook.issuerms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_issuer_details")

public class IssuerBO {

	@Id
	@Column(name = "issuer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int issuerId;
	private String isbn;
	@Column(name = "cust_id")
	private int custId;
	@Column(name = "no_of_copies")
	private int noOfCopies;

	public int getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(int issuerId) {
		this.issuerId = issuerId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	@Override
	public String toString() {
		return "IssuerBO [issuerId=" + issuerId + ", isbn=" + isbn + ", custId=" + custId + ", noOfCopies=" + noOfCopies
				+ "]";
	}

}

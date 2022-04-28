package com.amazingbook.bookms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazingbook.bookms.model.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}

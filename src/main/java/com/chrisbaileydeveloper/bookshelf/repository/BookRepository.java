package com.chrisbaileydeveloper.bookshelf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chrisbaileydeveloper.bookshelf.domain.Book;

/**
 * Spring Data MongoDB repository for the Book entity.
 */
public interface BookRepository extends MongoRepository<Book,String> {
	 Book findOneById(String id);
}

package com.chrisbaileydeveloper.bookshelf.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chrisbaileydeveloper.bookshelf.domain.Mongobook;

/**
 * Spring Data MongoDB repository for the Mongobook entity.
 */
public interface MongobookRepository extends MongoRepository<Mongobook,String> {
	 Mongobook findOneById(String id);
	 
	 List<Mongobook> findAll();
	 
	 void delete(Mongobook b);
	 
	 void deleteAll();
}

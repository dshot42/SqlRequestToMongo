package com.test.springMongo.mongoDb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface ElementRepository<T, Long> extends MongoRepository<T, Long> {
}

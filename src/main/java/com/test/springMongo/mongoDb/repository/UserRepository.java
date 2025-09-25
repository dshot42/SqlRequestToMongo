package com.test.springMongo.mongoDb.repository;

import com.test.springMongo.mongoDb.model.admin.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
}
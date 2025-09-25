package com.test.springMongo.mongoDb.repository;

import com.test.springMongo.mongoDb.model.admin.User;
import com.test.springMongo.mongoDb.model.productionstate.Part;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public
interface PartRepository extends MongoRepository<Part, Long> {
}
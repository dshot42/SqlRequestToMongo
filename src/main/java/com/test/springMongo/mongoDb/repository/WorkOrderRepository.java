package com.test.springMongo.mongoDb.repository;

import com.test.springMongo.mongoDb.model.productionstate.Workorder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkOrderRepository extends MongoRepository<Workorder, Long> {
}
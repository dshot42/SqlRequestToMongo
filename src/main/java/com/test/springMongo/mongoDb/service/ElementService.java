package com.test.springMongo.mongoDb.service;


import com.test.springMongo.mongoDb.criteriaFilter.CriteriaFilter;
import com.test.springMongo.mongoDb.criteriaFilter.CriteriaMongoFilter;
import com.test.springMongo.mongoDb.model.productionstate.Workorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.*;


//@CrossOrigin(origins = "http://localhost:4200")
@Component
public class ElementService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;



    public List<Object> getAllElements(Class model) {
        return mongoTemplate.findAll(model);
    }


    public Object getElementById(Class model, Object elementId) {
        return mongoTemplate.findById(elementId, model);
    }


    public List<Object> getElementBy(Class model, String filter) {
        try {
            if (filter != null && !filter.isEmpty()) {
                Query query = CriteriaMongoFilter.queryBuilder(filter);
                return mongoTemplate.find(query, model);
            }
            return mongoTemplate.findAll(model);
        } catch (Exception ex) {
            System.out.println(filter+", model :"+model);
        }
        return new LinkedList<>();
    }


    public Integer count(Class model, String filter) {
        if (filter == null || filter.isEmpty())
            return Math.toIntExact(mongoTemplate.estimatedCount(model));
        Query query = CriteriaMongoFilter.queryBuilder(filter);
        return Math.toIntExact(mongoTemplate.count(query, model));
    }


    public List<Object> getElementBy(Class model, String field, String value) {
        Query query = new Query().addCriteria(CriteriaMongoFilter.queryEquals(field, value));
        return mongoTemplate.find(query, model);
    }


    public List<Object> getElementBy(Class model, String filter, long offset, long limit) {
        // List<List<String>> => key = field,value,filter
        // operateur Map<List<List<String>> => key : operator (and) => list[0] with list[1]
        Query query = new Query();
        if (filter != null && !filter.isEmpty()) {
            query = CriteriaMongoFilter.queryBuilder(filter);
        }
        query.skip(offset).limit((int) limit);
        List<Object> objs = mongoTemplate.find(query, model);
        System.out.println(objs);
        return objs;
    }

    public Set<String> getCollectionsName() {
        return mongoTemplate.getCollectionNames();
    }

    public Object updateOrInsert(Class model, Object datas) {
        if (model != null)
            return mongoTemplate.save(datas, model.getName());
        else
            return mongoTemplate.save(datas);
    }

    public Object updateOrInsert(String model, Object datas) {
        return mongoTemplate.save(datas, model);
    }

    public Object updateElement(Class model, Object elementId, Object datas) {
        Object thisElement = this.getElementById(model, elementId);

        if (thisElement == null) return null; // gerer les exception !
        // jacksonMapper node des data
        return mongoTemplate.save(datas, model.getName().substring(model.getPackageName().length() + 1));
    }

    public Object deleteElement(Class model, Object elementId) {
        Object thisElement = this.getElementById(model, elementId);

        if (thisElement == null) return null; // gerer les exception !
        // jacksonMapper node des data
        Query query = new Query().addCriteria(CriteriaMongoFilter.queryEquals("_id", elementId));
        return mongoTemplate.remove(query, model.getName().substring(model.getPackageName().length() + 1));
    }

    public Object deleteElementByFilter(Class model, String filter) {
        Query query = null;
        if (filter != null && !filter.isEmpty()) {
            query = CriteriaMongoFilter.queryBuilder(filter); // AND OR  BETWEEN ... implementer
        }
        // jacksonMapper node des data
        return mongoTemplate.remove(query, model.getName().substring(model.getPackageName().length() + 1));
    }
}
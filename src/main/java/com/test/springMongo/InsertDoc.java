package com.test.springMongo;


import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertDoc {

    public static int cpt = 0;
    public static String uri = "mongodb://localhost:27017/pacali";

    @GetMapping("/importTest")
    private static void insertDocuments() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("pacali");
            MongoCollection<Document> collection = database.getCollection("springImportTest");
            while (true) {
                if (cpt == 1000000) break;
                insertDoc(collection);
                cpt++;
                System.out.println(cpt);
            }
        }
    }


    private static void insertDoc(MongoCollection<Document> collection) {
        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("title", "Lorem ipsum , entry.index  =>" + String.valueOf(cpt))
                    .append("Description", "spring test"));
            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }

}

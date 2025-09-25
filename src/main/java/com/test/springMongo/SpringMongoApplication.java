package com.test.springMongo;

import com.test.springMongo.mongoDb.model.ElementEntity;
import com.test.springMongo.mongoDb.model.productionstate.Workorder;
import com.test.springMongo.mongoDb.model.productionstate.WorkorderCN;
import com.test.springMongo.mongoDb.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@SpringBootApplication
public class SpringMongoApplication {

    static ElementService elementService;
    @Autowired
    public SpringMongoApplication(ElementService elementService) {
        this.elementService = elementService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoApplication.class, args);
        elementService.getAllElements(Workorder.class).forEach(e -> {
            System.out.println(e);
        });
        elementService.getElementBy(Workorder.class,"workOrderId","LCT2304859").get(0);
        Workorder mobj = Workorder.class.cast( elementService.getElementById(Workorder.class,2L));
        System.out.println(mobj.getWorkOrderId());
    }
}

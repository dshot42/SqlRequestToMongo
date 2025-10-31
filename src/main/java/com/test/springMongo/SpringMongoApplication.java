package com.test.springMongo;

import com.test.springMongo.mongoDb.model.productionstate.Part;
import com.test.springMongo.mongoDb.model.productionstate.Workorder;
import com.test.springMongo.mongoDb.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

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

        /*
        elementService.getAllElements(Part.class).forEach(e -> {
            System.out.println(e);
        });

         */

        System.out.println("//////////Workorders request /////////");


        Workorder mobj = Workorder.class.cast(elementService.getElementBy(Workorder.class, "workOrderId", "LCT2304859").get(0));

        elementService.getElementBy(Workorder.class, "key1=toto AND (key2=titi OR key2=fs)", 0, 10L);

        elementService.getElementBy(Workorder.class, " (key1=toto AND key2=tata) OR (key2=fssd OR key2=gfddg OR key2=gfddg)", 0, 10L);

        elementService.getElementBy(Workorder.class, " key1 = tata AND key2 = tata AND (key1=toto OR key2=tata)", 0, 10L);


        elementService.getElementBy(Workorder.class, " key1 = tata AND key2 = tata AND (key1=toto OR key2=tata AND ( key2=tutu OR key1=toto) AND key2=tata) OR (key1=tata AND key2=tutu)", 0, 10L);
        elementService.getElementBy(Workorder.class, " key1=toto AND (key2=tafdta OR  key2=tfdutu)", 0, 10L);
        elementService.getElementBy(Workorder.class, " key1=toto AND (key2=tata OR  key2=andtutu)", 0, 10L);
        elementService.getElementBy(Workorder.class, " key1=toto AND (key2=tata OR  key2=tutu)", 0, 10L);

        elementService.getElementBy(Workorder.class, "clemNo MATCH LCT2304859 AND createdAt > 2023-05-31T22:00:00.000Z AND createdAt < 2023-07-04T21:59:59.000Z ", 0, 10L);

        elementService.getElementBy(Workorder.class, "id MATCH _51 AND createdAt > 2023-05-31T22:00:00.000Z", 0, 10L);

        elementService.getElementBy(Workorder.class, "id MATCH INSTANCE AND (workorderState=PLANNED OR workorderState=CLOSED) AND id MATCH INSTANCE ", 0, 10L);

        elementService.getElementBy(Workorder.class, "workorderState=PLANfNED OR workorderState=OPENED OR workorderState=PLANNED", 0, 10L);


        elementService.getElementBy(Workorder.class, "(createdAt > 2023-06-20T22:00:00.000Z AND createdAt < 2023-07-05T21:59:59.000Z) AND ( workorderState=PLANNED OR workorderState=OPENED OR workorderState=IN_PROGRESS)", 0, 10L);


        elementService.getElementBy(Workorder.class, "(createdAt > 2023-06-20T22:00:00.000Z AND createdAt < 2023-07-05T21:59:59.000Z) AND ( workorderState=PLANNED OR workorderState=OPENED OR workorderState=IN_PROGRESS) AND id MATCH INS", 0, 10L);

        elementService.getElementBy(Workorder.class, "(createdAt > 2023-06-20T22:00:00.000Z AND createdAt < 2023-07-05T21:59:59.000Z) AND ( workorderState=PLANNED OR workorderState=OPENED OR workorderState=IN_PROGRESS)", 0, 10L);

        elementService.getElementBy(Workorder.class, "(createdAt > 2023-06-20T22:00:00.000Z AND createdAt < 2023-07-05T21:59:59.000Z) AND ( workorderState=PLANNED OR workorderState=OPENED OR workorderState=IN_PROGRESS) AND workOrderId MATCH INSTANCE", 0, 10);
        elementService.getElementBy(Workorder.class, "id MATCH INSTANCE AND (createdAt > 2023-05-31T22:00:00.000Z AND createdAt < 2023-07-04T21:59:59.000Z) AND (workorderState=OPENED OR workorderState=CLOSED)", 0, 10L);


        elementService.getElementBy(Workorder.class, "(id MATCH INSTANCE)  AND (workorderState=PLANNED OR workorderState=CLOSED) ", 0, 10L);


        elementService.getElementBy(Workorder.class, "id MATCH INSTANCE  AND (workorderState=PLANNED OR workorderState=CLOSED) ", 0, 10L);

        elementService.getElementBy(Workorder.class, "(createdAt > 2023-05-31T22:00:00.000Z AND createdAt < 2023-07-04T21:59:59.000Z) AND (workorderState=OPENED OR workorderState=CLOSED) AND id MATCH _51", 0, 10L);


        elementService.getElementBy(Workorder.class, "(createdAt > 2023-06-07T22:00:00.000Z AND createdAt < 1023-06-29T22:00:00.000Z)", 0, 10L);

        elementService.getElementBy(Workorder.class, "createdAt > 2023-06-07T22:00:00.000Z AND createdAt < 2023-06-29T22:00:00.000Z AND ( workorderState=d OR workorderState=fs OR workorderState=PLANNED)", 0, 10L);

        elementService.getElementBy(Workorder.class, "(createdAt > 2023-06-07T22:00:00.000Z AND createdAt < 2023-06-29T22:00:00.000Z) AND ( workorderState=IN_PROGRESS OR workorderState=OPENED OR workorderState=CLOSED)", 0, 10L);

        elementService.getElementBy(Workorder.class, "(createdAt > 2023-01-29T22:00:00.000Z AND createdAt < 2023-12-27T21:59:59.000Z) AND (( workorderState=OPENED OR workorderState=IN_PROGRESS) AND id MATCH ins)", 0, 10L);


        elementService.getElementBy(Workorder.class, "id MATCH 234 AND ( workorderState=OPENED OR workorderState=IN_PROGRESS OR workorderState=PLANNED)", 0, 10L);


        elementService.getElementBy(Workorder.class, "(createdAt > 2023-01-29T22:00:00.000Z AND createdAt < 2023-12-27T21:59:59.000Z) AND ( workorderState=OPENED OR workorderState=IN_PROGRESS OR workorderState=PLANNED)", 0, 10L);

        elementService.getElementBy(Workorder.class, " workorderState=PLANfNED OR workorderState=PLdANNED OR  workorderState=PLANNED ", 0, 10L);


        elementService.getElementBy(Workorder.class, "id MATCH instance AND  (workorderState=sf OR  workorderState=PLANNED)", 0, 10L);


        elementService.getElementBy(Workorder.class, "id=fdsfs OR  workorderState=sf OR  workorderState=PLANNED ", 0, 10L);

        elementService.getElementBy(Workorder.class, "(workorderState=fdsfs OR  workorderState=sf OR  workorderState=sf) AND (createdAt > 2023-01-29T22:00:00.000Z AND createdAt < 2023-12-27T21:59:59.000Z) ", 0, 10L);

        elementService.getElementBy(Workorder.class, "(createdAt > 2023-01-29T22:00:00.000Z AND createdAt < 2023-12-27T21:59:59.000Z) AND (workorderState=hf OR workorderState=hf OR workorderState=sf) ", 0, 10L);


        elementService.getElementBy(Workorder.class, "toto=tata OR titi=tutu AND tutu=titi AND lila=lali ", 0, 10L);

        elementService.getElementBy(Workorder.class, "toto MATCH tata ", 0, 10L);
        elementService.getElementBy(Workorder.class, "toto=tata AND tutu=titi", 0, 10L);

        elementService.getElementBy(Workorder.class, "toto=tata OR tutu=titi", 0, 10L);

        elementService.getElementBy(Workorder.class, "tutu=titi AND lila=lali  AND ( toto=tata OR titi=tutu )", 0, 10L);

        // Part tests

        System.out.println("//////////Part request /////////");
        elementService.getElementBy(Part.class,
                "state=Passed  ", 0, 10L);

        elementService.getElementBy(Part.class,
                "lastPartHistory.AtStep=OP6500.2-1", 0, 10L);

        elementService.getElementBy(Part.class,
                "state=\"Passed\" AND latestStepForPart=\"Chargement\" " +
                        "AND lastPartHistory.AtStep=OP6500.2-1", 0, 10L);



    }
}
package com.test.springMongo.mongoDb.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.springMongo.mongoDb.service.ElementService;
import com.test.springMongo.mongoDb.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/testApi2")
public class ElementControllerGeneric {

    public static final String prefixEntityModel = "com.test.springMongo.mongoDb.model.productionstate.";
    @Autowired
    ElementService elementService;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;



    @GetMapping("/{element}")
    public Object getAllElements(@PathVariable(value = "element") String element) throws ClassNotFoundException {
        //curl -X GET localhost:8090/api/vi/elements/WorkOrder
        return ResponseEntity.ok(elementService.getAllElements(this.getClassForName(element)));
    }

    @PostMapping("/{element}")
    public ResponseEntity<Object> createElement(@PathVariable(value = "element") String element, @Valid @RequestBody String datas) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(datas);
        ((ObjectNode) jsonNode).put("_id", sequenceGeneratorService.generateSequence(element + "_seq"));
        return ResponseEntity.ok(elementService.updateOrInsert(this.getClassForName(element), jsonNode.toString()));
    }

    ///////
    @PutMapping("/{element}/{id}")
    public ResponseEntity<Object> updateElement(@PathVariable(value = "element") String element,
                                                @PathVariable(value = "id") Long elementId, @Valid @RequestBody String datas) throws Throwable {
        return ResponseEntity.ok(elementService.updateElement(this.getClassForName(element), elementId, datas));
    }

    @DeleteMapping("/{element}/{id}")
    public ResponseEntity<Object> deleteElement(@PathVariable(value = "element") String element,
                                                @PathVariable(value = "id") Long elementId)
            throws Throwable {
        return ResponseEntity.ok(elementService.deleteElement(this.getClassForName(element), elementId));
    }

    private static Class<?> getClassForName(String element) throws ClassNotFoundException {
        return Class.forName(element.contains(".") ?
                element
                :
                prefixEntityModel + element);
    }

}
package com.test.springMongo.mongoDb.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springMongo.mongoDb.repository.UserRepository;
import com.test.springMongo.mongoDb.repository.WorkOrderRepository;
import com.test.springMongo.mongoDb.model.ElementEntity;
import com.test.springMongo.mongoDb.model.admin.User;
import com.test.springMongo.mongoDb.model.productionstate.Workorder;
import com.test.springMongo.mongoDb.model.productionstate.WorkorderCN;
import com.test.springMongo.mongoDb.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/testApi")
public class ElementController {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected WorkOrderRepository workorderRepository;
    @Autowired
    protected SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/test/{toto}")
    public ResponseEntity getTestToto(@PathVariable(value = "toto") String toto) {
        //curl -X GET localhost:8090/api/vi/element
        return ResponseEntity.ok(toto);
    }

    @GetMapping("/elements/{element}")
    public List<? extends ElementEntity> getAllElements(@PathVariable(value = "element") String element) {
        //curl -X GET localhost:8090/api/vi/elements/WorkOrder
        List<? extends ElementEntity> datas = null;
        if (element.equals(User.class.getName())) {
            datas = userRepository.findAll();
        } else {
            datas = workorderRepository.findAll();
        }
        return datas;
    }

    @GetMapping("/{element}/{id}")
    public ResponseEntity<? extends ElementEntity> getElementById(@PathVariable(value = "element") Class element, @PathVariable(value = "id") Long elementId)
            throws Throwable {
        ElementEntity obj = null;
        if (element.isInstance(User.class)) {
            obj = (ElementEntity) userRepository.findById(elementId)
                    .orElseThrow(() -> new Exception("Element not found for this id :: " + elementId));
        } else {
            obj = (ElementEntity) workorderRepository.findById(elementId)
                    .orElseThrow(() -> new Exception("Element not found for this id :: " + elementId));
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/{element}")
    public ElementEntity createElement(@PathVariable(value = "element") String element, @Valid @RequestBody String datas) throws IOException, ClassNotFoundException {
        ElementEntity resp = null;
        switch (element) {
            case "User":
                User user = new ObjectMapper().readValue(datas, User.class);
                user.setId(sequenceGeneratorService.generateSequence(User.class.getName()+"_seq"));
                resp = userRepository.save(user);
                break;
            case "WorkOrderCN":
                Workorder workorder = new ObjectMapper().readValue(datas, WorkorderCN.class);
                workorder.setIdEntity(sequenceGeneratorService.generateSequence(Workorder.class.getName()+"_seq"));
                resp = workorderRepository.save(workorder);
                break;
        }
        return resp;
    }

    /////////
    @PutMapping("/{element}/{id}")
    public ResponseEntity<? extends ElementEntity> updateElement(
            @PathVariable(value = "id") Long elementId, @Valid @RequestBody String datas) throws Throwable {
        User thisElement = userRepository.findById(elementId)
                .orElseThrow(() -> new Exception("Element not found for this id :: " + elementId));

        // jacksonMapper node des data
        thisElement = new ObjectMapper().readValue(datas, User.class);
        thisElement.setId(elementId);
        ElementEntity updatedElement = userRepository.save(thisElement);
        return ResponseEntity.ok(updatedElement);
    }

    @DeleteMapping("/{element}/{id}")
    public Map<String, Boolean> deleteElement(@PathVariable(value = "id") Long elementId)
            throws Throwable {
        User element = userRepository.findById(elementId)
                .orElseThrow(() -> new Exception("Element not found for this id :: " + elementId));

        userRepository.delete(element);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
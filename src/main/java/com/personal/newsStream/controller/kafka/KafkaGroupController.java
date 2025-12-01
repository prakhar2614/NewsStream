package com.personal.newsStream.controller.kafka;

import com.personal.newsStream.definition.KafkaGroupCreateEntry;
import com.personal.newsStream.definition.KafkaGroupUpdateEntry;
import com.personal.newsStream.service.kafka.KafkaGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */

@RestController
@RequestMapping("news-stream/kafka/group/")
public class KafkaGroupController {

    @Autowired
    KafkaGroupService kafkaGroupService;


    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody(required = true)KafkaGroupCreateEntry kafkaGroupCreateEntry){
        return new ResponseEntity(kafkaGroupService.save(kafkaGroupCreateEntry), HttpStatus.CREATED);
    }


    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody(required = true) KafkaGroupUpdateEntry kafkaGroupUpdateEntry){
        return kafkaGroupService.update(kafkaGroupUpdateEntry);
    }

    @GetMapping(value = "find/{groupName}")
    public ResponseEntity findByName(@PathVariable("groupName") String groupName){
        return kafkaGroupService.findByName(groupName);
    }


}

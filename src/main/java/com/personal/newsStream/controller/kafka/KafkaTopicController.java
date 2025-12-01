package com.personal.newsStream.controller.kafka;

import com.personal.newsStream.definition.KafkaTopicCreateEntry;
import com.personal.newsStream.definition.KafkaTopicUpdateEntry;
import com.personal.newsStream.service.kafka.KafkaTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */
@RestController
@RequestMapping("news-stream/kafka/topic/")
public class KafkaTopicController {

    @Autowired
    KafkaTopicService kafkaTopicService;
    // Create
    @PostMapping(value = "create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody(required = true) KafkaTopicCreateEntry requestBody){
        return kafkaTopicService.create(requestBody);
    }

    // Update
    @PutMapping(value = "update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody(required = true) KafkaTopicUpdateEntry requestBody){
        return kafkaTopicService.update(requestBody);
    }

    // find
    @PutMapping(value = "findByName/{topicName}")
    public ResponseEntity<Map<String, Object>> findByName(@PathVariable(value = "topicName")String topicName){
        return kafkaTopicService.findByName(topicName);
    }




    // create
    // update
    // pushToKafka
    // topic offset

}

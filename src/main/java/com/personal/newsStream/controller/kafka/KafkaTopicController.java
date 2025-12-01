package com.personal.newsStream.controller.kafka;

import com.personal.newsStream.definition.KafkaTopicCreateEntry;
import com.personal.newsStream.service.kafka.KafkaTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    // read
    @PostMapping(value = "create")
    public ResponseEntity<Map<String, Object>> getTopic(@RequestBody(required = true) KafkaTopicCreateEntry requestBody){
        return new ResponseEntity<>(kafkaTopicService.create(requestBody), HttpStatus.CREATED);
    }




    // create
    // update
    // pushToKafka
    // topic offset

}

package com.personal.newsStream.controller.kafka;

import com.personal.newsStream.definition.KafkaConsumerCreateEntity;
import com.personal.newsStream.service.kafka.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */
@RestController
@RequestMapping("news-stream/kafka/consumer/")
public class KafkaConsumerController {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @PostMapping(value = "create")
    public ResponseEntity create(KafkaConsumerCreateEntity kafkaConsumerCreateEntity){
        return kafkaConsumerService.create(kafkaConsumerCreateEntity);
    }


}

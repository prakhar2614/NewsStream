package com.personal.newsStream.controller.kafka;

import com.personal.newsStream.definition.KafkaConsumerCreateEntity;
import com.personal.newsStream.definition.KafkaConsumerUpdateEntity;
import com.personal.newsStream.service.kafka.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */
@RestController
@RequestMapping("news-stream/kafka/consumer/")
public class KafkaConsumerController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;
//    @Autowired
//    private Consumer consumer;

    @PostMapping(value = "create")
    public ResponseEntity<Map<String, Object>> create(KafkaConsumerCreateEntity kafkaConsumerCreateEntity) {
        return kafkaConsumerService.create(kafkaConsumerCreateEntity);
    }

    @PutMapping(value = "update")
    public ResponseEntity<Map<String, Object>> update(KafkaConsumerUpdateEntity kafkaConsumerUpdateEntity) {
        return kafkaConsumerService.update(kafkaConsumerUpdateEntity);
    }

    @GetMapping(value = "find/{consumerName}")
    public ResponseEntity<Map<String, Object>> findByName(@PathVariable(value = "consumerName") String consumerName) {
        return kafkaConsumerService.findByName(consumerName);
    }

    @PostMapping(value = "start/{consumerName}")
    public ResponseEntity<Map<String, Object>> consumerStart(@PathVariable(value = "consumerName") String consumerName) {
        return kafkaConsumerService.startConsumer(consumerName);
    }


}

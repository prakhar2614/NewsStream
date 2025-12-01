package com.personal.newsStream.service.kafka;

import com.personal.newsStream.definition.KafkaTopicCreateEntry;
import com.personal.newsStream.definition.KafkaTopicUpdateEntry;
import com.personal.newsStream.entity.kafka.KafkaTopic;
import com.personal.newsStream.repository.kafka.KafkaTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */
@Service
public class KafkaTopicService {

    @Autowired
    private KafkaTopicRepository kafkaTopicRepository;

    public ResponseEntity<Map<String, Object>> create(KafkaTopicCreateEntry requestBody) {

        String topicName = requestBody.getName();
        String status = requestBody.getStatus();
        Long retentionPeriod = requestBody.getRetentionPeriod();
        Integer partition = requestBody.getPartition();

        // CREATE
        KafkaTopic topic = new KafkaTopic(
                null,           // id → let Mongo generate
                topicName,
                partition,
                retentionPeriod,
                status
        );
        topic = kafkaTopicRepository.save(topic);  // INSERT

        return new ResponseEntity(Map.of("topic", topic), HttpStatus.CREATED);
    }

    public ResponseEntity<Map<String, Object>> update(KafkaTopicUpdateEntry requestBody) {
        String topicName = requestBody.getName();
        String status = requestBody.getStatus();
        Long retentionPeriod = requestBody.getRetentionPeriod();
        Integer partition = requestBody.getPartition();

        // Check existing topic by name
        KafkaTopic existingTopic = kafkaTopicRepository.findByName(topicName);

        if (existingTopic == null) {
            return new ResponseEntity<>(Map.of("result", "Topic not found for given id"), HttpStatus.BAD_REQUEST);
        }

        existingTopic.setPartition(partition);
        existingTopic.setRetentionPeriod(retentionPeriod);
        existingTopic.setStatus(status);

        return new ResponseEntity<>(Map.of("topic", kafkaTopicRepository.save(existingTopic)), HttpStatus.OK);
    }

    public ResponseEntity findByName(String name) {
        KafkaTopic topic = kafkaTopicRepository.findByName(name);
        if (topic != null) {
            return new ResponseEntity<>(Map.of("topic", topic), HttpStatus.FOUND);
        }
        return new ResponseEntity(new HashMap<>(), HttpStatus.BAD_REQUEST);
    }
}

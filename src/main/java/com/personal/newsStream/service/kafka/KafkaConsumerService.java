package com.personal.newsStream.service.kafka;

import com.personal.newsStream.definition.KafkaConsumerCreateEntity;
import com.personal.newsStream.definition.KafkaConsumerUpdateEntity;
import com.personal.newsStream.definition.KafkaTopicCreateEntry;
import com.personal.newsStream.definition.KafkaTopicUpdateEntry;
import com.personal.newsStream.entity.kafka.KafkaConsumer;
import com.personal.newsStream.entity.kafka.KafkaGroup;
import com.personal.newsStream.entity.kafka.KafkaTopic;
import com.personal.newsStream.repository.kafka.KafkaConsumerRepository;
import com.personal.newsStream.repository.kafka.KafkaGroupRepository;
import com.personal.newsStream.repository.kafka.KafkaTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */

@Service
public class KafkaConsumerService {

    @Autowired
    KafkaConsumerRepository kafkaConsumerRepository;
    @Autowired
    KafkaGroupRepository kafkaGroupRepository;
    @Autowired
    KafkaTopicRepository kafkaTopicRepository;



    public ResponseEntity<Map<String, Object>> create(KafkaConsumerCreateEntity requestBody) {
        String name = requestBody.getName();
        String status = requestBody.getStatus();
        Map<String, List<String>> groupTopicMap = requestBody.getGroupTopicMap();
        Map<KafkaGroup, List<KafkaTopic>> kafkaGroupListTopicMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry: groupTopicMap.entrySet()){
            String groupName = entry.getKey();
            KafkaGroup group = kafkaGroupRepository.findByName(groupName);
            if (group == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<KafkaTopic>topicList = new ArrayList<>();
            for(String topicName: entry.getValue()){
                KafkaTopic topic = kafkaTopicRepository.findByName(topicName);
                if (topic== null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                topicList.add(topic);
            }
            kafkaGroupListTopicMap.put(group, topicList);
        }

        // CREATE
        KafkaConsumer consumer = new KafkaConsumer(
                null,           // id → let Mongo generate
                name,
                kafkaGroupListTopicMap,
                status
        );
        consumer = kafkaConsumerRepository.save(consumer);  // INSERT

        return new ResponseEntity(Map.of("consumer", consumer), HttpStatus.CREATED);
    }

    public ResponseEntity<Map<String, Object>> update(KafkaConsumerUpdateEntity requestBody) {
        String id = requestBody.getId();
        KafkaConsumer existingConsumer = kafkaConsumerRepository.findById(id);

        if (existingConsumer == null) {
            return new ResponseEntity<>(Map.of("result", "Consumer not found for given id"), HttpStatus.BAD_REQUEST);
        }
        String name = requestBody.getName();
        String status = requestBody.getStatus();
        Map<String, List<String>> groupTopicMap = requestBody.getGroupTopicMap();
        Map<KafkaGroup, List<KafkaTopic>> kafkaGroupListTopicMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry: groupTopicMap.entrySet()){
            String groupName = entry.getKey();
            KafkaGroup group = kafkaGroupRepository.findByName(groupName);
            if (group == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<KafkaTopic>topicList = new ArrayList<>();
            for(String topicName: entry.getValue()){
                KafkaTopic topic = kafkaTopicRepository.findByName(topicName);
                if (topic== null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                topicList.add(topic);
            }
            kafkaGroupListTopicMap.put(group, topicList);
        }

        existingConsumer.setStatus(status);
        existingConsumer.setGroupTopicMap(kafkaGroupListTopicMap);

        return new ResponseEntity<>(Map.of("consumer", kafkaConsumerRepository.save(existingConsumer)), HttpStatus.OK);
    }

    public ResponseEntity findByName(String name) {
        KafkaConsumer consumer = kafkaConsumerRepository.findByName(name);
        if (consumer != null) {
            return new ResponseEntity<>(Map.of("consumer", consumer), HttpStatus.FOUND);
        }
        return new ResponseEntity(new HashMap<>(), HttpStatus.BAD_REQUEST);
    }
}

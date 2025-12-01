package com.personal.newsStream.service.kafka;

import com.personal.newsStream.definition.KafkaTopicCreateEntry;
import com.personal.newsStream.entity.kafka.KafkaTopic;
import com.personal.newsStream.repository.kafka.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */
@Service
public class KafkaTopicService {

//    private final MongoClient
    @Autowired
    private TopicRepository topicRepository;

    public Map<String, Object> create(KafkaTopicCreateEntry requestBody) {
        String topicName = requestBody.getName();
        String status = requestBody.getStatus();
        Long retentionPeriod = requestBody.getRetentionPeriod();
        int partition = requestBody.getPartition();
        KafkaTopic topic = new KafkaTopic(topicName, partition, retentionPeriod, status);
        return Map.of("topic",topicRepository.save(topic));
    }
}

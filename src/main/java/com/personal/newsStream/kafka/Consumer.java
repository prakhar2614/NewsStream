package com.personal.newsStream.kafka;

import com.personal.newsStream.entity.kafka.KafkaConsumer;
import com.personal.newsStream.entity.kafka.KafkaGroup;
import com.personal.newsStream.entity.kafka.KafkaTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * prakhar: 28/11/25
 *
 * @since 16
 */
public class Consumer {
    private static Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
    private KafkaConsumer kafkaConsumer;

    public Consumer(com.personal.newsStream.entity.kafka.KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;

    }

    public ResponseEntity<Map<String, Object>> start() {

        Map<KafkaGroup, List<KafkaTopic>> groupTopicMap = this.kafkaConsumer.getGroupTopicMap();
        for (Map.Entry<KafkaGroup, List<KafkaTopic>> entry : groupTopicMap.entrySet()) {
            KafkaGroup kafkaGroup = entry.getKey();
            List<KafkaTopic> kafkaTopics = entry.getValue();
            String groupId = kafkaGroup.getId();

            List<String> topicNames = new ArrayList<>();
            for (KafkaTopic topic : kafkaTopics) {
                topicNames.add(topic.getName());
            }

            Properties consumerProperties = new Properties();
            consumerProperties.put("bootstrap.servers", "localhost:9092");
            consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProperties.put("group.id", groupId);

            org.apache.kafka.clients.consumer.KafkaConsumer consumer = new org.apache.kafka.clients.consumer.KafkaConsumer(consumerProperties);
            consumer.subscribe(topicNames);
            Thread t = new Thread(() -> runConsumer(consumer, groupId), "kafka-consumer-" + groupId);
            t.start();
        }
        return new ResponseEntity<>(Map.of("response", "Consumer started"), HttpStatus.OK);

    }

    private void runConsumer(org.apache.kafka.clients.consumer.KafkaConsumer consumer, String groupId) {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(30));
                for (ConsumerRecord<String, String> record : records) {
                    LOGGER.info("Group: {}, Topic: {}, Partition: {}, Offset: {}, Value: {}",
                            groupId,
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.value());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error in consumer for group {}", groupId, e);
        } finally {
            consumer.close();
            LOGGER.info("Consumer closed for group {}", groupId);
        }
    }

    public void stop(String consumerName) {

//        Consumer consumer = con
    }
}

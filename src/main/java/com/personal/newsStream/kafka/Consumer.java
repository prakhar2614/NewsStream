package com.personal.newsStream.kafka;

import com.personal.newsStream.entity.kafka.KafkaConsumer;
import com.personal.newsStream.repository.kafka.KafkaGroupRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.util.HashMap;
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
    public static final Map<String, org.apache.kafka.clients.consumer.KafkaConsumer> consumerMap = new HashMap<>();
    private final Map<String, Thread> threadMap = new HashMap<>();

    @Autowired
    KafkaGroupRepository groupRepository;

    public Consumer(com.personal.newsStream.entity.kafka.KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    public ResponseEntity<Map<String, Object>> start() {

        Map<String, List<String>> groupTopicMap = this.kafkaConsumer.getGroupTopicMap();
        for (Map.Entry<String, List<String>> entry : groupTopicMap.entrySet()) {
            String groupName = entry.getKey();
            System.out.println("groupId  = "+groupName);

            List<String> topicNames = entry.getValue();

            Properties consumerProperties = new Properties();
            consumerProperties.put("bootstrap.servers", "localhost:9092");
            consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProperties.put("group.id", groupName);

            org.apache.kafka.clients.consumer.KafkaConsumer consumer = new org.apache.kafka.clients.consumer.KafkaConsumer(consumerProperties);
            consumer.subscribe(topicNames);
            consumerMap.put(groupName, consumer);
            Thread t = new Thread(() -> runConsumer(consumer, groupName), "kafka-consumer-" + groupName);
            threadMap.put(groupName, t);
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
        } catch (WakeupException e) {
            LOGGER.info("Stopping consumer for group " + groupId);
        } finally {
            consumer.close();
            LOGGER.info("Consumer closed for group {}", groupId);
        }
    }

    public ResponseEntity<Map<String, Object>> stop(String groupName) {
        org.apache.kafka.clients.consumer.KafkaConsumer consumer = consumerMap.get(groupName);
        if (consumer != null){
            consumer.wakeup();
            return new ResponseEntity<>(Map.of("response", "Consumer stopped"), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("response", "Consumer already stopped"), HttpStatus.ACCEPTED);
    }
}

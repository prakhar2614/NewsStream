package com.personal.newsStream.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * prakhar: 28/11/25
 *
 * @since 16
 */
public class Consumer {
    private static Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
    private KafkaConsumer<String, String> consumer;
    String name;
    public Consumer(String name, String topic, String group) {

        Properties consumerProperties = new Properties();

        consumerProperties.put("bootstrap.servers", "localhost:9092");
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        System.out.println("%%%%%%");
        consumerProperties.put("group.id", group);

        this.name = name;
        consumer = new KafkaConsumer<>(consumerProperties);

        Thread t = new Thread(new ConsumerThread(this, topic));
        t.start();
    }

    public void create(String name, Map<String,String> topicGroupMap){

    }

    public void start(String topic){
        System.out.println("Consumer called");

        System.out.println("Consumer subscribing");
        consumer.subscribe(Collections.singleton(topic));
        System.out.println("Consumer subscribed");

        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(30));
            for (ConsumerRecord<String, String> record: records){
                System.out.println(record.value());
            }
        }
    }

    public void stop(String name){
//        Consumer consumer = con
    }
}

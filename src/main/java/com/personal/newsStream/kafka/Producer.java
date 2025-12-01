package com.personal.newsStream.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class Producer {

    private KafkaProducer kafkaProducer;
    private static Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public Producer(){
        Properties kafkaProducerProperties = new Properties();
        kafkaProducerProperties.put("bootstrap.servers", "localhost:9092");
        kafkaProducerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.kafkaProducer = new KafkaProducer<>(kafkaProducerProperties);
    }


    public void produce(String topic, String message) {
        try {
            ProducerRecord<String, String> messageMap = new ProducerRecord<>(topic, message);
            kafkaProducer.send(messageMap);
            LOGGER.error("Message sent");
        } catch (Exception e) {
            System.out.println("GOT EXCEPTION IN PRODUCER");
            kafkaProducer.close();
        }

    }
}

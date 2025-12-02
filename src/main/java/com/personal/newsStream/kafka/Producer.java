package com.personal.newsStream.kafka;

import com.google.gson.Gson;
import com.personal.newsStream.definition.KafkaMessageRequestBody;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
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


    public ResponseEntity<Map<String, Object>> produce(KafkaMessageRequestBody requestBody) {
        String topic = requestBody.getTopicName();
        System.out.println("topic = "+topic);
        Map<String, Object> message = requestBody.getMessage();
        Gson gson = new Gson();
        String jsonString = gson.toJson(message);
        try {
            ProducerRecord<String, Object> messageMap = new ProducerRecord<>(topic, jsonString);
            kafkaProducer.send(messageMap);
            LOGGER.error("Message sent");
            return new ResponseEntity(Map.of("response", "Message Sent"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("GOT EXCEPTION IN PRODUCER e = "+e.getStackTrace());
//            kafkaProducer.close();
            return new ResponseEntity(Map.of("response", "Message Not Sent"), HttpStatus.GATEWAY_TIMEOUT);
        }

    }
}

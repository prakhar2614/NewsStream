//package com.personal.newsStream.controller.kafka;
//
//import com.personal.newsStream.helper.Helper;
//import com.personal.newsStream.kafka.Consumer;
//import com.personal.newsStream.kafka.Producer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("news-stream/kafka/")
//public class KafkaController {
//
//    private final Producer producer;
//
//    public KafkaController(Producer producer) {
//        this.producer = producer;
//    }
//
//    @PostMapping("producer/{topic}")
//    public ResponseEntity<Map<String, Object>> kafkaProducer(@PathVariable String topic, @RequestBody(required = true) Object message) throws Exception {
//        if (!Helper.isProducerMessageValid(message)) {
//            throw new RuntimeException("Invalid message signal, Expected message map with key 'message'");
//        }
//        return new ResponseEntity("Message sent", HttpStatus.OK);
//    }
//
//    @PostMapping("consumer/start")
//    public ResponseEntity<Map<String, Object>> kafkaConsumerStart(@RequestBody(required = true) Map<String, Object> info){
//        String topic = (String)info.get("topic");
//        String group = (String) info.get("group");
//        String name = (String) info.get("name");
//        new Consumer(name, topic, group);
//        return new ResponseEntity (Map.of("result", "Consumer started with topic = "+topic + " & group = "+group), HttpStatus.OK);
//    }
//
//    @PostMapping("consumer/stop")
//    public ResponseEntity<Map<String, Object>> kafkaConsumerStop(@RequestBody(required = true) Map<String, Object> info){
//        String topic = (String)info.get("topic");
//        String group = (String) info.get("group");
//        String name = (String) info.get("name");
//        System.out.println("info = "+info);
//        new Consumer(name, topic, group);
//        return new ResponseEntity (Map.of("result", "Consumer started with topic = "+topic + " & group = "+group), HttpStatus.OK);
//    }
//
//    // list of topic with partition & retentionn period
//    // list of group + topic
//    // consumer status
//    // consumer stop
//
//}

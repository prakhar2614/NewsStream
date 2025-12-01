package com.personal.newsStream;

import com.personal.newsStream.kafka.Consumer;
import com.personal.newsStream.kafka.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsStreamApplication.class, args);
//        Producer producer = new Producer();
//        Consumer consumer = new Consumer();
    }

}

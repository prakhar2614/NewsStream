package com.personal.newsStream.entity.kafka;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class KafkaConsumer {

    private String name;

    private Map<String, String> topicGroupMap;

    private String status;
}

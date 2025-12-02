package com.personal.newsStream.entity.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaConsumer {

    private String id;

    private String name;

    private Map<String, List<String>> groupTopicMap;

    private String status;
}

package com.personal.newsStream.definition;

import com.personal.newsStream.constants.kafka.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaConsumerCreateEntity {

    private String name;
    private String status = Status.PUBLISHED.name();
    private Map<String, List<String>> groupTopicMap;
}

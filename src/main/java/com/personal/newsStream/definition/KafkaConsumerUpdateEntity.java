package com.personal.newsStream.definition;

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
public class KafkaConsumerUpdateEntity {

    private String id;
    private String name;
    private String status;
    private Map<String, List<String>> groupTopicMap;
}

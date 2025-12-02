package com.personal.newsStream.definition;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * prakhar: 02/12/25
 *
 * @since 16
 */
@Getter
@Setter
public class KafkaMessageRequestBody {

    private String topicName;
    private Map<String, Object> message;
}

package com.personal.newsStream.definition;

import com.personal.newsStream.constants.kafka.Status;
import lombok.Data;

/**
 * prakhar: 30/11/25
 *
 * @since 16
 */
@Data
public class KafkaTopicCreateEntry {

    private String name;

    private String status = Status.PUBLISHED.name();

    private Long retentionPeriod;

    private int partition;

//    private


}

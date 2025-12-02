package com.personal.newsStream.definition;

import com.personal.newsStream.constants.kafka.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */

@Getter
@Setter
public class KafkaGroupCreateEntry {

    String name;
    private String status = Status.PUBLISHED.name();

}

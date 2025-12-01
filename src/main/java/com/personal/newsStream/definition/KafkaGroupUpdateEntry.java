package com.personal.newsStream.definition;

import lombok.Getter;
import lombok.Setter;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */

@Getter
@Setter
public class KafkaGroupUpdateEntry {

    String id;
    String name;
    String status;
}

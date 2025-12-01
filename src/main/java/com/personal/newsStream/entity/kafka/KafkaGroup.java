package com.personal.newsStream.entity.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaGroup {
    private String id;

    private String name;

    private String status;

}

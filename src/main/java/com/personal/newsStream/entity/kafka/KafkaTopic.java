package com.personal.newsStream.entity.kafka;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.MongoId;
//import jakarta.persistence.*;


/**
 * prakhar: 29/11/25
 *
 * @since 16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class KafkaTopic {

    private String id;

    private String name;

    private Integer partition;

    private Long retentionPeriod;

    private String status;
}

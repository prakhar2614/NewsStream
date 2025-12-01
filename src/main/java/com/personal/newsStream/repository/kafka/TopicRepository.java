package com.personal.newsStream.repository.kafka;

import com.personal.newsStream.entity.kafka.KafkaTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * prakhar: 30/11/25
 *
 * @since 16
 */

@Repository
public class TopicRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public KafkaTopic save(KafkaTopic topic){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>?????");
        return mongoTemplate.insert(topic);
    }
}

package com.personal.newsStream.repository.kafka;

import com.personal.newsStream.entity.kafka.KafkaTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * prakhar: 30/11/25
 *
 * @since 16
 */

@Repository
public class KafkaTopicRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public KafkaTopic save(KafkaTopic topic){
        return mongoTemplate.save(topic);
    }

    public KafkaTopic findById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, KafkaTopic.class);
    }

    public KafkaTopic findByName(String topicName){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(topicName));
        return mongoTemplate.findOne(query, KafkaTopic.class);
    }
}

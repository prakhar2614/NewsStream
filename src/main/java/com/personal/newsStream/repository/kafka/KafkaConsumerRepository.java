package com.personal.newsStream.repository.kafka;

import com.personal.newsStream.entity.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */

@Repository
public class KafkaConsumerRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public KafkaConsumer save(KafkaConsumer kafkaConsumer){
        return mongoTemplate.save(kafkaConsumer);
    }

    public KafkaConsumer findById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, KafkaConsumer.class);
    }

    public KafkaConsumer findByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, KafkaConsumer.class);
    }
}

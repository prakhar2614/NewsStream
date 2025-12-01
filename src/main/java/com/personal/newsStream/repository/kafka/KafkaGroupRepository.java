package com.personal.newsStream.repository.kafka;

import com.personal.newsStream.entity.kafka.KafkaGroup;
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
public class KafkaGroupRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public KafkaGroup save(KafkaGroup kafkaGroup) {
        return mongoTemplate.save(kafkaGroup);
    }

    public KafkaGroup findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, KafkaGroup.class);
    }

    public KafkaGroup findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, KafkaGroup.class);
    }
}

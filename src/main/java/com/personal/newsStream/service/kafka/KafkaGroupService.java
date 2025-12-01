package com.personal.newsStream.service.kafka;

import com.personal.newsStream.definition.KafkaGroupCreateEntry;
import com.personal.newsStream.definition.KafkaGroupUpdateEntry;
import com.personal.newsStream.entity.kafka.KafkaGroup;
import com.personal.newsStream.repository.kafka.KafkaGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * prakhar: 01/12/25
 *
 * @since 16
 */
@Service
public class KafkaGroupService {

    @Autowired
    KafkaGroupRepository kafkaGroupRepository;

    public KafkaGroup save(KafkaGroupCreateEntry kafkaGroupCreateEntry) {
        String name = kafkaGroupCreateEntry.getName();
        String status = kafkaGroupCreateEntry.getStatus();
        KafkaGroup kafkaGroup = new KafkaGroup(null, name, status);
        return kafkaGroupRepository.save(kafkaGroup);
    }

    public ResponseEntity update(KafkaGroupUpdateEntry kafkaGroupUpdateEntry) {
        String id = kafkaGroupUpdateEntry.getId();
        String name = kafkaGroupUpdateEntry.getName();
        String status = kafkaGroupUpdateEntry.getStatus();
        KafkaGroup existingGroup = kafkaGroupRepository.findById(id);

        if (existingGroup == null) {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
        existingGroup.setName(name);
        existingGroup.setStatus(status);
        return new ResponseEntity(kafkaGroupRepository.save(existingGroup), HttpStatus.OK);
    }

    public ResponseEntity findByName(String name) {
        KafkaGroup existingGroup = kafkaGroupRepository.findByName(name);
        if (existingGroup == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(existingGroup, HttpStatus.FOUND);
    }
}

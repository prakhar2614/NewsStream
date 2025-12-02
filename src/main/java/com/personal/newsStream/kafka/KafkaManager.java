package com.personal.newsStream.kafka;

import com.personal.newsStream.entity.kafka.KafkaTopic;
import org.apache.kafka.clients.admin.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * prakhar: 02/12/25
 *
 * @since 16
 */
@Service
public class KafkaManager {

    private AdminClient admin;

    public KafkaManager() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        admin = AdminClient.create(properties);
    }

    public HttpStatus createTopic(KafkaTopic kafkaTopic) {
        String name = kafkaTopic.getName();
        int partition = kafkaTopic.getPartition();
        long retentionPeriod = kafkaTopic.getRetentionPeriod();

        NewTopic topic = new NewTopic(name, partition, (short) 1);

        Map<String, String> config = Map.of("retention.ms", String.valueOf(retentionPeriod), "cleanup.policy", "delete");
        topic.configs(config);
        admin.createTopics(List.of(topic));
        System.out.println("TOPIC CREATED");
        return HttpStatus.CREATED;
    }

    public List<String> topicList() throws ExecutionException, InterruptedException {
        return this.admin.listTopics().names().get().stream().toList();
    }

    public List<String> groupList() throws ExecutionException, InterruptedException {
        Collection<ConsumerGroupListing> groupListing = this.admin.listConsumerGroups().all().get();
        List<String> groups = new ArrayList<>();
        for (ConsumerGroupListing group : groupListing) {
            groups.add(group.groupId());
        }

        for (ConsumerGroupListing group : groupListing) {

            String groupId = group.groupId();

            DescribeConsumerGroupsResult result =
                    admin.describeConsumerGroups(Collections.singleton(groupId));

            System.out.println("Group: " + groupId);

            for (MemberDescription member :
                    result.all().get().get(groupId).members()) {

                System.out.println("  ConsumerId: " + member.consumerId());
                System.out.println("  ClientId:   " + member.clientId());
                System.out.println("  Host:       " + member.host());
                System.out.println("  Assignments:" + member.assignment().topicPartitions());
                System.out.println("-----------------------");
            }
        }
        return groups;
    }
}

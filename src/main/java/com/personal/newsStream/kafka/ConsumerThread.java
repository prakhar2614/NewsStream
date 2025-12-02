package com.personal.newsStream.kafka;

import java.util.List;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */

class ConsumerThread implements Runnable{

    private final Consumer consumer;
    private final List<String> topic;

    public ConsumerThread(Consumer consumer, List<String> topic) {
        this.consumer = consumer;
        this.topic = topic;
    }

    @Override
    public void run(){
        this.consumer.start();
    }
}

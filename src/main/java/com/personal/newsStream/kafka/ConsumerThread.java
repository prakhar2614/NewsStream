package com.personal.newsStream.kafka;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */

class ConsumerThread implements Runnable{

    private final Consumer consumer;
    private final String topic;

    public ConsumerThread(Consumer consumer, String topic){
        this.consumer = consumer;
        this.topic = topic;
    }

    @Override
    public void run(){
        this.consumer.start(this.topic);
    }
}

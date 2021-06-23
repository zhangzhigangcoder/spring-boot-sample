package org.spring.boot.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class OriginalConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();

        //bootstrap.servers kafka集群地址 host1:port1,host2:port2 ....
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // key.deserializer 消息key序列化方式
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value.deserializer 消息体序列化方式
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // group.id 消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-group516");
        // enable.auto.commit 设置自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // auto.offset.reset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        String[] topics = new String[]{"test2"};
        consumer.subscribe(Arrays.asList(topics), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                // 重新分配分区之后和消费者开始消费之前被调用
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                // 再均衡之前和消费者停止读取消息之后被调用
            }
        });
        // 手动提交
//        consumer.commitSync();
        consumer.u

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }

    }
//    FetchSessionHandler
}
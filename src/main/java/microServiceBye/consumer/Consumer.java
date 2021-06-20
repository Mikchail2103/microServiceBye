package microServiceBye.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class Consumer {
    @KafkaListener(topics = "hello")
    public KafkaConsumer<String, String> getConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "app.1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(properties);
    }

    @KafkaListener(topics = "hello")
    public int getCountMessage() {
        org.apache.kafka.clients.consumer.Consumer<String, String> consumer = getConsumer();
        TopicPartition topicPartition = new TopicPartition("hello", 0);
        List<TopicPartition> topicPartitionList = Collections.singletonList(topicPartition);
        consumer.assign(topicPartitionList);
        consumer.seekToBeginning(topicPartitionList);
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
        return records.count();
    }
}

package microServiceBye.consumer;

import org.apache.kafka.clients.consumer.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ConsumerForKafka {
    @KafkaListener(topics = "hello")
    public KafkaConsumer<String, Long> getConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "app.1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        return new KafkaConsumer<>(properties);
    }

    @KafkaListener(topics = "hello")
    public long getCountMessage() {
        Consumer<String, Long> consumer = getConsumer();

        consumer.subscribe(Collections.singletonList("hello"));

        consumer.poll(Duration.ofMillis(100));

        AtomicLong maxTimestamp = new AtomicLong();
        AtomicReference<ConsumerRecord<String, Long>> latestRecord = new AtomicReference<>();

        consumer.endOffsets(consumer.assignment()).forEach((topicPartition, offset) -> {
            System.out.println("offset: "+offset);

            consumer.seek(topicPartition, (offset==0) ? offset:offset - 1);

            consumer.poll(Duration.ofMillis(100)).forEach(record -> {

                if (record.timestamp() > maxTimestamp.get()) {
                    maxTimestamp.set(record.timestamp());
                    latestRecord.set(record);
                }
            });
        });
        consumer.close();
        ConsumerRecord<String, Long> record = latestRecord.get();
        return record.value();
    }
}

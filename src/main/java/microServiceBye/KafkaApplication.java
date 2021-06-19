package microServiceBye;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class KafkaApplication {

    @KafkaListener(topics = {"hello"})
    public void consume(ConsumerRecord<String, String> record, String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}

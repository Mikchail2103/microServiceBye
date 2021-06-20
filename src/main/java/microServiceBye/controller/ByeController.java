package microServiceBye.controller;

import microServiceBye.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bye")
@EnableKafka
public class ByeController {
    private KafkaTemplate<String, String> kafkaTemplate;
    private Consumer consumer;

    @Autowired
    public void set(KafkaTemplate<String, String> kafkaTemplate, Consumer consumer){
        this.consumer = consumer;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    @GetMapping
    public String sayBye(){
        String msg = "Всего доброго!Вы здоровались " + consumer.getCountMessage() + " раз";
        kafkaTemplate.send("bye", msg);
        return msg;
    }
}

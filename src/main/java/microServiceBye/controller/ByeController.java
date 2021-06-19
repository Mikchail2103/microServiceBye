package microServiceBye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bye")
public class ByeController {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public void set(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    public void sayBye(){
        String msg = "Всего доброго!";
        System.out.println(msg);
        kafkaTemplate.send("hello", msg);
    }
}

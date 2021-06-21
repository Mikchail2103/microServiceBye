package microServiceBye.controller;

import microServiceBye.consumer.ConsumerForKafka;
import microServiceBye.model.ByeEntity;
import microServiceBye.service.ByeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bye")
@EnableKafka
public class ByeController {
    private ByeEntityService byeEntityService;
    private ConsumerForKafka consumerForKafka;

    @Autowired
    public void set(ByeEntityService byeEntityService,
                    ConsumerForKafka consumerForKafka){
        this.byeEntityService = byeEntityService;
        this.consumerForKafka = consumerForKafka;
    }
    
    @GetMapping
    public String sayBye(){
        String message = "Всего доброго!Вы здоровались "  + consumerForKafka.getCountMessage() + " раз";
        ByeEntity bye = byeEntityService.getByIdName("bye");
        bye.setCount(bye.getCount() + 1L);
        byeEntityService.saveBye(bye);
        return message;
    }
}

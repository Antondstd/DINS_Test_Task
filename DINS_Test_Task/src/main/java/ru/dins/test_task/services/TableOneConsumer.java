package ru.dins.test_task.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.stereotype.Component;
import ru.dins.test_task.models.TableOne;
import ru.dins.test_task.models.TableTwo;

@Component
public class TableOneConsumer {

    @Autowired
    TableTwoService tableTwoService;


    @Value("${kafka.customForTableOne.id}")
    String kafkaListenerTableOneID;

    @Bean
    String getKafkaListenerTableOneID(){
        return kafkaListenerTableOneID;
    }

    @Value("${kafka.customForTableOne.topic}")
    String customTableOnetopic;


    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Autowired
    private ApplicationContext context;

    private static final Logger logger =
            LoggerFactory.getLogger(TableOneConsumer.class);

    @KafkaListener(id = "${kafka.customForTableOne.id}",
            topics = "${kafka.customForTableOne.topic}",
            containerFactory = "kafkaTableOneListenerContainerFactory",
            autoStartup = "false")
    void processMessage(TableOne tableOneEntity) {
        logger.info("Got tableOne entity from kafka: " + tableOneEntity.getName() + " | " + tableOneEntity.getTimeStamp().toString());
        TableTwo tableTwoEntity = new TableTwo(tableOneEntity);
        tableTwoService.save(tableTwoEntity);
    }

    @EventListener(condition = "event.listenerId.startsWith(@getKafkaListenerTableOneID)")
    public void eventHandler(ListenerContainerIdleEvent event) {
        logger.info("The Kafka listener " + kafkaListenerTableOneID + " is idle. Turning it off");
        registry.getListenerContainer(kafkaListenerTableOneID).stop();
    }
}

package ru.dins.test_task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;
import ru.dins.test_task.Models.TableOne;
import ru.dins.test_task.services.TableOneService;

import java.util.List;
import java.util.Objects;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger logger =
            LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    public TableOneService tableOneService;

    @Value("${mode}")
    String mode;

    @Value("${kafka.customForTableOne.id}")
    String kafkaListenerTableOneID;

    @Autowired
    private KafkaListenerEndpointRegistry registry;


    @Override
    public void run(ApplicationArguments args) {
        logger.info("The mode is " + mode);
        if (Objects.equals(mode, "spamKafka")) {
            this.tableOneService.checkAndGenerateRows();
            List<TableOne> tableOneList = tableOneService.getAllElements();
            for (TableOne element : tableOneList) {
                tableOneService.sendToKafka(element);
            }
        } else if (Objects.equals(mode, "readKafka")) {
            logger.info("Turning on Kafka");
            registry.getListenerContainer(kafkaListenerTableOneID).start();
        }

    }


}

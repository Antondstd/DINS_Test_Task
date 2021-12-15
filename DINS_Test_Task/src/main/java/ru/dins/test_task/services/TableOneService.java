package ru.dins.test_task.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.dins.test_task.Models.TableOne;
import ru.dins.test_task.Repositories.TableOneRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableOneService {
    @Autowired
    private TableOneRepository tableOneRepository;

    private Logger logger = LoggerFactory.getLogger(TableOneService.class);

    @Autowired
    private KafkaTemplate<String, TableOne> kafkaTemplate;

    public void checkAndGenerateRows(){
        logger.info("Checking Rows");
        long amountOfRows = tableOneRepository.count();
        long rowsLeft =  1000 - amountOfRows;
        logger.info("Need to generate " + rowsLeft + " rows");
        if (rowsLeft > 0){
            ArrayList<TableOne> listTableOne = new ArrayList<TableOne>();
            for (int i = (int) (1000 - rowsLeft); i < 1000; i ++)
            listTableOne.add(new TableOne("entity_" + i));
            tableOneRepository.saveAll(listTableOne);
        }
    }

    @Value("${kafka.customForTableOne.topic}")
    String customTableOnetopic;

    public void sendToKafka(TableOne tableOne){
        logger.info("Sending to Kafka TableOneTopic entity with name: " + tableOne.getName());
        kafkaTemplate.send(customTableOnetopic,tableOne);
    }

    public List<TableOne> getAllElements(){
        return tableOneRepository.findAll();
    }
}

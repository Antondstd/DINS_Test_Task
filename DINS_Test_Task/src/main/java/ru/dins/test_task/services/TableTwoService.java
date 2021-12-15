package ru.dins.test_task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dins.test_task.models.TableTwo;
import ru.dins.test_task.repositories.TableTwoRepository;

@Service
public class TableTwoService {
    @Autowired
    TableTwoRepository tableTwoRepository;

    TableTwo save(TableTwo tableTwo){
        return tableTwoRepository.save(tableTwo);
    }
}

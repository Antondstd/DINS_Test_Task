package ru.dins.test_task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ru.dins.test_task.services.TableOneService;

@SpringBootApplication
public class DinsTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DinsTestTaskApplication.class, args);
    }
}
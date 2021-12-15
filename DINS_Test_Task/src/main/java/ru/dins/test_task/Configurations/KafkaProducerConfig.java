package ru.dins.test_task.configurations;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.dins.test_task.models.TableOne;

import java.util.HashMap;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap_server_config}")
    String kafkaBootstrapServerConfig;

    @Bean
    public ProducerFactory<String,TableOne> producerFactory(){
        HashMap<String,Object> configProps = new HashMap();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaBootstrapServerConfig);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, TableOne>(configProps);
    }

    @Bean
    public KafkaTemplate<String, TableOne> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}

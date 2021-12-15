package ru.dins.test_task.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import ru.dins.test_task.models.TableOne;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap_server_config}")
    String kafkaBootstrapServerConfig;

    @Value("${kafka.customForTableOne.id}")
    String kafkaListenerTableOneID;

    @Bean
    public ConsumerFactory<String, TableOne> tableOneConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaBootstrapServerConfig);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                kafkaListenerTableOneID);
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),
                new JsonDeserializer<TableOne>(TableOne.class));
    }

    @Value("${kafka.listener.idletime}")
    Long kafkaIdleTime;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TableOne>
    kafkaTableOneListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, TableOne> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(tableOneConsumerFactory());
        factory.getContainerProperties().setIdleEventInterval(kafkaIdleTime);
        return factory;
    }

}

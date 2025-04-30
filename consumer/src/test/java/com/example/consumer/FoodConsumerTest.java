package com.example.consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import com.example.consumer.entity.FoodOrderEntity;
import com.example.consumer.model.FoodOrderDto;
import com.example.consumer.repository.FoodOrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

@DisplayName("Тесты для получения сообщения из кафки")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(value = {TestConfig.class})
class FoodConsumerTest extends KafkaContainer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private FoodOrderRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Тестирование получения сообщения из кафки")
    @Test
    void shouldConsumeMessageFromKafka() throws JsonProcessingException {
        var beforeList = repository.findAll();
        assertThat(beforeList).isEmpty();

        var foodOrderDto = new FoodOrderDto("Молоко", 1.0);

        var message = objectMapper.writeValueAsString(foodOrderDto);

        kafkaTemplate.send("t.food.order", message);

        await()
            .pollInterval(Duration.ofSeconds(3))
            .atMost(10, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                List<FoodOrderEntity> list = repository.findAll();
                assertThat(list).isNotEmpty();
                assertThat(list).hasSize(1);
            });
    }
}

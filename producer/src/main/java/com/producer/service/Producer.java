package com.producer.service;

import com.producer.model.FoodOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

  @Value("${topic.name}")
  private String orderTopic;

  private final KafkaTemplate<String, FoodOrderDto> kafkaTemplate;

  @Retryable(
      retryFor = {Exception.class},
      backoff = @Backoff(delay = 1000, multiplier = 2)
  )
  public void sendMessage(String key, FoodOrderDto foodOrderDto) {
    // Отправка сообщения в кафку
    var future = kafkaTemplate.send(orderTopic, key, foodOrderDto);
    // Обработка результата отправки
    future.whenComplete((result, throwable) -> {
      if (throwable != null) {
        log.error("Возникла ошибка при отправке в кафку {}", throwable.getMessage());
      } else {
        log.info("Успешная отправка сообщения {} с ключом {}", foodOrderDto, key);
      }
    });
  }

}

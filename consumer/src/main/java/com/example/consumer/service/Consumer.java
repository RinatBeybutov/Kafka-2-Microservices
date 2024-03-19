package com.example.consumer.service;

import com.example.consumer.model.FoodOrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

  private static final String orderTopic = "${topic.name}";
  private final ObjectMapper objectMapper;
  private final FoodOrderService foodOrderService;

  @KafkaListener(topics = orderTopic)
  public void consumeMessage(String message) {
    log.info("message consumed {}", message);
    try {
      FoodOrderDto foodOrder = objectMapper.readValue(message, FoodOrderDto.class);
      foodOrderService.persistFoodOrder(foodOrder);
    } catch (JsonProcessingException e) {
      log.error("Error parsing message {}", message);
    }

  }
}

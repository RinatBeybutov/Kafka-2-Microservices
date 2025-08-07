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
public class FoodConsumer {

  private static final String orderTopic = "${topic.name}";

  private final FoodOrderService foodOrderService;

  @KafkaListener(topics = "#{orderTopic}")
  public void consumeMessage(FoodOrderDto foodOrder) {
    log.info("message consumed {}", foodOrder);
    foodOrderService.saveFoodOrder(foodOrder);
  }
}

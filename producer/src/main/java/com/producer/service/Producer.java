package com.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.model.FoodOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class Producer {

  @Value("${topic.name}")
  private String orderTopic;

  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public String sendMessage(FoodOrder foodOrder) {
    try {
      String orderAsMessage = objectMapper.writeValueAsString(foodOrder);
      kafkaTemplate.send(orderTopic, orderAsMessage);
      log.info("send order {}", orderAsMessage);
    } catch (JsonProcessingException e) {
      return "error parsing food order";
    }
    return "sent!";
  }

}

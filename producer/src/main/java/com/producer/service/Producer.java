package com.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.model.FoodOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

  @Value("${topic.name}")
  private String orderTopic;

  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public String sendMessage(FoodOrderDto foodOrderDto) {
    try {
      // Преобразование ДТО в строку для отправки
      String orderAsMessage = objectMapper.writeValueAsString(foodOrderDto);
      // Отправка сообщения в кафку
      kafkaTemplate.send(orderTopic, orderAsMessage);
      log.info("send order {}", orderAsMessage);
    } catch (JsonProcessingException e) {
      return "error parsing food order";
    }
    return "sent!";
  }

}

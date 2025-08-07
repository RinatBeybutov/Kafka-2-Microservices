package com.producer.service;

import com.producer.model.FoodOrderDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodOrderService {

  private final Producer producer;

  public void createFoodOrder(FoodOrderDto foodOrderDto) {
    String key = UUID.randomUUID().toString();
    sendMessageWithErrorHandling(foodOrderDto, key);
  }

  private void sendMessageWithErrorHandling(FoodOrderDto foodOrderDto, String key) {
    try {
      producer.sendMessage(key, foodOrderDto);
    } catch (Exception e) {
      log.error("Error sending message '{}': {}", foodOrderDto, e.getMessage());
    }
  }
}

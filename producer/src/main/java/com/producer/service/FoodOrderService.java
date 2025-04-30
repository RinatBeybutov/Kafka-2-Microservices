package com.producer.service;

import com.producer.model.FoodOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodOrderService {

  private final Producer producer;

  public void createFoodOrder(FoodOrderDto foodOrderDto) {
    producer.sendMessage(foodOrderDto);
  }
}

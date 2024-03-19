package com.producer.service;

import com.producer.model.FoodOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodOrderImpl implements FoodOrderService{

  private final Producer producer;

  @Override
  public String createFoodOrder(FoodOrder foodOrder) {
    return producer.sendMessage(foodOrder);
  }
}

package com.example.consumer.service;

import com.example.consumer.model.FoodOrderDto;

public interface FoodOrderService {

  void persistFoodOrder(FoodOrderDto foodOrder);
}

package com.producer.controller;

import com.producer.model.FoodOrderDto;
import com.producer.service.FoodOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class FoodController {

  private final FoodOrderService foodOrderService;

  @PostMapping
  public String createFoodOrder(@RequestBody FoodOrderDto foodOrderDto) {
    log.info("create food order request received");
    return foodOrderService.createFoodOrder(foodOrderDto);
  }
}

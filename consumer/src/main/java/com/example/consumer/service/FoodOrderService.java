package com.example.consumer.service;

import com.example.consumer.entity.FoodOrderEntity;
import com.example.consumer.model.FoodOrderDto;
import com.example.consumer.repository.FoodOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodOrderService {

  private final FoodOrderRepository foodOrderRepository;
  private final ModelMapper modelMapper;

  @Transactional
  public void saveFoodOrder(FoodOrderDto foodOrder) {
    var foodOrderEntity = modelMapper.map(foodOrder, FoodOrderEntity.class);
    var saved = foodOrderRepository.save(foodOrderEntity);
    log.info("saved order {}", saved);
  }
}

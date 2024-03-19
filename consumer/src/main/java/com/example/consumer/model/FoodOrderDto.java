package com.example.consumer.model;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class FoodOrderDto {
  String item;
  Double amount;
}

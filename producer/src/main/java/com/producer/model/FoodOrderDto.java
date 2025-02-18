package com.producer.model;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class FoodOrderDto {
  String item;
  Double amount;
}

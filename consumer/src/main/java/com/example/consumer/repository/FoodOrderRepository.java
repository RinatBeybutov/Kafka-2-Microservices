package com.example.consumer.repository;

import com.example.consumer.entity.FoodOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrderEntity, Long> {

}

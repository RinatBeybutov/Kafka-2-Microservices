package com.example.consumer;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class KafkaContainer {

  @Container
  static final org.testcontainers.containers.KafkaContainer kafka = new org.testcontainers.containers.KafkaContainer(
      DockerImageName.parse("confluentinc/cp-kafka:7.6.1")
  );

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    registry.add("spring.kafka.consumer.auto-offset-reset", ()->"earliest");
  }
}

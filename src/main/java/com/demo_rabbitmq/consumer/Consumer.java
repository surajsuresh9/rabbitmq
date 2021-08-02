package com.demo_rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.demo_rabbitmq.config.RabbitMQConfig;
import com.demo_rabbitmq.dto.OrderStatus;

@Configuration
public class Consumer {

	@RabbitListener(queues = RabbitMQConfig.QUEUE)
	public void consumeMessage(OrderStatus orderStatus) {
		System.out.println("Message received from queue: "+orderStatus);
		
	}
	
}

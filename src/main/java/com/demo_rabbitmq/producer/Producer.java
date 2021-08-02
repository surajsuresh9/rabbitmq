package com.demo_rabbitmq.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo_rabbitmq.config.RabbitMQConfig;
import com.demo_rabbitmq.dto.Order;
import com.demo_rabbitmq.dto.OrderStatus;

@RestController
@RequestMapping("/api/order")
public class Producer {

	@Autowired
	private RabbitTemplate template;

	@PostMapping("/{restaurantName}")
	public String publishOrder(@RequestBody Order order, @PathVariable("restaurantName") String restuarantName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus status = new OrderStatus(order, "PROCESSING", "Order placed successfully at " + restuarantName);
		template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, status);
		return "Success";
	}

}

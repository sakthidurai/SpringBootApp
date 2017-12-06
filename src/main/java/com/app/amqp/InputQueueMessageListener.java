package com.app.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InputQueueMessageListener {
	
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value("${spring.app.amqp.output.exchange}")
	private String outputExchangeName;
	
	@Value("${spring.app.amqp.output.routingKey}")
	private String outputRoutingKey;

	
	public void receiveMessage(String message) {
		System.err.println("The value from the queue is "+message);
		rabbitTemplate.convertAndSend(outputExchangeName,outputRoutingKey,message);
	}

}

package com.app.controller;

import java.util.Arrays;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.vo.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AmqpMessageController {
	
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value("${spring.app.amqp.output.exchange}")
	private String outputExchangeName;
	
	@Value("${spring.app.amqp.output.queue}")
	private String outputQueueName;
	
	@Value("${spring.app.amqp.output.routingKey}")
	private String outputRoutingKey;	
	
	@Value("${spring.app.amqp.input.exchange}")
	private String inputExchangeName;
	
	@Value("${spring.app.amqp.input.queue}")
	private String inputQueueName;
	
	@Value("${spring.app.amqp.input.routingKey}")
	private String inputRoutingKey;
	
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(value = "/postMessage", method= RequestMethod.POST, consumes ="application/JSON")
	public void postMessage(@RequestBody Employee employee) throws Exception{
		
		String employeeString = mapper.writeValueAsString(employee);
		rabbitTemplate.convertAndSend(inputExchangeName,inputRoutingKey,employeeString);
	
	}
	@RequestMapping(value = "/receiveMessage", method= RequestMethod.GET, produces ="application/JSON")
	public String receiveMessage() throws Exception{
		
		Message receivedMessage = rabbitTemplate.receive(outputQueueName);
		return  receivedMessage != null ? new String(receivedMessage.getBody()) : null;
		//return new String(receivedMessage.getBody()) ;
	}	

}

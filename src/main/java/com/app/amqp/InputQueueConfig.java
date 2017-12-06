package com.app.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class InputQueueConfig {
	
	@Value("${spring.app.amqp.input.queue}")
	private String inputQueueName;
	
	@Value("${spring.app.amqp.input.exchange}")
	private String inputExchangeName;
	
	@Value("${spring.app.amqp.input.routingKey}")
	private String inputRoutingKey;	
	

	@Bean
	private Queue inputQueue() {
		return new Queue(inputQueueName,false);
	}
	
	@Bean
	private DirectExchange inputExchange() {
		return new DirectExchange("default-exchange");
	}
	
	@Bean
	private Binding bindInputQueue(@Qualifier("inputQueue") Queue inputQueueObj,@Qualifier("inputExchange") DirectExchange inputExchangeObj) {
		return BindingBuilder.bind(inputQueueObj).to(inputExchangeObj).with(inputRoutingKey);
	}
	
	
	@Bean
	private MessageListenerAdapter messageListener(InputQueueMessageListener messageListener) {
		return new MessageListenerAdapter(messageListener,"receiveMessage");
	}	
	
	
	@Bean
	private SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			 MessageListenerAdapter messageListener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(inputQueueName);
		container.setMessageListener(messageListener);
		return container;
	}	
}

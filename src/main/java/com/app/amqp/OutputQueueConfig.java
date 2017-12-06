package com.app.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class OutputQueueConfig {
	
	@Value("${spring.app.amqp.output.queue}")
	private String outputQueueName;
	
	@Value("${spring.app.amqp.output.exchange}")
	private String outputExchangeName;
	
	@Value("${spring.app.amqp.output.routingKey}")
	private String outputRoutingKey;	
	

	@Bean
	private Queue outputQueue() {
		return new Queue(outputQueueName,false);
	}
	
	@Bean
	private DirectExchange outputExchange() {
		return new DirectExchange(outputExchangeName);
	}
	
	@Bean
	private Binding bindOutputQueue(@Qualifier("outputQueue")Queue outputQueueObj,@Qualifier("outputExchange")DirectExchange outputExchangeObj) {
		return BindingBuilder.bind(outputQueueObj).to(outputExchangeObj).with(outputRoutingKey);
	}
	
}

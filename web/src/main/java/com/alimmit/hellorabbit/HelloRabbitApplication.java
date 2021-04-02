package com.alimmit.hellorabbit;

import com.alimmit.hellorabbit.common.Constants;
import com.alimmit.hellorabbit.common.RoutingKeyFactory;
import com.alimmit.hellorabbit.common.message.ComplexMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@SpringBootApplication
public class HelloRabbitApplication {

	private static final Logger logger = LoggerFactory.getLogger(HelloRabbitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloRabbitApplication.class, args);
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	@Qualifier("simpleRoutingKeyFactory")
	private RoutingKeyFactory simpleMessageRoutingKeyFactory;

	@Autowired
	@Qualifier("complexRoutingKeyFactory")
	private RoutingKeyFactory complexMessageRoutingKeyFactory;

	@PostMapping("/simple")
	public void simple(@RequestBody MessageRequest messageRequest) {
		logger.info("Send message {}", messageRequest.message);
		rabbitTemplate.convertAndSend(Constants.SIMPLE_EXCHANGE_NAME, simpleMessageRoutingKeyFactory.generate(), messageRequest.message);
	}

	@PostMapping("/complex")
	public void complex(@RequestBody MessageRequest messageRequest) {
		final ComplexMessage complexMessage = new ComplexMessage(messageRequest.message, new Date(), UUID.randomUUID());
		logger.info("Send message {}", complexMessage);
		rabbitTemplate.convertAndSend(Constants.COMPLEX_EXCHANGE_NAME, complexMessageRoutingKeyFactory.generate(), complexMessage);
	}

	public static final class MessageRequest {

		private final String message;

		@JsonCreator
		public MessageRequest(@JsonProperty("message") final String message) {
			this.message = message;
		}
	}
}

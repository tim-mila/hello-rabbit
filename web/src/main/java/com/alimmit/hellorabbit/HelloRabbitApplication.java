package com.alimmit.hellorabbit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloRabbitApplication {

	private static final Logger logger = LoggerFactory.getLogger(HelloRabbitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloRabbitApplication.class, args);
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping("/test")
	public void test(@RequestBody MessageRequest messageRequest) {
		logger.info("Send message {}", messageRequest.message);
		rabbitTemplate.convertAndSend("rabbit.test.exchange", "foo.bar.baz", messageRequest.message);
	}

	public static final class MessageRequest {

		private final String message;

		@JsonCreator
		public MessageRequest(@JsonProperty("message") final String message) {
			this.message = message;
		}
	}
}

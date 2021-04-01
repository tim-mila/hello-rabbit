package com.alimmit.hellorabbit;

import com.alimmit.hellorabbit.common.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

    @Bean
    Queue queue() {
        return new Queue(Constants.SIMPLE_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(Constants.SIMPLE_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(final Queue queue, final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constants.SIMPLE_ROUTING_KEY);
    }
}

package com.alimmit.hellorabbit;

import com.alimmit.hellorabbit.common.Constants;
import com.alimmit.hellorabbit.common.RoutingKeyFactory;
import com.alimmit.hellorabbit.common.SimpleRoutingKeyFactoryImpl;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

    @Bean("simpleExchange")
    TopicExchange exchange() {
        return new TopicExchange(Constants.SIMPLE_EXCHANGE_NAME);
    }

    @Bean("complexExchange")
    TopicExchange complexExchange() {
        return new TopicExchange(Constants.COMPLEX_EXCHANGE_NAME);
    }

    @Bean("simpleQueue")
    Queue queue() {
        return new Queue(Constants.SIMPLE_MESSAGE_QUEUE_NAME, false);
    }

    @Bean("simpleBinding")
    Binding binding(@Qualifier("simpleQueue") final Queue queue, @Qualifier("simpleExchange") final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constants.SIMPLE_MESSAGE_ROUTING_KEY);
    }

    @Bean("simpleRoutingKeyFactory")
    RoutingKeyFactory simpleRoutingKeyFactory() {
        return new SimpleRoutingKeyFactoryImpl("foo.bar");
    }

    @Bean("complexQueue")
    Queue complexQueue() {
        return new Queue(Constants.COMPLEX_MESSAGE_QUEUE_NAME, false);
    }

    @Bean("complexBinding")
    Binding complexBinding(@Qualifier("complexQueue") final Queue queue, @Qualifier("complexExchange") final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constants.COMPLEX_MESSAGE_ROUTING_KEY);
    }

    @Bean("complexRoutingKeyFactory")
    RoutingKeyFactory complexRoutingKeyFactory() {
        return new SimpleRoutingKeyFactoryImpl("complex.message");
    }
}

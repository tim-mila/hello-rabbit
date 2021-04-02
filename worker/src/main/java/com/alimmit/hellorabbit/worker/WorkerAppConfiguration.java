package com.alimmit.hellorabbit.worker;

import com.alimmit.hellorabbit.common.Constants;
import com.alimmit.hellorabbit.common.SimpleMessageReceiver;
import com.alimmit.hellorabbit.common.message.ComplexMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerAppConfiguration {

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

    @Bean("simpleMessageListenerAdapter")
    MessageListenerAdapter simpleMessageListenerAdapter(@Qualifier("simpleMessageReceiver") SimpleMessageReceiver<String> receiver) {
        return new MessageListenerAdapter(receiver, Constants.DEFAULT_LISTENER_METHOD);
    }

    @Bean("simpleMessageListenerContainer")
    SimpleMessageListenerContainer simpleMessageListenerContainer(
            final ConnectionFactory connectionFactory,
            @Qualifier("simpleQueue") final Queue queue,
            @Qualifier("simpleMessageListenerAdapter") final MessageListenerAdapter listenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(listenerAdapter);
        container.addQueues(queue);
        return container;
    }

    @Bean("complexQueue")
    Queue simpleObjectQueue() {
        return new Queue(Constants.COMPLEX_MESSAGE_QUEUE_NAME, false);
    }

    @Bean("complexBinding")
    Binding bindingSimpleObject(@Qualifier("complexQueue") final Queue queue, @Qualifier("complexExchange") final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constants.COMPLEX_MESSAGE_ROUTING_KEY);
    }

    @Bean("complexMessageListenerAdapter")
    MessageListenerAdapter complexMessageListenerAdapter(@Qualifier("complexMessageReceiver") SimpleMessageReceiver<ComplexMessage> receiver) {
        return new MessageListenerAdapter(receiver, Constants.DEFAULT_LISTENER_METHOD);
    }

    @Bean("complexMessageListenerContainer")
    SimpleMessageListenerContainer complexMessageListenerContainer(
            final ConnectionFactory connectionFactory,
            @Qualifier("complexQueue") final Queue queue,
            @Qualifier("complexMessageListenerAdapter") final MessageListenerAdapter listenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(Constants.COMPLEX_MESSAGE_QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        container.addQueues(queue);
        return container;
    }
}

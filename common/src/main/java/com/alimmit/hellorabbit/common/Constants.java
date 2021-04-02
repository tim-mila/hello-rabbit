package com.alimmit.hellorabbit.common;

public interface Constants {

    String SIMPLE_EXCHANGE_NAME = "rabbit.test.exchange";
    String COMPLEX_EXCHANGE_NAME = "complex.test.exchange";

    String SIMPLE_MESSAGE_QUEUE_NAME = "rabbit-test";
    String SIMPLE_MESSAGE_ROUTING_KEY = "foo.bar.#";

    String COMPLEX_MESSAGE_QUEUE_NAME = "rabbit-object";
    String COMPLEX_MESSAGE_ROUTING_KEY = "complex.message.#";

    String DEFAULT_LISTENER_METHOD = "receiveMessage";
}

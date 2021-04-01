package com.alimmit.hellorabbit.common;

public interface Constants {

    String SIMPLE_EXCHANGE_NAME = "rabbit.test.exchange";
    String SIMPLE_QUEUE_NAME = "rabbit-test";
    String SIMPLE_ROUTING_KEY = "foo.bar.#";

    String DEFAULT_LISTENER_METHOD = "receiveMessage";
}

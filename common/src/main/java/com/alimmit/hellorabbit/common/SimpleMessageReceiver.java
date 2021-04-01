package com.alimmit.hellorabbit.common;

/**
 * Common interface to receive messages
 *
 * @param <T> Message data type
 */
public interface SimpleMessageReceiver<T> {

    /**
     * Receive message handler
     *
     * @param message Message body
     */
    void receiveMessage(T message);
}

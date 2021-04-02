package com.alimmit.hellorabbit.common;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleRoutingKeyFactoryImpl implements RoutingKeyFactory {

    private final AtomicInteger counter;
    private final String prefix;

    public SimpleRoutingKeyFactoryImpl(final String prefix) {
        this.prefix = prefix;
        counter = new AtomicInteger();
    }

    @Override
    public String generate() {
        return prefix + "." + counter.getAndIncrement();
    }
}

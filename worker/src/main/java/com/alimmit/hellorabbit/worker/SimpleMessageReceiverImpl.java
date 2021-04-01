package com.alimmit.hellorabbit.worker;

import com.alimmit.hellorabbit.common.SimpleMessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageReceiverImpl implements SimpleMessageReceiver<String> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageReceiverImpl.class);

    @Override
    public void receiveMessage(final String message) {
        logger.info("Received Message: <{}>", message);
    }
}

package com.alimmit.hellorabbit.worker;

import com.alimmit.hellorabbit.common.SimpleMessageReceiver;
import com.alimmit.hellorabbit.common.message.ComplexMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("complexMessageReceiver")
public class ComplexMessageReceiverImpl implements SimpleMessageReceiver<ComplexMessage> {

    private static final Logger logger = LoggerFactory.getLogger(ComplexMessageReceiverImpl.class);

    @Override
    public void receiveMessage(final ComplexMessage message) {
        logger.info("Received Message: <{}>", message.toString());
    }
}

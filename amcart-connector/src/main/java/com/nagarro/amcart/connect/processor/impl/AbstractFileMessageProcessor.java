package com.nagarro.amcart.connect.processor.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

import com.nagarro.amcart.connect.processor.FileMessageProcessor;

public abstract class AbstractFileMessageProcessor implements FileMessageProcessor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Message inputMessage = exchange.getIn();
        String message = inputMessage.getBody(String.class);
        processStringMessage(message);
    }

    protected abstract void processStringMessage(String message);
}

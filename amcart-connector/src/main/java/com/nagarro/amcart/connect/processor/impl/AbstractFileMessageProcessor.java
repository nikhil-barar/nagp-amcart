package com.nagarro.amcart.connect.processor.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.amcart.connect.processor.FileMessageProcessor;

public abstract class AbstractFileMessageProcessor implements FileMessageProcessor {
private static final Logger LOGGER=LoggerFactory.getLogger(AbstractFileMessageProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        Message inputMessage = exchange.getIn();
        String camelFileName = inputMessage.getHeader(Exchange.FILE_NAME, String.class);
        String message = inputMessage.getBody(String.class);
        try {
            String processedMessage = processStringMessage(message);
            exchange.getOut().setBody(processedMessage, String.class);
        } catch (Exception exception) {
            LOGGER.error("Fail to process Message",exception);
            throw exception;
        }
        exchange.setProperty(Exchange.FILE_NAME, camelFileName);
    }

    protected abstract String processStringMessage(String message) throws Exception;
}

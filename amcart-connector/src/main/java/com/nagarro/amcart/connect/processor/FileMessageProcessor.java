package com.nagarro.amcart.connect.processor;

import org.apache.camel.Processor;

import com.nagarro.amcart.connect.message.type.MessageType;

/**
 * The Interface FileMessageProcessor process Apache camel message.
 */
public interface FileMessageProcessor extends Processor {

    /**
     * Gets the message type.
     *
     * @return the message type
     */
    MessageType getMessageType();

}

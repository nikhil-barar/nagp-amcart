package com.nagarro.amcart.connect.processor.impl;

import com.nagarro.amcart.connect.util.JsonUtil;

/**
 * The Class AbstractStringMessageProcessor.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractStringMessageProcessor<T> extends AbstractFileMessageProcessor {

    /** The class of T. */
    private Class<T> classOfT;

    /**
     * Instantiates a new abstract string message processor.
     *
     * @param classOfT
     *            the class of T
     */
    public AbstractStringMessageProcessor(Class<T> classOfT) {
        this.classOfT = classOfT;
    }

    @Override
    protected void processStringMessage(String message) {
        T object = JsonUtil.fromJson(message, classOfT);
        processObject(object);
    }

    /**
     * Process object.
     *
     * @param object
     *            the object
     */
    protected abstract void processObject(T object);
}

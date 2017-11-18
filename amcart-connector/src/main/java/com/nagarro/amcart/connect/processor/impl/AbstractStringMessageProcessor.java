package com.nagarro.amcart.connect.processor.impl;

import java.util.Objects;

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
    protected String processStringMessage(String message) {
        T object = JsonUtil.fromJson(message, classOfT);
        T processedObject = processObject(object);
        return Objects.isNull(processedObject) ? message : JsonUtil.toJson(processedObject);
    }

    /**
     * Process object.
     *
     * @param object
     *            the object
     * @return the t
     */
    protected abstract T processObject(T object);
}

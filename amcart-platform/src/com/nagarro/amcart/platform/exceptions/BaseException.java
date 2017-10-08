package com.nagarro.amcart.platform.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public abstract class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseException.class);
	private static final Object SEPARATOR = "|";
	

	public BaseException(String exceptionCode, String className, String methodName, Throwable originalException) {
		StringBuilder errorBuilder=new StringBuilder();
		errorBuilder.append("EXCEPTION CODE: ").append(exceptionCode).append(SEPARATOR);
		errorBuilder.append("EXCEPTION IN CLASS: ").append(className).append(SEPARATOR);
		errorBuilder.append("EXPCEPTION IN METHOD: ").append(methodName).append(SEPARATOR);
		errorBuilder.append("ORIGINAL EXPCEPTION : ").append(originalException.getLocalizedMessage());
		LOGGER.error(errorBuilder.toString());
	}

}
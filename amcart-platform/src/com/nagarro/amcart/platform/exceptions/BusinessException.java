package com.nagarro.amcart.platform.exceptions;

public abstract class BusinessException extends BaseException {
	protected static final String TYPE = "BusinessException";
	private static final long serialVersionUID = 9223372036854775807L;
	
	public BusinessException(String exceptionCode, String className, String methodName, Throwable originalException) {
		super(exceptionCode, className, methodName, originalException);
	}

}
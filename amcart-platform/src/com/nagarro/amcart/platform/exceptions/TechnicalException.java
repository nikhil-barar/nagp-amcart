package com.nagarro.amcart.platform.exceptions;

public abstract class TechnicalException extends BaseException {
	
	protected static final String TYPE = "TechnicalException";
	private static final long serialVersionUID = 92233720368547758L;
	
	public TechnicalException(String exceptionCode, String className, String methodName,Throwable originalException) {
		super(exceptionCode, className, methodName,originalException);
	}
}
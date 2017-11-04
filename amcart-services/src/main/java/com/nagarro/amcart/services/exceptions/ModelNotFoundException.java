package com.nagarro.amcart.services.exceptions;

import com.nagarro.amcart.platform.exceptions.BusinessException;

public class ModelNotFoundException extends BusinessException {

	protected static final String TYPE = "ModelNotFoundException";
	private static final long serialVersionUID = 9223372036854771107L;
	
	public ModelNotFoundException(String className, String methodName,
			Throwable originalException) {
		super(className, methodName, originalException);
	}

}

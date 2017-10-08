package com.nagarro.amcart.daos.exceptions;

import com.nagarro.amcart.platform.exceptions.TechnicalException;


/**
 * This class extends from TechnicalRuntimeException. Data Access Component
 * throws this exception when so ever desired behavior is not achieved.
 * 
 */

public class DAOException extends TechnicalException {

    private static final long serialVersionUID = 1L;

    public DAOException(String exceptionCode, String className,
            String methodName,Throwable originalException) {
        super(exceptionCode, className, methodName,originalException);
    }


    /**
     * @return String Type of Exception
     */
    public String getType() {
        return "DAOException";
    }

}
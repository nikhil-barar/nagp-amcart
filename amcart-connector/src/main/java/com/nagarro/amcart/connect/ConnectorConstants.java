package com.nagarro.amcart.connect;

import org.apache.camel.Exchange;

/**
 * The Class ConnectorConstants store all constants for connector module.
 */
public class ConnectorConstants {

    public static final String FILE_IMPORT_URL = "%s/%s?delete=true&sortBy=file:modified";
    public static final String FILE_OUTPUT_URL = "%s/%s/%s";
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String FILE_NAME_PROPERTY = "${property." + Exchange.FILE_NAME + "}";
}

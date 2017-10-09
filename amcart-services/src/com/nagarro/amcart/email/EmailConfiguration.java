package com.nagarro.amcart.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;

public class EmailConfiguration {
	
	private static final String MY_EMAIL = "amcart.nagp@gmail.com";
	private static final String MY_PASSWORD = "Nagarro123";
	
	public static void configure(Email email){
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(MY_EMAIL, MY_PASSWORD));
		email.setSSLOnConnect(true);
	}

}

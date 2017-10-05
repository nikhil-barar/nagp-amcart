package com.nagarro.amcart.security;

import java.io.UnsupportedEncodingException;

public interface PasswordEncoder {
	
	public String encode(String in) throws UnsupportedEncodingException;

	public String decode(String in);
}

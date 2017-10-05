package com.nagarro.amcart.security;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service("md5PasswordEncoder")	
public class MD5PasswordEncoder implements PasswordEncoder {
    
    private final MessageDigest md;

    public MD5PasswordEncoder() throws SecurityException, NoSuchAlgorithmException, NoSuchProviderException {
        md = MessageDigest.getInstance("MD5", "SUN");        
    }

    @Override
    public String encode(final String in) throws UnsupportedEncodingException {
        if (in == null) {
            return null;
        }
        byte[] raw = null;
        byte[] stringBytes = null;
        stringBytes = in.getBytes("UTF8");
        synchronized (md) {
            raw = md.digest(stringBytes);
        }
        return Base64.getEncoder().encodeToString(raw);
    }

    @Override
    public String decode(final String in) {
        throw new RuntimeException("NOT SUPPORTED");
    }	
}
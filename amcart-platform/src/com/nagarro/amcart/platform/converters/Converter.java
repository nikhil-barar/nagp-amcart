package com.nagarro.amcart.platform.converters;

import com.nagarro.amcart.platform.exceptions.ConversionException;

public interface Converter<SOURCE, TARGET> {
	public TARGET convert(SOURCE paramSOURCE) throws ConversionException;
}
package com.nagarro.amcart.user.converter.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.User;
import com.nagarro.amcart.platform.converters.Converter;

import junit.framework.Assert;


public class UserConverterTest {
	
	@Autowired
	private Converter<User, UserData> userConverter;
	
	@Test
	public void testUserConverter(){
		User user = new User();
		user.setId(1L);
		user.setName("Amcart");
		UserData convertedUser = userConverter.convert(user);
		Assert.assertEquals(user.getId().toString(), convertedUser.getId());
		Assert.assertEquals(user.getName(), convertedUser.getName());
	}

}

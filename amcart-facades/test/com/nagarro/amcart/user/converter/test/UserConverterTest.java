package com.nagarro.amcart.user.converter.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.User;
import com.nagarro.amcart.platform.converters.Converter;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/facades-config.xml", "/platform-config.xml" })
public class UserConverterTest {

    @Autowired
    private Converter<User, UserData> userConverter;

    @Test(expected = IllegalArgumentException.class)
    public void testUserConvertNull() {
        userConverter.convert(null);
    }

    @Test
    public void testUserConvertEmpty() {
        User user = new User();
        UserData convertedUser = userConverter.convert(user);
        Assert.assertNull(convertedUser.getId());
        Assert.assertNull(convertedUser.getName());
    }

    @Test
    public void testUserConvertBasics() {
        User user = new User();
        user.setId(1L);
        user.setName("Amcart");
        UserData convertedUser = userConverter.convert(user);
        Assert.assertEquals(user.getId(), convertedUser.getId());
        Assert.assertEquals(user.getName(), convertedUser.getName());
    }
}
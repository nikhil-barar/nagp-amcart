package com.nagarro.amcart.facade.user.converters.populator;

import org.springframework.util.Assert;

import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.user.User;
import com.nagarro.amcart.platform.converters.Populator;

public class UserPopulator implements Populator<User, UserData> {
    @Override
    public void populate(final User source, final UserData target) {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        target.setId(source.getId());
        target.setName(source.getName());
    }
}

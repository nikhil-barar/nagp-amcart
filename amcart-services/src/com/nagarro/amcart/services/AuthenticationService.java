package com.nagarro.amcart.services;

import com.nagarro.amcart.models.user.User;

public interface AuthenticationService {

	User authenticateUser(final String userName, final char[] password);

}

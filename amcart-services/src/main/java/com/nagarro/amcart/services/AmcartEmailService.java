package com.nagarro.amcart.services;

import com.nagarro.amcart.email.AMCartEmail;

public interface AmcartEmailService {

	public boolean sendEmail(AMCartEmail email);
}

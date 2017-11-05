package com.nagarro.amcart.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.amcart.email.AMCartEmail;
import com.nagarro.amcart.models.enums.EmailTypeEnum;
import com.nagarro.amcart.services.AmcartEmailService;
import com.nagarro.amcart.services.impl.AmcartEmailServiceImpl;

public class TestEmailService {

    @Autowired
    AmcartEmailService amcartEmailService;

    @Test
    public void testEmailService() {
        AMCartEmail amcartEmail = new AMCartEmail();
        amcartEmail.setToAddress("rishabgandhar@gmail.com");
        amcartEmail.setFromAddress("mandeep.kaur.jolly@gmail.com");
        amcartEmail.setMessage("This is text message");
        amcartEmail.setType(EmailTypeEnum.ORDER_CONFIRMATION);
        Map<String, String> params = new HashMap<>();
        params.put("name", "mandeep");
        params.put("company", "nagarro");
        amcartEmail.setSubject("test Email");
        amcartEmail.setParams(params);
        amcartEmailService = new AmcartEmailServiceImpl();
        amcartEmailService.sendEmail(amcartEmail);

    }

}

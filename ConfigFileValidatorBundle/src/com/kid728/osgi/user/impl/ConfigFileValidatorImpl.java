package com.kid728.osgi.user.impl;

import com.kid728.osgi.service.user.Validator;

public class ConfigFileValidatorImpl implements Validator {

    @Override
    public boolean validate(String username, String password) throws Exception {
        System.out.println("ConfigFile验证方式");
        if ("kid".equals(username) && "kid-1412".equals(password)) {
            return true;
        }
        return false;
    }

}

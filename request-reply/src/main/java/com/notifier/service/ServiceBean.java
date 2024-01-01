package com.notifier.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;

@Component
public class ServiceBean {

    public User response(User user) {
        user.setName(user.getName().toUpperCase());
        return user;
    }

    public String hello(String name) {
        return "Hello "+name;
    }
}

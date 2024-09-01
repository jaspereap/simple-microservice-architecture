package com.jaspereap.simple.users.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jaspereap.simple.users.ui.model.request.UserDetailsRequest;
import com.jaspereap.simple.users.ui.model.response.UserRest;
import com.jaspereap.simple.users.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
    Map<String, UserRest> users;
    @Override
    public UserRest createUser(UserDetailsRequest userDetails) {
        UserRest newUser = new UserRest();
        String userId = UUID.randomUUID().toString();
        newUser.setUserId(userId);
        newUser.setEmail(userDetails.getEmail());
        newUser.setFirstName(userDetails.getFirstName());
        newUser.setLastName(userDetails.getLastName());
        if (users == null) users = new HashMap<>();
        users.put(userId, newUser);
        return newUser;
    }

}

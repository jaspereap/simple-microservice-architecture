package com.jaspereap.simple.users.userservice;

import com.jaspereap.simple.users.ui.model.request.UserDetailsRequest;
import com.jaspereap.simple.users.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequest userDetails);
}

package com.jaspereap.simple.users.userservice;

import com.jaspereap.simple.users.shared.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDetails);
}

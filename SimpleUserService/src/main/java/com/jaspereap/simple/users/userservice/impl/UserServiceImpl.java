package com.jaspereap.simple.users.userservice.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaspereap.simple.users.data.UserEntity;
import com.jaspereap.simple.users.data.UserRepository;
import com.jaspereap.simple.users.shared.UserDto;
import com.jaspereap.simple.users.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        // Setting explicit model mapping strategy
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        userEntity.setEncryptedPassword("testing");
        userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
        return returnValue;
    }

}

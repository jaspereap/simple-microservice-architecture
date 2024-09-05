package com.jaspereap.simple.users.ui.controller;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaspereap.simple.users.exceptions.UserServiceException;
import com.jaspereap.simple.users.shared.UserDto;
import com.jaspereap.simple.users.ui.model.request.CreateUserRequestModel;
import com.jaspereap.simple.users.ui.model.request.UpdateUserDetailsRequest;
import com.jaspereap.simple.users.ui.model.request.UserDetailsRequest;
import com.jaspereap.simple.users.ui.model.response.UserRest;
import com.jaspereap.simple.users.userservice.UserService;
import com.jaspereap.simple.users.userservice.impl.UserServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    Environment env;

    @GetMapping("/status/check")
    public String status() {
        return "Working." + env.getProperty("local.server.port");
    }
    
    @GetMapping(path="/{userId}", 
        produces = { 
                MediaType.APPLICATION_JSON_VALUE, 
                MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.ACCEPTED);
        } else {
            throw new UserServiceException("User not found!");
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<CreateUserRequestModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = userServiceImpl.createUser(userDto);

        CreateUserRequestModel returnValue = modelMapper.map(createdUser, CreateUserRequestModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
        
    }

    @PutMapping(
        path="/{userId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = { 
            MediaType.APPLICATION_JSON_VALUE, 
            MediaType.APPLICATION_XML_VALUE }
    )
    public UserRest updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequest updateUserDetails ) {
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(updateUserDetails.getFirstName());
        storedUserDetails.setLastName(updateUserDetails.getLastName());
        users.put(userId, storedUserDetails);
        return storedUserDetails;
    }


    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

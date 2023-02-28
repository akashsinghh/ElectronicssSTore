package com.lcea.electronic.store.ElectronicssSTore.controllers;

import com.lcea.electronic.store.ElectronicssSTore.dtos.ApiResponseMessage;
import com.lcea.electronic.store.ElectronicssSTore.dtos.UserDto;
import com.lcea.electronic.store.ElectronicssSTore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    //create
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDto1=userService.createUSer(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/userId")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") String userId,
            @RequestBody UserDto userDto)
    {   UserDto updateUserDto=userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/userId")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        ApiResponseMessage message=ApiResponseMessage
                .builder()
                .message("User is deleted Successfully").
                success(true).
                status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    //get all
    @GetMapping
    public ResponseEntity<List<UserDto>> ugetAllUser(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }
    // get single
    @GetMapping("/userId")
    public ResponseEntity<UserDto> getUser(String userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
    //get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }
    //search user
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){
        return new ResponseEntity<>(userService.searchUsers(keywords),HttpStatus.OK);
    }

}


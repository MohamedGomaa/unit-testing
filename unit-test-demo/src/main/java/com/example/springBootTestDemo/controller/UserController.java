package com.example.springBootTestDemo.controller;

import com.example.springBootTestDemo.dto.UserDTO;
import com.example.springBootTestDemo.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController (IUserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addOrUpdateUser(@RequestBody UserDTO userDTO){
        try{
            userService.saveUser(userDTO);
            return new ResponseEntity<>("User saved successfully.", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to save user : " + userDTO.getUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId){
        try{
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> ListUsers(){
        try{
            return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

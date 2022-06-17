package com.example.springBootTestDemo.service;

import com.example.springBootTestDemo.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO getUserById(Long userId);

    List<UserDTO> listAllUsers();

    void saveUser(UserDTO userDTO);
}

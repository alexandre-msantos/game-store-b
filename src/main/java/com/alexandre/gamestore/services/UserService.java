package com.alexandre.gamestore.services;

import com.alexandre.gamestore.dto.UserDTO;
import com.alexandre.gamestore.model.User;

import java.util.List;

public interface UserService {

    User insertUser(UserDTO userDto);
    List<User> findAll();
    User findById(Long id);
    User updateUser(UserDTO userDto);
    void delete(Long id);
}

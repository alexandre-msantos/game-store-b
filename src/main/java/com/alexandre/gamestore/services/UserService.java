package com.alexandre.gamestore.services;

import com.alexandre.gamestore.dto.UserDTO;
import com.alexandre.gamestore.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    User insertUser(UserDTO userDto);
    List<User> findAll();
    User findById(Long id);
    List<User> findAllUserByNameContainingIgnoreCase(@Param("name") String name);
    User updateUser(User user);
    void delete(User user);
}

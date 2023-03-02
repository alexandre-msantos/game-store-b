package com.alexandre.gamestore.controllers;

import com.alexandre.gamestore.model.User;
import com.alexandre.gamestore.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return userRepository.findById(id)
                .map(obj -> ResponseEntity.ok().body(obj))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

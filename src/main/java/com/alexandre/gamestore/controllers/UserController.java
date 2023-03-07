package com.alexandre.gamestore.controllers;

import com.alexandre.gamestore.model.User;
import com.alexandre.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    /*@GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return service.findById(id)
                .map(obj -> ResponseEntity.ok().body(obj))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }*/
}

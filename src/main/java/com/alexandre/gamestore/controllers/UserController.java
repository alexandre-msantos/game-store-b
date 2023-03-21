package com.alexandre.gamestore.controllers;

import com.alexandre.gamestore.dto.UserDTO;
import com.alexandre.gamestore.model.User;
import com.alexandre.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO userDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(service.insertUser(userDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO userDto = new UserDTO(service.findById(id));
        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping(value = "atualizar/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        UserDTO newUserDto = new UserDTO(service.updateUser(userDTO));
        return ResponseEntity.ok().body(newUserDto);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<UserDTO> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

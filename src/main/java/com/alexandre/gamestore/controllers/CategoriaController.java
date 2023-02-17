package com.alexandre.gamestore.controllers;

import com.alexandre.gamestore.model.Categoria;
import com.alexandre.gamestore.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<Categoria> insert(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.insert(categoria));
    }

}

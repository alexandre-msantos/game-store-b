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
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(categoria));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

}

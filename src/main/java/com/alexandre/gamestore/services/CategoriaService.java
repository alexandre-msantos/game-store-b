package com.alexandre.gamestore.services;

import com.alexandre.gamestore.model.Categoria;
import com.alexandre.gamestore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

   @Autowired
    private CategoriaRepository categoriaRepository;

   public List<Categoria> findAll(){
       return categoriaRepository.findAll();
   }
}

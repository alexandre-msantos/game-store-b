package com.alexandre.gamestore.services;

import com.alexandre.gamestore.model.Categoria;
import com.alexandre.gamestore.repositories.CategoriaRepository;
import com.alexandre.gamestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

   @Autowired
    private CategoriaRepository categoriaRepository;

   public List<Categoria> findAll(){
       return categoriaRepository.findAll();
   }

   public Categoria findById(Long id){
       Optional<Categoria> obj = categoriaRepository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException(
               "Id " + id + " não encontrado " + Categoria.class.getName()));
   }

   public List<Categoria> findByName(String nome) {
       return categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
   }

   public Categoria insert(Categoria categoria){
       categoria.setId(null);
       categoria.setNome(categoria.getNome());
       return  categoriaRepository.save(categoria);
   }

   public Categoria update(Categoria categoria){
       categoriaRepository.findById(categoria.getId());
       categoria.setNome(categoria.getNome());
       return categoriaRepository.save(categoria);
   }

   public void delete(Long id){
       categoriaRepository.findById(id);
       categoriaRepository.deleteById(id);
   }
}

package com.alexandre.gamestore.services;

import com.alexandre.gamestore.model.Categoria;
import com.alexandre.gamestore.repositories.CategoriaRepository;
import com.alexandre.gamestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
               "Objeto não encontrado: " + id + " Tipo: " + Categoria.class.getName()));
   }

   public List<Categoria> findByName(String nome) {
       return categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
   }

   public Categoria insert(Categoria categoria){
       return  categoriaRepository.save(categoria);
   }

   public Categoria update(Categoria obj){
      Categoria newObj = findById(obj.getId());
      updateData(newObj, obj);
      return categoriaRepository.save(newObj);
   }

   private void updateData(Categoria newObj, Categoria obj){
       newObj.setNome(obj.getNome());
   }

   public void delete(Long id){
       findById(id);
       categoriaRepository.deleteById(id);
   }
}

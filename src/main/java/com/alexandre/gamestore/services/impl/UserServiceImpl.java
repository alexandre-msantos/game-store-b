package com.alexandre.gamestore.services.impl;

import com.alexandre.gamestore.dto.UserDTO;
import com.alexandre.gamestore.model.User;
import com.alexandre.gamestore.repositories.UserRepository;
import com.alexandre.gamestore.services.UserService;
import com.alexandre.gamestore.services.exceptions.DataIntegrityViolationException;
import com.alexandre.gamestore.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User insertUser(UserDTO userDto) {
        if(repo.findByEmail(userDto.getEmail()).isPresent()){
            throw new DataIntegrityViolationException("O e-mail " + userDto.getEmail() + " já está em uso");
        }
        return repo.save(mapper.map(userDto, User.class));
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: " + id + " Tipo: " + User.class.getName()));
    }

    @Override
    public List<User> findAllUserByNameContainingIgnoreCase(String name) {
        return null;
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        findById(userDTO.getId());
        Optional<User> searchUser = repo.findByEmail(userDTO.getEmail());

            if((searchUser.isPresent()) && (searchUser.get().getId() != userDTO.getId())){
                throw new DataIntegrityViolationException("O e-mail " + userDTO.getEmail() + " já está em uso");
            }
            return repo.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(User user) {

    }
}
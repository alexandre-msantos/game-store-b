package com.alexandre.gamestore.services.impl;

import com.alexandre.gamestore.dto.UserDTO;
import com.alexandre.gamestore.model.User;
import com.alexandre.gamestore.repositories.UserRepository;
import com.alexandre.gamestore.services.exceptions.DataIntegrityViolationException;
import com.alexandre.gamestore.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Long   ID       = 1L;
    public static final String NAME     = "Alexandre Marcelino";
    public static final String EMAIL    = "alexandre@email.com";
    public static final String PASSWORD = "12345";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenInsertUserThenReturnSuccess() {
        Mockito.when(repository.save(any())).thenReturn(user);

        User response = service.insertUser(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenInsertUserThenReturnAnDataIntegrityViolationException(){
        Mockito.when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            service.insertUser(userDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("O e-mail " + userDTO.getEmail() + " já está em uso", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
    }

    @Test
    void whenFindByIdReturnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);

        User response = service.findById(anyLong());

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        try {
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado: " + ID + " Tipo: " + User.class.getName(), ex.getMessage());
        }
    }

    @Test
    void whenUpdateUserThenReturnSuccess() {
        Mockito.when(repository.save(any())).thenReturn(user);
        Mockito.when(repository.findById(userDTO.getId())).thenReturn(optionalUser);

        User response = service.updateUser(userDTO);

        assertNotNull(response);
        assertEquals(response.getId(), ID);
        assertEquals(response.getName(), NAME);
        assertEquals(response.getEmail(), EMAIL);
        assertEquals(response.getPassword(), PASSWORD);
    }

    @Test
    void whenUpdateUserThenReturnAnDataIntegrityViolationException(){
        Mockito.when(repository.findById(userDTO.getId())).thenReturn(optionalUser);
        Mockito.when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2L);
            service.updateUser(userDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("O e-mail " + user.getEmail() + " já está em uso", ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        Mockito.doNothing().when(repository).deleteById(anyLong());

        service.delete(ID);

        Mockito.verify(repository, Mockito.times(1)).deleteById(ID);
    }

    @Test
    void deleteWithObjectNotFoundException() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            service.delete(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado: " + ID + " Tipo: " + User.class.getName(), ex.getMessage());
        }
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(user);
        optionalUser = Optional.of(new User(1L, "Alexandre Marcelino", "alexandre@email.com", "12345"));
    }
}
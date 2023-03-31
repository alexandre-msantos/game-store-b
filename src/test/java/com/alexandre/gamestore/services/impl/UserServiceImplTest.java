package com.alexandre.gamestore.services.impl;

import com.alexandre.gamestore.dto.UserDTO;
import com.alexandre.gamestore.model.User;
import com.alexandre.gamestore.repositories.UserRepository;
import com.alexandre.gamestore.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void insertUser() {
    }

    @Test
    void whenFindAllReturn() {
    }

    @Test
    void whenFindByIdReturnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException(
                        "Objeto não encontrado: " + user.getId() + " Tipo: " + User.class.getName()));

        try {
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado: "
                    + user.getId() + " Tipo: " + User.class.getName(), ex.getMessage());
        }
    }

    @Test
    void updateUser() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(1L, "Alexandre Marcelino", "alexandre@email.com", "12345"));
    }
}
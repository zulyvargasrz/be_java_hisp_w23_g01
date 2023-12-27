package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meli.socialmeli.dtos.response.MessageDTO;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequestException;
import com.meli.socialmeli.exceptions.custom.DataSourceException;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import util.UserDTOUtilsGenerator;
import util.UserEntityUtilsGenerator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    IUserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("T-0003:Verificar que el tipo de ordenamiento alfabético exista. Continuar con normalidad - name_asc")
    void getFollowersListByNameAscShouldReturnSortedList() {
        //Arrange
        User userFromRepository = UserEntityUtilsGenerator.getUserWithThreeFollowers();
        when(userRepository.finById(100000)).thenReturn(userFromRepository);

        UserFollowersDTO userFollowersExpected = UserDTOUtilsGenerator.getUserFollowersDTOWithThreeFollowersOrderAsc();

        //Act
        UserFollowersDTO resultUserFollowers = userService.findFollowersById(100000, "name_asc");

        //Assert
        assertEquals(userFollowersExpected.getUser_id(),resultUserFollowers.getUser_id());
        assertEquals(userFollowersExpected.getUser_name(),resultUserFollowers.getUser_name());
        assertEquals(userFollowersExpected.getFollowers(),resultUserFollowers.getFollowers());
    }

    @Test
    @DisplayName("T-0003:Verificar que el tipo de ordenamiento alfabético exista. Continuar con normalidad - name_desc")
    void getFollowersListByNameDescShouldReturnSortedList() {
        //Arrange
        User userFromRepository = UserEntityUtilsGenerator.getUserWithThreeFollowers();
        when(userRepository.finById(100000)).thenReturn(userFromRepository);

        UserFollowersDTO userFollowersExpected = UserDTOUtilsGenerator.getUserFollowersDTOWithThreeFollowersOrderDesc();

        //Act
        UserFollowersDTO resultUserFollowers = userService.findFollowersById(100000, "name_desc");

        //Assert
        assertEquals(userFollowersExpected.getUser_id(),resultUserFollowers.getUser_id());
        assertEquals(userFollowersExpected.getUser_name(),resultUserFollowers.getUser_name());
        assertEquals(userFollowersExpected.getFollowers(),resultUserFollowers.getFollowers());
    }

    @Test
    @DisplayName("T-0003:Verificar que el tipo de ordenamiento alfabético exista. Continuar con normalidad - Orden no válido")
    void getFollowersListByNameEmptyShouldThrowException() {
        //Arrange
        User userFromRepository = UserEntityUtilsGenerator.getUserWithThreeFollowers();
        when(userRepository.finById(100000)).thenReturn(userFromRepository);

        //Act - Assert
        assertThrows(BadRequestException.class, ()->userService.findFollowersById(100000, "empty"));
    }


    private List<User> loadTestUsers(){
        List<User> userList;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            File file= ResourceUtils.getFile("classpath:static/users-2.json");
            userList = objectMapper.readValue(file,new TypeReference<List<User>>(){});
        } catch (IOException e) {
            throw new DataSourceException("There is an internal problem with the connection to the data source.");
        }
        return userList;
    }

    @Test
    @DisplayName("T-0001: Verificar que el usuario a seguir exista. Todo ok")
    public void followSellerOk(){
        //Arrange
        int userIdFollower = 100;
        int userIdFollowed = 1100;
        MessageDTO expected = new MessageDTO();
        expected.setMessage("Usuario seguido agregado");
        List<User> userList = loadTestUsers();
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        //Act
        MessageDTO result = userService.followSeller(userIdFollower, userIdFollowed);
        //Assert
        Assertions.assertEquals(expected.getMessage(), result.getMessage());
        Assertions.assertEquals(1, userList.get(0).getFollowed().size());
    }

    @Test
    @DisplayName("T-0001: Verificar que el usuario a seguir exista. Usuario a seguir no encontrado")
    public void followSellerInvalidFollowed(){
        //Arrange
        int userIdFollower = 100;
        int userIdFollowed = 9999;
        String expectedMessage = "Usuario a seguir no encontrado";
        List<User> userList = loadTestUsers();
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        //Act & Assert
        Exception e = Assertions.assertThrows(BadRequestException.class, () -> {
            userService.followSeller(userIdFollower, userIdFollowed);
        });
        Assertions.assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("T-0001: Verificar que el usuario a seguir exista. Usuario seguidor no encontrado")
    public void followSellerInvalidFollower(){
        //Arrange
        int userIdFollower = 9999;
        int userIdFollowed = 100;
        String expectedMessage = "Usuario seguidor no encontrado";
        List<User> userList = loadTestUsers();
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        //Act & Assert
        Exception e = Assertions.assertThrows(BadRequestException.class, () -> {
            userService.followSeller(userIdFollower, userIdFollowed);
        });
        Assertions.assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("T-0001: Verificar que el usuario a seguir exista. Vendedor ya seguido")
    public void followSellerAlreadyFollowed(){
        //Arrange
        List<User> userList = loadTestUsers();
        int userIdFollower = userList.get(0).getUser_id();
        int userIdFollowed = userList.get(1).getUser_id();
        userList.get(0).addFollowed(userList.get(1));
        userList.get(1).addFollower(userList.get(0));
        String expectedMessage = "El usuario ya sigue al usuario deseado";
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        //Act & Assert
        Exception e = Assertions.assertThrows(BadRequestException.class, () -> {
            userService.followSeller(userIdFollower, userIdFollowed);
        });
        Assertions.assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("T-0001: Verificar que el usuario a seguir exista. Usuario a seguir sin posts")
    public void followSellerWithoutPosts(){
        //Arrange
        List<User> userList = loadTestUsers();
        int userIdFollower = 100;
        int userIdFollowed = 5100;
        String expectedMessage = "Usuario inválido para seguir";
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        //Act & Assert
        Exception e = Assertions.assertThrows(BadRequestException.class, () -> {
            userService.followSeller(userIdFollower, userIdFollowed);
        });
        Assertions.assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("T-0001: Verificar que el usuario a seguir exista. Usuario siguiéndose a sí mismo")
    public void followSellerSameUser(){
        //Arrange
        List<User> userList = loadTestUsers();
        int userIdFollower = 100;
        int userIdFollowed = 100;
        String expectedMessage = "Usuario inválido para seguir";
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        //Act & Assert
        Exception e = Assertions.assertThrows(BadRequestException.class, () -> {
            userService.followSeller(userIdFollower, userIdFollowed);
        });
        Assertions.assertEquals(expectedMessage, e.getMessage());
    }
}

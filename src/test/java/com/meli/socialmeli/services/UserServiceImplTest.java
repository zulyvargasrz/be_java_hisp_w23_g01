package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequestException;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.UserDTOUtilsGenerator;
import util.UserEntityUtilsGenerator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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


}

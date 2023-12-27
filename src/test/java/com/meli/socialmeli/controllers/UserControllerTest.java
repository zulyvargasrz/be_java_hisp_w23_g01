package com.meli.socialmeli.controllers;

import com.meli.socialmeli.dtos.response.MessageDTO;
import com.meli.socialmeli.services.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    IUserService userService;

    @InjectMocks
    UserController userController;

    @Test
    @DisplayName("Follow seller ok")
    public void followeSellerOk() {
        //Arrange
        int userIdFollower = 100;
        int userIdFollowed = 1100;
        Mockito.when(userService.followSeller(userIdFollower, userIdFollowed))
                .thenReturn(new MessageDTO("Usuario seguido agregado"));
        ResponseEntity<?> expected = ResponseEntity.ok(new MessageDTO("Usuario seguido agregado"));
        //Act
        ResponseEntity<?> result = userController.followSeller(userIdFollower, userIdFollowed);
        //Assert
        Assertions.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assertions.assertEquals(((MessageDTO)expected.getBody()).getMessage(), ((MessageDTO)result.getBody()).getMessage());
    }
}

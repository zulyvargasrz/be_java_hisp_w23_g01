package com.meli.socialmeli.services;

import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequestException;
import com.meli.socialmeli.services.impl.ProductServiceImpl;
import com.meli.socialmeli.services.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import util.UserEntityUtilsGenerator;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static util.UserEntityUtilsGenerator.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    @DisplayName("T-0005: Verificar que el tipo de ordenamiento por fecha exista (US-0009): " +
            "El tipo de ordenamiento existe entonces retorna un PostsFromFollowsDTO sin lanzar excepción.")
    void getAllPostsFollowsLastTwoWeeksOrdenamientoValidoTest(){
        // Arrange
        String order = "date_asc";
        User user = getUserFollwingSellers();
        List<User> sellers = user.getFollowers();
        Integer id = user.getUser_id();
        // Act
        when(userService.findFollowsByIdProductService(Mockito.anyInt())).thenReturn(sellers);
        // Assert
        assertDoesNotThrow(() -> productService.getAllPostsFollowsLastTwoWeeks(id, order));
    }

    @Test
    @DisplayName("T-0005: Verificar que el tipo de ordenamiento por fecha exista (US-0009): " +
            "El tipo de ordenamiento no existe entonces lanza BadRequestException.")
    void getAllPostsFollowsLastTwoWeeksOrdenamientoInvalidoTest(){
        // Arrange
        String order = "de_mayor_a_menor";
        User user = new User(1,"User", null, null, null);
        Integer id = user.getUser_id();
        // Act - Assert
        assertThrows(
                BadRequestException.class,
                () -> productService.getAllPostsFollowsLastTwoWeeks(id, order)
                );
    }
}

package com.meli.socialmeli.services;

import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequestException;
import com.meli.socialmeli.repositories.IProductRepository;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.impl.ProductServiceImpl;
import com.meli.socialmeli.services.impl.UserServiceImpl;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
        User user = new User(1,"User", List.of(), List.of(), List.of());
        User seller1 = new User(2, "seller1", List.of(), List.of(), List.of());
        User seller2 = new User(3, "seller2", List.of(), List.of(), List.of());
        List<User> sellers = List.of(seller1, seller2);
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

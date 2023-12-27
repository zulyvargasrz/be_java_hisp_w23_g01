package com.meli.socialmeli.services;

import com.meli.socialmeli.repositories.IProductRepository;
import com.meli.socialmeli.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    IProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;
}

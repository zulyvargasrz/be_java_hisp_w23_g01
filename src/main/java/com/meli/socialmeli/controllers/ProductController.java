package com.meli.socialmeli.controllers;

import com.meli.socialmeli.dtos.response.PostsFromFollowsDTO;
import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.services.IProductService;
import com.meli.socialmeli.services.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService iProductService;
    public ProductController(ProductServiceImpl productService){
        this.iProductService = productService;
    }
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsFromFollowsDTO> getAllPostsFollowsLastTwoWeeks(@PathVariable Integer userId, @RequestParam(defaultValue = "date_asc") String order)
    {
        return new ResponseEntity<>(iProductService.getAllPostsFollowsLastTwoWeeks(userId, order), HttpStatus.OK);
    }
}

package com.meli.socialmeli.services.impl;

import com.meli.socialmeli.dtos.response.PostNoPromoDTO;
import com.meli.socialmeli.dtos.response.PostsFromFollowsDTO;
import com.meli.socialmeli.dtos.response.ProductDTO;
import com.meli.socialmeli.entities.Post;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.NotFoundException;
import com.meli.socialmeli.repositories.IProductRepository;
import com.meli.socialmeli.repositories.impl.ProductRepositoryImpl;
import com.meli.socialmeli.dtos.response.PostsFromFollowsDTO;
import com.meli.socialmeli.services.IProductService;
import com.meli.socialmeli.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IUserService userService;

    public ProductServiceImpl(ProductRepositoryImpl productRepository, UserServiceImpl userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public PostsFromFollowsDTO getAllPostsFollowsLastTwoWeeks(Integer userId) {
        List<User> follows = userService.findFollowsByIdProductService(userId);
        if (follows.isEmpty()) throw new NotFoundException("The user with id: " + userId + " does not follow anyone");
        List<Post> posts = productRepository.getPostsFollowersLastTwoWeeks(follows);
        if (posts.isEmpty()) throw new NotFoundException("The sellers of the user with id: " + userId +
                " do not have any publications in the last two weeks");
        List<PostNoPromoDTO> postFromFollows = posts.stream()
                .map(p -> new PostNoPromoDTO(
                        p.getPost_id(),
                        p.getDate().toString(),
                        new ProductDTO(
                                p.getProduct().getProduct_id(),
                                p.getProduct().getProduct_name(),
                                p.getProduct().getType(),
                                p.getProduct().getBrand(),
                                p.getProduct().getColor(),
                                p.getProduct().getNotes()
                        ),
                        p.getCategory(),
                        p.getPrice()
                ))
                .toList();
        return new PostsFromFollowsDTO(userId, postFromFollows);
    }
}

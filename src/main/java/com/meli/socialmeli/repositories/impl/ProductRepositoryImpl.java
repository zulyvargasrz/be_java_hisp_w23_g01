package com.meli.socialmeli.repositories.impl;

import com.meli.socialmeli.entities.Post;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.repositories.IProductRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private List<User> users = new ArrayList<>();
    @Override
    public List<Post> getPostsFollowersLastTwoWeeks(List<User> follows) {
        return follows.stream()
                .filter(u -> u.getPosts() != null && !u.getPosts().isEmpty())
                .flatMap(u -> u.getPosts().stream())
                .filter(p -> !p.getDate().isBefore(LocalDate.now().minusWeeks(2)))
                .toList();
    }

    @Override
    public void newPost(User user) {

    }

 /*   @Override
    public void newPost(User user) {
        users.set(users.indexOf(user), user);
    }*/
}

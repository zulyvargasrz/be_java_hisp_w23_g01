package com.meli.socialmeli.repositories;

import com.meli.socialmeli.entities.Post;
import com.meli.socialmeli.entities.User;

import java.util.List;


public interface IUserRepository {
    User finById(int id);
    List<User> getUserFollowers(User user);
}

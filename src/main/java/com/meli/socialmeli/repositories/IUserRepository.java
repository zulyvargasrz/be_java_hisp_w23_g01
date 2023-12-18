package com.meli.socialmeli.repositories;

import com.meli.socialmeli.entities.User;

import java.util.List;

import com.meli.socialmeli.entities.User;


public interface IUserRepository {
    List<User> findAll();
    User finById(int id);
    User getUserById(int userId);
    User updateUser(User user);

    List<User> getFollowedUsers(int userId);
}

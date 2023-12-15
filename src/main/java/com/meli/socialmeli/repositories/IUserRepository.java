package com.meli.socialmeli.repositories;

import com.meli.socialmeli.entities.User;

import java.util.List;

public interface IUserRepository {
    User getUserById(int userId);
    User updateUser(User user);

    List<User> getFollowedUsers(int userId);
}

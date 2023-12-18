package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.entities.User;

import java.util.List;

public interface IUserService {
    UserFollowersDTO findFollowersById(int id);
    List<User> findFollowsByIdProductService(int id);
}

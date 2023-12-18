package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;

public interface IUserService {
    UserFollowersDTO findFollowersById(int id);
}

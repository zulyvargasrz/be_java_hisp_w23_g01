package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();
    MessageDto followSeller(int userId, int userIdToFollow);
}

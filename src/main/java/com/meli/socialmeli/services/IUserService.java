package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.UserDto;
import com.meli.socialmeli.dtos.response.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> findAll();
    MessageDto followSeller(int userId, int userIdToFollow);
}

package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.UserDto;
import com.meli.socialmeli.dtos.response.FollowersCountDto;
import com.meli.socialmeli.dtos.response.UserResponseDto;

import java.util.List;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;

public interface IUserService {

    UserFollowersDTO findFollowersById(int id, String order);
    List<UserResponseDto> findAll();
    MessageDto followSeller(int userId, int userIdToFollow);
    FollowersCountDto getFollowersCount(int userId);

}

package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.UserDto;
import com.meli.socialmeli.dtos.response.UserResponseDto;

import java.util.List;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;

import com.meli.socialmeli.dtos.UserUnfollowDto;

public interface IUserService {

    List<UserResponseDto> findAll();
    MessageDto followSeller(int userId, int userIdToFollow);
    UserFollowersDTO findFollowersById(int id);
    UserUnfollowDto unfollowUser(int userId, int userIdToUnfollow);

}

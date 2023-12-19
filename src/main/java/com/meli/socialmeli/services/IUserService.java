package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.response.*;
import com.meli.socialmeli.entities.User;

import java.util.List;

public interface IUserService {
    List<User> findFollowsByIdProductService(int userId);
    UserFollowersDTO findFollowersById(int id, String order);
    List<UserResponseDto> findAll();
    MessageDto followSeller(int userId, int userIdToFollow);
    FollowersCountDto getFollowersCount(int userId);
    UserFollowedDTO findFollowedById(int userId, String order);

    UserUnfollowDTO unfollowUser(int userId, int userIdToUnfollow);
}
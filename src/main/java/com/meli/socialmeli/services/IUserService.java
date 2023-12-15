package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.UserUnfollowDto;

public interface IUserService {
    UserUnfollowDto unfollowUser(int userId, int userIdToUnfollow);

}

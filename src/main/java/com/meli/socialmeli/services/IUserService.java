package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;

public interface IUserService {
    MessageDto followSeller(int userId, int userIdToFollow);
}

package com.meli.socialmeli.services.impl;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.services.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public MessageDto followSeller(int userId, int userIdToFollow) {
        return null;
    }
}

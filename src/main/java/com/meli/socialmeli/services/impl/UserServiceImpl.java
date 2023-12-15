package com.meli.socialmeli.services.impl;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequest;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public MessageDto followSeller(int userId, int userIdToFollow) {
        User followerUser = userRepository.findAll()
                .stream()
                .filter(u -> userId == u.getUser_id())
                .findFirst()
                .orElse(null);
        if(followerUser == null){
            throw new BadRequest("User not found");
        }

        User followedUser = userRepository.findAll()
                .stream()
                .filter(u -> userIdToFollow == u.getUser_id())
                .findFirst()
                .orElse(null);
        if(followedUser == null){
            throw new BadRequest("User not found");
        }

        followerUser.addFollowed(followedUser);
        followedUser.addFollower(followerUser);

        return new MessageDto("Followed added");
    }
}

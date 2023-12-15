package com.meli.socialmeli.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.UserDto;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequest;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        userList.forEach(u ->  userDtoList.add(mapper.convertValue(u, UserDto.class)));
        return userDtoList;
    }

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

        if(followedUser.getPosts().isEmpty() || userId == userIdToFollow){
            throw new BadRequest("Invalid User to follow");
        }

        followerUser.addFollowed(followedUser);
        followedUser.addFollower(followerUser);

        return new MessageDto("Followed added");
    }
}

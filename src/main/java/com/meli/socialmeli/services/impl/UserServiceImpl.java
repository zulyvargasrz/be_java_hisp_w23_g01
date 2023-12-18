package com.meli.socialmeli.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.UserDto;
import com.meli.socialmeli.dtos.response.UserResponseDto;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.BadRequest;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.dtos.response.UserInfoDTO;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.NotFoundException;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public List<UserResponseDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        userList.forEach(u ->  {
            UserResponseDto userResponseDto = new UserResponseDto();

            userResponseDto.setUser_id(u.getUser_id());
            userResponseDto.setUser_name(u.getUser_name());

            List<String> followers = u.getFollowers().stream()
                    .map(User::getUser_name)
                    .toList();
            userResponseDto.setFollowers(followers);

            List<String> followed = u.getFollowed().stream()
                    .map(User::getUser_name)
                    .toList();
            userResponseDto.setFollowed(followed);

            userResponseDtoList.add(userResponseDto);
        });
        return userResponseDtoList;
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

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserFollowersDTO findFollowersById(int id) {
        Optional<User> userFound = Optional.ofNullable(userRepository.finById(id));
        if (userFound.isEmpty()) throw new NotFoundException("There is no user with the id: " + id);

        List<UserInfoDTO> followers = userFound.get().getFollowers()
                                                     .stream()
                                                     .map( f -> new UserInfoDTO(f.getUser_id(), f.getUser_name() ) )
                                                     .toList();

        return new UserFollowersDTO(userFound.get().getUser_id(), userFound.get().getUser_name(), followers);
    }
}
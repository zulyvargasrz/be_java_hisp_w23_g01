package com.meli.socialmeli.services.impl;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.dtos.response.UserInfoDTO;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.NotFoundException;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

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

    @Override
    public List<User> findFollowsByIdProductService(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.finById(id));
        if (user.isEmpty()) throw new NotFoundException("There is no user with the id: " + id);

        return userRepository.getUserFollowers(user.get());
    }
}
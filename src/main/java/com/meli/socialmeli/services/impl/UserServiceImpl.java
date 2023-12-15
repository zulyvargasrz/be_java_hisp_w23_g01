package com.meli.socialmeli.services.impl;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.NotFoundException;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserFollowersDTO findFollowersById(int id) {
        Optional<User> userFound = userRepository.finById(id);
        if (userFound.isEmpty()) throw new NotFoundException("There is no user with the id: " + id);
        
        return null;
    }
}
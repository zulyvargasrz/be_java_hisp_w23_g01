package com.meli.socialmeli.services.impl;

import com.meli.socialmeli.dtos.UserUnfollowDto;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.repositories.IUserRepository;
import com.meli.socialmeli.repositories.impl.UserRepositoryImpl;
import com.meli.socialmeli.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    IUserRepository userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserUnfollowDto unfollowUser(int userId, int userIdToUnfollow) {
        User user = userRepository.getUserById(userId);
        if (user != null) {
            List<User> followedUsers = user.getFollowed();
            followedUsers.removeIf(followedUser -> followedUser.getUser_id() == userIdToUnfollow);
            user.setFollowed(followedUsers);
            userRepository.updateUser(user);
        }
        return new UserUnfollowDto(userId, userIdToUnfollow);
    }
}

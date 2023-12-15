package com.meli.socialmeli.controllers;

import com.meli.socialmeli.services.IUserService;
import com.meli.socialmeli.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(int userId, int userIdToUnfollow){
        return ResponseEntity.ok(userService.unfollowUser(userId, userIdToUnfollow));
    }
}

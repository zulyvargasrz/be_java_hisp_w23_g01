package com.meli.socialmeli.controllers;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowersDTO> getFollowersById(@PathVariable int id){
        return new ResponseEntity<>(userService.findFollowersById(id), HttpStatus.OK);
    }
}

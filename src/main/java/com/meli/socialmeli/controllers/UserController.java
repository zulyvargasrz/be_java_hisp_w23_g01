package com.meli.socialmeli.controllers;

import com.meli.socialmeli.dtos.response.UserFollowersDTO;
import com.meli.socialmeli.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSeller(@PathVariable int userId, @PathVariable int userIdToFollow){
        return ResponseEntity.ok(userService.followSeller(userId, userIdToFollow));
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowersDTO> getFollowersById(@PathVariable int userId, @RequestParam(defaultValue = "name_asc") String order){
        return ResponseEntity.ok(userService.findFollowersById(userId, order));
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable int userId){
        return ResponseEntity.ok(null);
    }

}
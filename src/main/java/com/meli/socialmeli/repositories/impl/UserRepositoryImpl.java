package com.meli.socialmeli.repositories.impl;

import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.repositories.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final List<User> userList = new ArrayList<>();

    UserRepositoryImpl(){
        userList.add(new User(1, "TestUser1"));
        userList.add(new User(2, "TestUser2"));
        userList.add(new User(3, "TestUser3"));
        userList.add(new User(4, "TestUser4"));
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}

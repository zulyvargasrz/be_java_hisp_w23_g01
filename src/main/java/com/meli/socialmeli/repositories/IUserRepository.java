package com.meli.socialmeli.repositories;

import com.meli.socialmeli.entities.User;

import java.util.List;

import com.meli.socialmeli.entities.User;


public interface IUserRepository {
    List<User> findAll();
    User finById(int id);

}

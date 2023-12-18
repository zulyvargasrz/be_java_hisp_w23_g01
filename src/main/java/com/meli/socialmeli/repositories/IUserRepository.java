package com.meli.socialmeli.repositories;

import com.meli.socialmeli.entities.User;


public interface IUserRepository {
    User finById(int id);
}

package com.meli.socialmeli.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.repositories.IUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> listOfUsers = new ArrayList<>();

    public UserRepositoryImpl() throws IOException {
        loadDataBase();
    }

    @Override
    public User getUserById(int userId) {
        return listOfUsers.stream()
                .filter(user -> user.getUser_id() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User updateUser(User user) {
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (listOfUsers.get(i).getUser_id() == user.getUser_id()) {
                listOfUsers.set(i, user);
                break;
            }
        }
        return getUserById(user.getUser_id());
    }

    @Override
    public List<User> getFollowedUsers(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            return user.getFollowed();
        } else {
            return null;
        }
    }

    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users ;

        file= ResourceUtils.getFile("classpath:vehicles_100.json");
        users= objectMapper.readValue(file,new TypeReference<List<User>>(){});

        listOfUsers = users;
    }
}

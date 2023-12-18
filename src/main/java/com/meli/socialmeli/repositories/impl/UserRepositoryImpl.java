package com.meli.socialmeli.repositories.impl;

import com.meli.socialmeli.entities.Post;
import com.meli.socialmeli.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meli.socialmeli.entities.User;
import com.meli.socialmeli.exceptions.custom.DataSourceException;
import com.meli.socialmeli.repositories.IUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        Post testPost1 = new Post();
        List<Post> user1PostsList = new ArrayList<>();
        user1PostsList.add(testPost1);
        userList.get(0).setPosts(user1PostsList);

    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    private List<User> users;
    public UserRepositoryImpl() throws IOException {
        loadDataBase();
    }

    @Override
    public User finById(int id) {
        return users.stream().filter( u -> u.getUser_id() == id ).findFirst().orElse(null);
    }

    private void loadDataBase() {
        users = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            File file= ResourceUtils.getFile("classpath:static/users-2.json");
            users = objectMapper.readValue(file,new TypeReference<List<User>>(){});

        } catch (IOException e) {
            throw new DataSourceException("There is an internal problem with the connection to the data source.");
        }
    }
}

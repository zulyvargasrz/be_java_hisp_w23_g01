package com.meli.socialmeli.dtos;

import com.meli.socialmeli.entities.Post;
import com.meli.socialmeli.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer user_id;
    private String user_name;
    private List<User> followers;
    private List<User> followed;
    private List<Post> posts;
}

package com.meli.socialmeli.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer user_id;
    private String user_name;
    private List<User> followers;
    private List<User> followed;
    private List<Post> posts;

}

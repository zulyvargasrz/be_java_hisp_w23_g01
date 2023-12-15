package com.meli.socialmeli.dtos.response;

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
public class UserResponseDto {
    private Integer user_id;
    private String user_name;
    private List<String> followers;
    private List<String> followed;
}

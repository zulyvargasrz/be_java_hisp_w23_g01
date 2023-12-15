package com.meli.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUnfollowDto {
    private int userId;
    private int userIdToUnfollow;
}

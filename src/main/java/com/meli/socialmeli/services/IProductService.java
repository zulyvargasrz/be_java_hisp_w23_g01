package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.response.PostsFromFollowsDTO;

public interface IProductService {
    PostsFromFollowsDTO getAllPostsFollowsLastTwoWeeks(Integer userId);
}

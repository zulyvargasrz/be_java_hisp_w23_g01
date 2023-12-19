package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.response.PostDTO;
import com.meli.socialmeli.dtos.response.PostsFromFollowsDTO;


public interface IProductService {
    PostsFromFollowsDTO getAllPostsFollowsLastTwoWeeks(Integer userId);
    MessageDto newPost(PostDTO post);
}

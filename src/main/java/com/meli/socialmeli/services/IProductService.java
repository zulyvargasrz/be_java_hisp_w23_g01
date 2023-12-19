package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.dtos.response.PostDTO;
import com.meli.socialmeli.dtos.response.PostsFromFollowsDTO;


public interface IProductService {

    MessageDto newPost(PostDTO post);

    PostsFromFollowsDTO getAllPostsFollowsLastTwoWeeks(Integer userId, String order);

}

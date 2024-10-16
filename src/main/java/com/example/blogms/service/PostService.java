package com.example.blogms.service;

import com.example.blogms.dto.request.PostRequestDto;
import com.example.blogms.dto.response.MyPostResponseDto;
import com.example.blogms.dto.response.PostResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PostService {
    void createPost(PostRequestDto requestDto, HttpServletRequest servletRequest);

    List<MyPostResponseDto> getMyPost(int page, int size);

    void deleteMyPostById(Long postId);

    List<PostResponseDto> getAllPost(int page, int size);

    PostResponseDto getPostById(Long postId);

    void updateMyPost(Long postId, PostRequestDto requestDto);

    List<PostResponseDto> getPostsAndProfileByUserEmail(String email, int page, int size);
}
